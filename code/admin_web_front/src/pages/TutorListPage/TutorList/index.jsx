import React from 'react';
import { Table, Tag } from 'antd';
import styles from './index.less';

import { Input, Popconfirm, notification } from 'antd';
import { AudioOutlined } from '@ant-design/icons';

import $ from  'jquery';
import config from '../../../config.js';

import { login_info } from '@/services/login';
import { history } from 'umi';

const { Search } = Input;

const unbind_fail = type => {
  notification[type]({
      message: '解除绑定失败！',
      description:
      '',
  });
};
const unbind_success = type => {
  notification[type]({
    message: '解除绑定成功！',
    description:
    '',
  })
};
const notLoggedIn = type => {
  notification[type]({
    message: '未登录或登录失效！',
    description:
      '请先进行登录',
  });
};

var _this;

export default class TutorList extends React.Component{
  constructor(props){
    super(props);
    _this = this;

    this.state = {
      datalist: [],
      showlist: [],
    }

    this.columns = [
      {
        title: '姓名',
        dataIndex: 'tutorname',
        key: 'tutorname',
        render: (text) => <a>{text}</a>,
      },
      {
        title: '工号',
        dataIndex: 'tutorid',
        key: 'tutorid',
      },
      {
        title: '绑定微信号',
        dataIndex: 'uid',
        key: 'uid',
        render: (text, record) => (
          record.uid==null ?
          <span>
            <Tag color="default">未绑定</Tag>
          </span> :
          <span>
            {record.uid} &nbsp;&nbsp;
            <Popconfirm title="确认解绑？" onConfirm={() => this.handleUidUnbind(record.tutorid)}>
              <a>解绑</a>
            </Popconfirm>
          </span>
        )
      },
    ];

    if(login_info.isLoggedIn === false){
      history.push('/user/login');
      notLoggedIn('error');
    }

    this.acquireTutors();
  }

  acquireTutors(){
    $.ajax({
      type: "get",
      url: global.config.backendUrl+"/api/admin/tutors",
      contentType: "application/json;charset=utf-8;",
      beforeSend (XMLHttpRequest) {
        XMLHttpRequest.setRequestHeader("token", login_info.token);
      },
      dataType: "text",
      success:function(data) {
          var result = JSON.parse(data);
          var tut_list = [];
          for (var i = 0; i < result.length; ++i) {
              var newTutor={
                  key: i,
                  id: result[i].id,
                  tutorname: result[i].tutorname,
                  tutorid: result[i].tutorid,
                  uid: result[i].uid,
              }
              tut_list = [...tut_list, newTutor];
          }
          _this.setState({
            datalist: result,
            showlist: tut_list,
          })
      },
      error:function(error) {
          console.log("acquire tutors error");
      }
    });
  }


  handleUidUnbind=(realId)=>{
    var info = {
      realId: realId,
      type: "2",
    };
    var info_json = JSON.stringify(info);
    console.log(info_json);

    $.ajax({
      type: "post",
      url: global.config.backendUrl+"/api/admin/unbind",
      contentType: "application/json;charset=utf-8;",
      beforeSend (XMLHttpRequest) {
        XMLHttpRequest.setRequestHeader("token", login_info.token);
      },
      data: info_json,
      dataType: "text",
      success:function(data) {
          console.log("success with returning: "+data);
          var feedback=JSON.parse(data);
          if(feedback.code != 200){
            unbind_fail('error');
          }else{
            unbind_success('success');
            _this.acquireTutors();
          }
      },
      error:function(error) {
          console.log("error: "+error)
      }
    });
  }

  onSearch=(string)=>{
    if(string == ''){
      this.setState({
        showlist: this.state.datalist,
      });
      return;
    }
    var searchList = [];
    for(var i = 0; i < this.state.datalist.length; ++i){
      if(this.state.datalist[i].tutorid == string || this.state.datalist[i].tutorname.search(string) >= 0){
        searchList = [...searchList, this.state.datalist[i]];
      }
    }
    this.setState({
      showlist: searchList,
    })
  }

  render(){
    return(
      <div className={styles.container}>
        <div className="search">
          <Search
            style={{ width: 512 }}
            placeholder="输入要查询的姓名或工号"
            onSearch={this.onSearch}
            enterButton
          />
          <br />
          <br />
        </div>
        <div id="components-table-demo-basic">
          <Table columns={this.columns} dataSource={this.state.showlist} />
        </div>
      </div>
    )
  }
}
