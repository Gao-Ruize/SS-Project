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
    dataIndex: 'title',
    key: 'title',
    render: (text) => <a href="">{text}</a>,
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
    title: '毕设开题提交通知',
    release_time: 1582992000,
  },
  {
    key: '2',
    title: '毕设开题提交入口即将关闭',
    release_time: 1584115200,
  },
  {
    key: '3',
    title: '中期检查通知',
    release_time: 1590681600,
  },
  {
    key: '4',
    title: '阶段4',
    release_time: 1594742400,
  }
];
// export default () => (
//   <div className={styles.container}>
//     <div className="search">
//       <Search
//         style={{ width: 512 }}
//         placeholder="输入关键字"
//         onSearch={(value) => console.log(value)}
//         enterButton
//       />
//       <br />
//       <br />
//     </div>
//     <div className="dataPicker">
//       根据发布时间筛选：
//       <br />
//       <DatePicker onChange={onChangeStartTime} placeholder="选择起始时间" />
//       {/* <br />
//       <br /> */}
//       &nbsp;&nbsp; ~ &nbsp; &nbsp;
//       <DatePicker onChange={onChangeEndTime} placeholder="选择截止时间" />
//       <br />
//       <br />
//     </div>
//     <div id="components-table-demo-basic">
//       <Table columns={columns} dataSource={data} />
//     </div>
//   </div>
// );


export default class MsgList extends React.Component{
  constructor(props){
    super(props);
    
    this.state = {
      list: data,
      showList: data,
      start_time: null,
      end_time: null,
    }
  }

  onChangeStartTime=(date, dateString)=>{
    if(date == null){
      this.state.start_time = null;
    }else{
      this.state.start_time = Math.round(Date.parse(date) / 1000);
    }
    this.checkTime()
  }
  onChangeEndTime=(date, dateString)=>{
    if(date == null){
      this.state.end_time = null;
    }else{
      this.state.end_time = Math.round(Date.parse(date) / 1000);
    }

    this.checkTime()
  }

  checkTime=()=>{
    // console.log("in check time:");
    // console.log("    start_time: " + this.state.start_time);
    // console.log("    end_time: " + this.state.end_time);
    var newList = [];
    if(this.state.start_time != null && this.state.end_time != null){
      if(this.state.start_time >= this.state.end_time){
        document.getElementById("datePickerRangeWarning").innerHTML = "Invalid Time Range!";
        this.setState({
          showList: newList,
        })
        return;
      }
    }
    document.getElementById("datePickerRangeWarning").innerHTML = "";

    for(var i = 0; i < this.state.list.length; ++i){
      if(this.state.start_time == null || this.state.list[i].release_time > this.state.start_time){
        if(this.state.end_time == null || this.state.list[i].release_time < this.state.end_time)
          newList = [...newList, this.state.list[i]];
      }
    }
    this.setState({
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
          <p id="datePickerRangeWarning" style={{ display:"inline", color:"orange"}}></p>
          <br />
          <br />
        </div>
        <div id="components-table-demo-basic">
          <Table columns={columns} dataSource={this.state.showList} />
        </div>
      </div>
    )
  }
}