import React from 'react';
import { shallow, mount, render } from 'enzyme';
import MsgList from "./index.jsx";

import { configure } from 'enzyme';
import Adapter from 'enzyme-adapter-react-16';

configure({ adapter: new Adapter() });

test('test',()=>{
    var date = new Date();
    var ret = (Date.parse(date) / 1000);

    // const wrapper = shallow(<MsgList />);
    // expect(wrapper.onChangeStartTime(date, 'dateString')).toBe(ret);

    const msglist = new MsgList;

    var exp_list = [
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
    expect(msglist.state.showList).toEqual(exp_list);

    expect(msglist.onChangeStartTime(date, 'dateString')).toBe(ret);
    expect(msglist.onChangeEndTime(date, 'dateString')).toBe(ret);

    // exp_list = [];
    // expect(msglist.state.showList).toEqual(exp_list);

    expect(msglist.onChangeStartTime(null, "dateString")).toBe(null);
    expect(msglist.onChangeEndTime(null, "dateString")).toBe(null);


})
