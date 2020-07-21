import React from 'react';

import styles from './index.less';

import { Card, Button, Table } from 'antd';
import {
    LeftOutlined
} from '@ant-design/icons';

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

export default class StudentList extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            msgDetail: {
                title: "中期检查通知",
                content: "nightwatch 需要安装配置 selenium，selenium-server需要安装jdk（Java Development Kit） \n \
                cypress 安装方便，测试写法和单元测试一致，只支持 Chrome 类浏览器，有支持其他浏览器的计划\
                testcafe 安装方便，测试写法与之前的单元测试差异比较大，编写测试用例时只能选择到可见的元素\
                katalon 不是测试框架，是 IDE ，比较重，并且不开源，测试用例不是用 js 编写但是可以通过 Chrome 插件录测试用例",
                release_time: 1582992000,
                phase: 1,
                unread_S_list: [
                    { id: 2, studentid: "123456789", uid: "xxx", studentname: "张三" },
                    { id: 4, studentid: "314258697", uid: "xxx", studentname: "李四" },
                    { id: 6, studentid: "987654321", uid: "xxx", studentname: "王八" },
                ],
                unread_T_list: [
                    { id: 15, tutorid: "12345", uid: "xxx", tutorname: "Tutor1" },
                    { id: 27, tutorid: "31425", uid: "xxx", tutorname: "Tutor2" },
                ]
            }
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
        ]
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
                            title={this.state.msgDetail.title} 
                            style={{ width: "100%" }}
                            // headStyle={{}}
                            bodyStyle={{padding: 32}}
                        >
                            <h4>{'发布时间： '+new Date(this.state.msgDetail.release_time*1000).toLocaleDateString()}</h4>
                            <p>{this.state.msgDetail.content}</p>
                        </Card>
                    </Card.Grid>
                    <Card.Grid style={gridStyleLists} hoverable={false}>
                        未读学生名单
                        <Table columns={this.s_table} dataSource={this.state.msgDetail.unread_S_list} />
                    </Card.Grid>
                    <Card.Grid style={gridStyleLists} hoverable={false}>
                        未读导师名单
                        <Table columns={this.tut_table} dataSource={this.state.msgDetail.unread_T_list} />
                    </Card.Grid>
                </Card>
            </div>
        )
    }
}

