import React from 'react';

import styles from './index.less';

import { Card, Button, Table } from 'antd';
import {
    LeftOutlined
} from '@ant-design/icons';

import $ from  'jquery';
import config from '../../../config.js';


const gridStyleContent = {
    width: '100%',
};
// const gridStyleDisplay = {
//     width: '50%',
//     textAlign: 'center',
// };
const gridStyleLists = {
    width: '50%',
}

var _this;

export default class StudentList extends React.Component {
    constructor(props) {
        super(props);
        _this = this;

        this.state = {
            // msgDetail: {
            //     title: "中期检查通知",
            //     content: "nightwatch 需要安装配置 selenium，selenium-server需要安装jdk（Java Development Kit） \n \
            //     cypress 安装方便，测试写法和单元测试一致，只支持 Chrome 类浏览器，有支持其他浏览器的计划\
            //     testcafe 安装方便，测试写法与之前的单元测试差异比较大，编写测试用例时只能选择到可见的元素\
            //     katalon 不是测试框架，是 IDE ，比较重，并且不开源，测试用例不是用 js 编写但是可以通过 Chrome 插件录测试用例",
            //     release_time: 1582992000,
            //     phase: 1,
            //     unread_S_list: [
            //         { key: 1, id: 2, studentid: "123456789", uid: "xxx", studentname: "张三" },
            //         { key: 2, id: 4, studentid: "314258697", uid: "xxx", studentname: "李四" },
            //         { key: 3, id: 6, studentid: "987654321", uid: "xxx", studentname: "王八" },
            //     ],
            //     unread_T_list: [
            //         { key: 1, id: 15, tutorid: "12345", uid: "xxx", tutorname: "Tutor1" },
            //         { key: 2, id: 27, tutorid: "31425", uid: "xxx", tutorname: "Tutor2" },
            //     ]
            // },
            jwcMsg_detail: {},
            unread_S_list: [],
            unread_T_list: [],
        };
        this.s_table = [
            {
                title: '姓名',
                dataIndex: 'studentname',
                key: 's_name',
            },
            {
                title: '学号',
                dataIndex: 'studentid',
                key: 's_id',
            }
        ];
        this.tut_table = [
            {
                title: '姓名',
                dataIndex: 'tutorname',
                key: 'tut_name',
            },
            {
                title: '工号',
                dataIndex: 'tutorid',
                key: 'tut_id'
            }
        ];

        console.log(this.props.match.params.msgid);
        this.acquireMsgDetail();
    }

    acquireMsgDetail() {
        $.ajax({
            type: "get",
            url: global.config.backendUrl + "/api/admin/jwcmsgdetail/"+this.props.match.params.msgid,
            contentType: "application/json;charset=utf-8;",
            dataType: "text",
            success: function (data) {
                var result = JSON.parse(data);
                console.log(result);

                var jwcMsg_detail = {
                    msgid: result.msgid,
                    title: result.title,
                    content: result.content,
                    release_time: result.release_time,
                }

                var S_list = [];
                for (var i = 0; i < result.unreadStudents.length; ++i) {
                    var newS = {
                        key: i,
                        studentid: result.unreadStudents[i].studentid,
                        studentname: result.unreadStudents[i].studentname,
                        uid: result.unreadStudents[i].uid,
                    }
                    S_list = [...S_list, newS];
                }

                var T_list = [];
                for (var i = 0; i < result.unreadTutors.length; ++i) {
                    var newT = {
                        key: i,
                        tutorid: result.unreadTutors[i].tutorid,
                        tutorname: result.unreadTutors[i].tutorname,
                        uid: result.unreadTutors[i].uid,
                    }
                    T_list = [...T_list, newT];
                }

                // console.log(jwcMsg_detail);
                // console.log(S_list);
                // console.log(T_list);

                _this.setState({
                    unread_S_list: S_list,
                    unread_T_list: T_list,
                    jwcMsg_detail: jwcMsg_detail,
                })
            },
            error: function (error) {
                console.log("acquire tutors error");
            }
        });
    }

    render() {
        return (
            <div className={styles.container}>
                <Card
                    title={
                        <a href='#/'>
                            <div><LeftOutlined />返回消息列表</div>
                        </a>
                    }
                    bordered={false}
                    style={{ width: '100%' }}
                >
                    {/* <p>Card content</p>
                    <p>Card content</p>
                    <p>Card content</p> */}
                    {/* <Card type="inner" title="Inner Card title"
                        style={{width:'48%', height:'80vh', display:'inline-block'}}>
                        Inner Card content
                    </Card>
                    &nbsp;
                    <Card type="inner"
                        style={{width:'48%', height:'75vh', display:'inline-block'}}>
                        Inner Card content
                    </Card> */}
                    <Card.Grid style={gridStyleContent} hoverable={false}>
                        <Card
                            type='inner'
                            title={this.state.jwcMsg_detail.title}
                            style={{ width: "100%" }}
                            // headStyle={{}}
                            bodyStyle={{ padding: 32 }}
                        >
                            <h4>{'发布时间： ' + new Date(this.state.jwcMsg_detail.releasetime * 1000).toLocaleDateString()}</h4>
                            <p>{this.state.jwcMsg_detail.content}</p>
                        </Card>
                    </Card.Grid>
                    <Card.Grid style={gridStyleLists} hoverable={false}>
                        未读学生名单
                        <Table columns={this.s_table} dataSource={this.state.unread_S_list} />
                    </Card.Grid>
                    <Card.Grid style={gridStyleLists} hoverable={false}>
                        未读导师名单
                        <Table columns={this.tut_table} dataSource={this.state.unread_T_list} />
                    </Card.Grid>
                </Card>
            </div>
        )
    }
}

