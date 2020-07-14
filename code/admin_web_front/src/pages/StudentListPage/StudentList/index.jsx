import React from 'react';
import { Table, Tag } from 'antd';
import styles from './index.less';

import { Input } from 'antd';
import { AudioOutlined } from '@ant-design/icons';

const { Search } = Input;

const columns = [
  {
    title: '姓名',
    dataIndex: 's_name',
    key: 's_name',
    render: (text) => <a>{text}</a>,
  },
  {
    title: '学号',
    dataIndex: 's_ID',
    key: 's_ID',
  },
  {
    title: '专业',
    dataIndex: 'dept_name',
    key: 'dept_name',
  },
  {
    title: '绑定微信号',
    dataIndex: 'openID',
    key: 'openID',
  },
  {
    title: '操作',
    key: 'action',
    render: (text, record) => (
      <span>
        <a
          style={{
            marginRight: 16,
          }}
        >
          解除绑定： {record.s_name}
        </a>
      </span>
    ),
  },
];
const data = [
  {
    key: '1',
    s_name: '张三',
    s_ID: '123456789',
    dept_name: '软件工程',
    openID: 'abcde',
  },
  {
    key: '2',
    s_name: '李四',
    s_ID: '987654321',
    dept_name: '机械工程',
    openID: 'edksj',
  },
  {
    key: '3',
    s_name: '王八',
    s_ID: '132457689',
    dept_name: '软件工程',
    openID: 'dkwii',
  },
  {
    key: '4',
    s_name: '茄子',
    s_ID: '796813245',
    dept_name: '电子竞技',
    openID: 'wdnmd',
  },
  {
    key: '5',
    s_name: '李华',
    s_ID: '314253647',
    dept_name: '软件工程',
    openID: 'krgbs',
  },
];

// export default () => (
//   <div className={styles.container}>
//     <div className="search">
//       <Search
//         style={{ width: 512 }}
//         placeholder="输入要查询的姓名或学号"
//         onSearch={(value) => console.log(value)}
//         enterButton
//       />
//       <br />
//       <br />
//     </div>
//     <div id="components-table-demo-basic">
//       <Table columns={columns} dataSource={data} />
//     </div>
//   </div>
// );

export default class StudentList extends React.Component{
  constructor(props){
    super(props);
    
    this.state = {
      list: data,
    }
  }

  acquireStudents(){
    $.ajax({
      type: "get",
      url: global.config.backendUrl+"/api/user/???",
      contentType: "application/json;charset=utf-8;",
      dataType: "text",
      success:function(data) {
          var result = JSON.parse(data);
          var s_list = [];
          for (var i = 0; i < result.length; ++i) {
              var newStudent={
                  key: i,
                  s_name: result[i].s_name,
                  s_ID: result[i].s_ID,
                  dept_name: result[i].dept_name,
                  openID: result[i].uid,
              }
              s_list = [...s_list, newStudent];
          }
          _this.setState({
              list: s_list,
          })
      },
      error:function(error) {
          console.log("acquire students error");
      }
    });
  }

  render(){
    return(
      <div className={styles.container}>
        <div className="search">
          <Search
            style={{ width: 512 }}
            placeholder="输入要查询的姓名或学号"
            onSearch={(value) => console.log(value)}
            enterButton
          />
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

