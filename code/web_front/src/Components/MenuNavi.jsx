import React from 'react';
import { Menu } from 'antd';
import { BarsOutlined, UserOutlined, BankOutlined, ReadOutlined } from '@ant-design/icons';

const { SubMenu } = Menu;



export default class MenuNavi extends React.Component{
// constructor(props){
//     super(props);

//     this.state = {
//     editable: true,
//     }
//     if(global.config.role == "root"){
//     this.state.editable = false;
//     }
// }

    render(){
        return(
            <div>
                <Menu
                onClick={this.handleClick}
                style={{ width: 256, height: 1080 }}
                defaultSelectedKeys={[ this.props.currentPage ]}
                mode="inline"
                >
                <Menu.Item key="home"><BankOutlined />
                    <a href="#/">首页</a>
                </Menu.Item>

                <SubMenu
                    key="sort"
                    title={
                    <span>
                        <BarsOutlined />
                        <span>分类</span>
                    </span>
                    }
                >
                    <Menu.Item key="1">科幻世界</Menu.Item>
                    <Menu.Item key="2">文学名著</Menu.Item>
                    <Menu.Item key="3">现代文学</Menu.Item>
                    <Menu.Item key="4">历史书籍</Menu.Item>
                    <Menu.Item key="5">专业书籍</Menu.Item>
                    <Menu.Item key="6">其他书籍</Menu.Item>
                </SubMenu>

                <Menu.Item key="history"><ReadOutlined />历史</Menu.Item>
                
                <SubMenu
                    key="user"
                    title={
                    <span>
                        <UserOutlined />
                        <span>我的</span>
                    </span>
                    }
                >
                    <Menu.Item key="reg_log"><a href="#/reg_log">登录/注册</a></Menu.Item>
                    <Menu.Item key="cart"><a href="#/carts">购物车</a></Menu.Item>
                    <Menu.Item key="orders"><a href="#/view_orders">个人信息</a></Menu.Item>
                    <Menu.Item key="10">设置</Menu.Item>
                    
                </SubMenu>

                </Menu>
            </div>
        )
    }
}
