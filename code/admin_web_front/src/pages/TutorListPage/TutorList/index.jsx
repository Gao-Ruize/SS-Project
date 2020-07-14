import React from 'react';
import { Table, Tag } from 'antd';
import styles from './index.less';

import $ from  'jquery';

import { Input } from 'antd';
import { AudioOutlined } from '@ant-design/icons';

const { Search } = Input;

const columns = [
  {
    title: '姓名',
    dataIndex: 'tut_name',
    key: 'tut_name',
    render: (text) => <a>{text}</a>,
  },
  {
    title: '工号',
    dataIndex: 'tut_ID',
    key: 'tut_ID',
  },
  {
    title: '院系',
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
          解除绑定： {record.tut_name}
        </a>
      </span>
    ),
  },
];
const data = [
  {
    key: '1',
    tut_name: 'John Brown',
    tut_ID: '13245',
    dept_name: '软件学院',
    openID: 'akgoe',
  },
  {
    key: '2',
    tut_name: 'Jim Green',
    tut_ID: '31425',
    dept_name: '机械与动力学院',
    openID: 'kgoes',
  },
  {
    key: '3',
    tut_name: 'Joe Black',
    tut_ID: '96385',
    dept_name: '软件学院',
    openID: 'eijaf',
  },
];

// export default () => (
//   <div className={styles.container}>
//     <div className="search">
//       <Search
//         style={{ width: 512 }}
//         placeholder="输入要查询的姓名或工号"
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


export default class TutorList extends React.Component{
  constructor(props){
    super(props);
    
    this.state = {
      list: data,
    }
  }

  acquireTutors(){
    $.ajax({
      type: "get",
      url: global.config.backendUrl+"/api/user/tutors",
      contentType: "application/json;charset=utf-8;",
      dataType: "text",
      success:function(data) {
          var result = JSON.parse(data);
          var tut_list = [];
          for (var i = 0; i < result.length; ++i) {
              var newTutor={
                  key: i,
                  tut_name: result[i].tutorname,
                  tut_ID: result[i].tutorid,
                  openID: result[i].uid,
              }
              tut_list = [...tut_list, newTutor];
          }
          _this.setState({
              list: tut_list,
          })
      },
      error:function(error) {
          console.log("acquire tutors error");
      }
    });
  }

  render(){
    return(
      <div className={styles.container}>
        <div className="search">
          <Search
            style={{ width: 512 }}
            placeholder="输入要查询的姓名或工号"
            onSearch={(value) => console.log(value)}
            enterButton
          />
          <br />
          <br />
        </div>
        <div id="components-table-demo-basic">
          <Table columns={columns} dataSource={this.state.list} />
        </div>
      </div>
    )
  }
}