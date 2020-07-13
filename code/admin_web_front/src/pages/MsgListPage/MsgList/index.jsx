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
    release_time: '2020-4-1',
  },
  {
    key: '2',
    title: '毕设开题提交入口即将关闭',
    release_time: '2020-4-14',
  },
  {
    key: '3',
    title: '中期检查通知',
    release_time: '2020-6-29',
  },
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
      start_time: null,
      end_time: null,
    }
  }

  onChangeStartTime=(date, dateString)=>{
    console.log(date, dateString);
    console.log(Date.parse(date));
    var timestamp = Date.parse(date);
    // this.setState({
    //   start_time: date,
    // })
    this.state.start_time = date;
    this.checkTime()
  }
  onChangeEndTime=(date, dateString)=>{
    console.log(date, dateString);
    console.log(Date.parse(date));
    var timestamp = Date.parse(date);
    // this.setState({
    //   end_time: date,
    // })
    this.state.end_time = date;
    this.checkTime()
  }

  checkTime=()=>{
    console.log("check time:");
    console.log("start_time: " + this.state.start_time);
    console.log("end_time: " + this.state.end_time);
    if(this.state.start_time == null || this.state.end_time == null){
        console.log("time: null");
        return;
    }

    if(this.state.start_time >= this.state.end_time){
        console.log("invalid time range");
        //alert("invalid time range!");
        return;
    }
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
          <br />
          <br />
        </div>
        <div id="components-table-demo-basic">
          <Table columns={columns} dataSource={data} />
        </div>
      </div>
    )
  }
}