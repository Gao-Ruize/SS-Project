import React from 'react';
import { Table, Tag, notification } from 'antd';
import styles from './index.less';
import { DatePicker } from 'antd';
import { Input } from 'antd';
import { AudioOutlined } from '@ant-design/icons';
import { history } from 'umi';

import { login_info } from '@/services/login';

import $ from  'jquery';
import config from '../../../config.js';
// import { history } from '@@/core/history';

const { Search } = Input;


const notLoggedIn = type => {
  notification[type]({
    message: '未登录或登录失效！',
    description:
      '请先进行登录',
  });
};


var _this;

export default class MsgList extends React.Component{
  constructor(props){
    super(props);
    _this = this;

    this.state = {
      datalist: [],
      showlist: [],
      start_time: null,
      end_time: null,
      datePickerRangeWarning: '',

    };

    this.columns = [
      {
        title: '通知',
        dataIndex: 'title',
        key: 'title',
        render: (text, record) => <a href={'#/msgdetail/'+record.id}>{text}</a>,
      },
      {
        title: '发布时间',
        dataIndex: 'releasetime',
        key: 'releasetime',
        render: (text) => <p>{new Date(text*1000).toLocaleDateString()}</p>,
      },
      {
        title: '阅读情况',
        key: 'action',
        render: (text, record) => (
          <span>
            <a
              href={'#/msgdetail/'+record.msgid}
              style={{
                marginRight: 16,
              }}
            >
              <div>
                {'学生阅读情况：'+record.sreadnum+'/'+record.stotnum}
              </div>
              <div>
                {'导师阅读情况：'+record.treadnum+'/'+record.ttotnum}
              </div>
            </a>
          </span>
        ),
      },
    ];

    if(login_info.isLoggedIn === false){
      notLoggedIn('error');
      history.push('/user/login');
    }

    this.acquireMsgs();
  }

  acquireMsgs(){
    $.ajax({
      type: "get",
      url: global.config.backendUrl+"/api/admin/jwcmsgs",
      contentType: "application/json;charset=utf-8;",
      beforeSend (XMLHttpRequest) {
        XMLHttpRequest.setRequestHeader("token", login_info.token);
      },
      dataType: "text",
      success:function(data) {
          var result = JSON.parse(data);
          var msg_list = [];
          for (var i = 0; i < result.length; ++i) {
              var newMsg={
                  key: i,
                  id: result[i].id,
                  title: result[i].title,
                  releasetime: result[i].releasetime,
                  phase: result[i].phase,
                  treadnum: result[i].treadnum,
                  ttotnum: result[i].ttotnum,
                  sreadnum: result[i].sreadnum,
                  stotnum: result[i].stotnum,
              }
              msg_list = [...msg_list, newMsg];
          }
          console.log(result);
          _this.setState({
              datalist: result,
              showlist: msg_list,
          })
      },
      error:function(error) {
          console.log("acquire messages error");
      }
    });
  }

  onChangeStartTime=(date, dateString)=>{
    var res;
    if(date == null){
      res = null;
    }else{
      res = Math.round(Date.parse(date) / 1000);
    }
    this.state.start_time = res;
    this.checkTime()
    return res;
  }
  onChangeEndTime=(date, dateString)=>{
    var res;
    if(date == null){
      res = null;
    }else{
      res = Math.round(Date.parse(date) / 1000);
    }
    this.state.end_time = res;
    this.checkTime()
    return res;
  }

  checkTime=()=>{
    var newList = [];
    if(this.state.start_time != null && this.state.end_time != null){
      if(this.state.start_time >= this.state.end_time){
        //document.getElementById("datePickerRangeWarning").innerHTML = "Invalid Time Range!";
        this.setState({
          datePickerRangeWarning: "Invalid Time Range!",
          showList: newList,
        })
        return;
      }
    }
    //document.getElementById("datePickerRangeWarning").innerHTML = "";

    for(var i = 0; i < this.state.datalist.length; ++i){
      if(this.state.start_time == null || this.state.datalist[i].releasetime > this.state.start_time){
        if(this.state.end_time == null || this.state.datalist[i].releasetime < this.state.end_time)
          // var newRow={
          //   key: this.state.list[i].key,
          //   title: this.state.list[i].title,
          //   release_time: this.state.list[i].release_time,
          //   read_cond: '学生：'+String(this.state.list[i].s_read_num)+'/'+String(this.state.list[i].s_tot_num),

          // }
          // newList = [...newList, newRow];
          newList = [...newList, this.state.datalist[i]];
      }
    }
    this.setState({
      datePickerRangeWarning: "",
      showlist: newList,
    })
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
      if(this.state.datalist[i].title.search(string) >= 0){
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
            placeholder="输入关键字"
            onSearch={this.onSearch}
            enterButton
          />
          <br />
          <br />
        </div>
        <div className="dataPicker">
          根据发布时间筛选：
          <br />
          <DatePicker id="startTime" onChange={this.onChangeStartTime}  placeholder="选择起始时间" />
          {/* <br />
          <br /> */}
          &nbsp;&nbsp; ~ &nbsp; &nbsp;
          <DatePicker id="endTime" onChange={this.onChangeEndTime} placeholder="选择截止时间" />
          &nbsp; &nbsp;
          {/* <Button onClick={this.checkTime}>确认</Button> */}
          <p id="datePickerRangeWarning" style={{ display:"inline", color:"orange"}}>{this.state.datePickerRangeWarning}</p>
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
