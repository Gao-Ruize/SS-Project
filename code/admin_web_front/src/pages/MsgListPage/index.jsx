import { PageHeaderWrapper } from '@ant-design/pro-layout';
import React, { useState, useEffect } from 'react';
import { Spin } from 'antd';
import styles from './index.less';
import MsgList from './MsgList';
import MsgDetail from './MsgDetail';

import { BrowserRouter as Router, HashRouter, Switch, Route, Link } from "react-router-dom"


export default () => {
  const [loading, setLoading] = useState(true);
  useEffect(() => {
    setTimeout(() => {
      setLoading(false);
    }, 3000);
  }, []);
  return (
    // <PageHeaderWrapper className={styles.main}>
    //   <MsgList />
    //   <div
    //     style={{
    //       paddingTop: 100,
    //       textAlign: 'center',
    //     }}
    //   >
    //     <Spin spinning={loading} size="large" />
    //   </div>
    // </PageHeaderWrapper>
    <HashRouter>
      <Switch>
        <Route exact path="/" component={MsgList} />
        <Route path="/msgdetail/:msgid" component={MsgDetail} />
      </Switch>
    </HashRouter>
  );
};
