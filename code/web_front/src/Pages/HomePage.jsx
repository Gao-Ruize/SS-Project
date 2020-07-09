import React from 'react';
import MenuNavi from '../Components/MenuNavi.jsx';

import { Affix, Button } from 'antd';
import { Layout } from 'antd';


const { Header, Footer, Sider, Content} = Layout;




export default class HomePage extends React.Component{



    render(){
        return(
            <div>
                <Layout>
                    <Layout>
                        <Sider width='256'>
                            <Affix style={{position: 'absolute', top: 0, left:0 }}>
                                <MenuNavi currentPage="home" />
                            </Affix>
                        </Sider>
                        <Layout>
                            <Header>
                                Header
                            </Header>
                            <Content>
                                Content
                            </Content>
                            <Footer>
                                Footer
                            </Footer>
                        </Layout>
                    </Layout>
                </Layout>
            </div>
        )
    }
}