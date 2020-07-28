import React from 'react';
import { Table, Tag } from 'antd';
import styles from './index.less';

import { Input, Popconfirm, notification } from 'antd';
import { AudioOutlined } from '@ant-design/icons';

import { login_info } from '@/services/login';
import { history } from 'umi';

import $ from  'jquery';
import config from '../../../config.js';

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

export default class StudentList extends React.Component{
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
        dataIndex: 'studentname',
        key: 's_name',
        render: (text) => <a>{text}</a>,
      },
      {
        title: '学号',
        dataIndex: 'studentid',
        key: 's_ID',
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
            <Popconfirm title="确认解绑？" onConfirm={() => this.handleUidUnbind(record.studentid)}>
              <a>解绑</a>
            </Popconfirm>
          </span>
        )
      },
      {
        title: '所选导师',
        dataIndex: 'tutorname',
        key:'tut_name',
        render: (text, record) => (
          record.tutorname==null ?
          <span>
            <Tag color="default">未选择</Tag>
          </span> :
          <span>
            {record.tutorname} &nbsp;&nbsp;
            <Popconfirm title="确认解绑？" onConfirm={() => this.handleTutorUnbind(record.studentid)}>
              <a>解绑</a>
            </Popconfirm>
          </span>
        )
      }
    ];

    if(login_info.isLoggedIn === false){
      history.push('/user/login');
      notLoggedIn('error');
    }

    this.acquireStudents();
  }

  acquireStudents(){
    $.ajax({
      type: "get",
      url: global.config.backendUrl+"/api/admin/students",
      contentType: "application/json;charset=utf-8;",
      beforeSend (XMLHttpRequest) {
        XMLHttpRequest.setRequestHeader("token", login_info.token);
      },
      dataType: "text",
      success:function(data) {
          var result = JSON.parse(data);
          var s_list = [];
          for (var i = 0; i < result.length; ++i) {
              var newStudent={
                  key: i,
                  id: result[i].id,
                  studentname: result[i].studentname,
                  studentid: result[i].studentid,
                  uid: result[i].uid,
                  tutorid: result[i].tutorid,
                  tutorname: result[i].tutorname,
              }
              s_list = [...s_list, newStudent];
          }
          console.log(result);
          _this.setState({
              datalist: result,
              showlist: s_list,
          })
      },
      error:function(error) {
          console.log("acquire students error");
      }
    });
  }

  handleUidUnbind=(realId)=>{
    var info = {
      realId: realId,
      type: "1",
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
            _this.acquireStudents();
          }
      },
      error:function(error) {
          console.log("error: "+error)
      }
    });
  }

  handleTutorUnbind=(studentid)=>{
    // var info = {
    //   studentId: studentid,
    // };
    // var info_json = JSON.stringify(info);
    // console.log(info_json);

    $.ajax({
      type: "post",
      url: global.config.backendUrl+"/api/admin/unbindtutor",
      contentType: "application/json;charset=utf-8;",
      beforeSend (XMLHttpRequest) {
        XMLHttpRequest.setRequestHeader("token", login_info.token);
      },
      data: studentid,
      dataType: "text",
      success:function(data) {
          console.log("success with returning: "+data);
          var feedback=JSON.parse(data);
          if(feedback.code != 200){
            unbind_fail('error');
          }else{
            unbind_success('success');
            _this.acquireStudents();
          }
      },
      error:function(error) {
          console.log("error: "+ error)
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
      if(this.state.datalist[i].studentid == string || this.state.datalist[i].studentname.search(string) >= 0){
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
            placeholder="输入要查询的姓名或学号"
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

