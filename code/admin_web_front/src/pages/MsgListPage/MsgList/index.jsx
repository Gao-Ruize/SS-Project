import React from 'react';
import { Table, Tag } from 'antd';
import styles from './index.less';
import { DatePicker } from 'antd';
import { Input } from 'antd';
import { AudioOutlined } from '@ant-design/icons';

const { Search } = Input;



const columns = [
  {
    title: '通知',
    dataIndex: 'key',
    key: 'title',
    render: (text) => <a href={'#/msgdetail/1'}>{text}</a>,
  },
  {
    title: '发布时间',
    dataIndex: 'release_time',
    key: 'release_time',
    render: (text) => <p>{new Date(text*1000).toLocaleDateString()}</p>,
  },
  {
    title: '阅读情况',
    key: 'action',
    render: (text, record) => (
      <span>
        <a
          href=""
          style={{
            marginRight: 16,
          }}
        >
          查看阅读情况
        </a>
      </span>
    ),
  },
];
const data = [
  {
    key: '1',
    msgid: 1,
    title: '毕设开题提交通知',
    release_time: 1582992000,
    phase: 1,
    t_read_num: 16,
    t_tot_num: 16,
    s_read_num: 64,
    s_tot_num: 64,
  },
  {
    key: '2',
    msgid: 3,
    title: '毕设开题提交入口即将关闭',
    release_time: 1584115200,
    phase: 1,
    t_read_num: 16,
    t_tot_num: 16,
    s_read_num: 62,
    s_tot_num: 64,
  },
  {
    key: '3',
    msgid: 4,
    title: '中期检查通知',
    release_time: 1590681600,
    phase: 2,
    t_read_num: 15,
    t_tot_num: 16,
    s_read_num: 60,
    s_tot_num: 64,
  },
  {
    key: '4',
    msgid: 2,
    title: '阶段4',
    release_time: 1594742400,
    phase: 4,
    t_read_num: 12,
    t_tot_num: 16,
    s_read_num: 46,
    s_tot_num: 64,
  }
];



export default class MsgList extends React.Component{
  constructor(props){
    super(props);
    
    this.state = {
      list: data,
      showList: data,
      start_time: null,
      end_time: null,
      datePickerRangeWarning: '',

    };

    
this.columns = [
  {
    title: '通知',
    dataIndex: 'title',
    key: 'title',
    render: (text, record) => <a href={'#/msgdetail/'+record.msgid}>{text}</a>,
  },
  {
    title: '发布时间',
    dataIndex: 'release_time',
    key: 'release_time',
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
            {'学生阅读情况：'+record.s_read_num+'/'+record.s_tot_num}
          </div>
          <div>
            {'导师阅读情况：'+record.t_read_num+'/'+record.t_tot_num}
          </div>
        </a>
      </span>
    ),
  },
];
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

    for(var i = 0; i < this.state.list.length; ++i){
      if(this.state.start_time == null || this.state.list[i].release_time > this.state.start_time){
        if(this.state.end_time == null || this.state.list[i].release_time < this.state.end_time)
          // var newRow={
          //   key: this.state.list[i].key,
          //   title: this.state.list[i].title,
          //   release_time: this.state.list[i].release_time,
          //   read_cond: '学生：'+String(this.state.list[i].s_read_num)+'/'+String(this.state.list[i].s_tot_num),
            
          // }
          // newList = [...newList, newRow];
          newList = [...newList, this.state.list[i]];
      }
    }
    this.setState({
      datePickerRangeWarning: "",
      showList: newList,
    })
  }

  render(){
    return(
      <div className={styles.container}>
        <div className="search">
          <Search
            style={{ width: 512 }}
            placeholder="输入关键字"
            onSearch={(value) => console.log(value)}
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
          <Table columns={this.columns} dataSource={this.state.showList} />
        </div>
      </div>
    )
  }
}