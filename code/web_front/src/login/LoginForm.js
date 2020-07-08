import React from 'react';
import {Link} from 'react-router-dom';
import { Form, Input, Button, Checkbox } from 'antd';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import '../css/LoginForm.css';
import 'antd/dist/antd.css';
import {apiUrl} from '../urlconfig';

const LOGIN_URL = apiUrl + "/login";

export default class LoginForm extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: ""
        }
    }


    onFinish = values => {
        console.log('Received values of form: ', values);
    };


    render() {
        return (
            <Form
                name="normal_login"
                className="login-form"
                initialValues={{
                    remember: true,
                }}
                onFinish={this.onFinish}
            >
                <Form.Item
                    name="username"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your Username!',
                        },
                    ]}
                >
                    <Input prefix={<UserOutlined className="site-form-item-icon" />}
                           placeholder="Username"
                    />
                </Form.Item>
                <Form.Item
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your Password!',
                        },
                    ]}
                >
                    <Input
                        prefix={<LockOutlined className="site-form-item-icon" />}
                        type="password"
                        placeholder="Password"
                    />
                </Form.Item>

                <Form.Item>
                    <Button type="primary" htmlType="submit" className="login-form-button">
                        Log in
                    </Button>
                </Form.Item>
            </Form>
        )
    }
}
