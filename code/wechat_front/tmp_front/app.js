//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    // 登录
    wx.login({
           success: res => {
             console.log(res.code); // 先login得到code
             if (res.code) {
               // url为后端地址
               var url = "http://localhost:8443/api/user/login";
               wx.request({
                  url: url,
                  method: 'POST', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT 
                  data: res.code,
                  success: function (res) {
                    let token = res.data.token;
                    wx.setStorageSync('token', token);
                    let test = wx.getStorageSync('token');
                    console.log(test);
                    console.log(res.data);
                    let realId = res.data.realId;
                    console.log(realId);
                    let openId = res.data.openId;
                    wx.setStorageSync('openid', openId);
                    console.log("openid!");
                    console.log(openId);
                    wx.setStorageSync('realid', realId);
                    let type = res.data.type;
                    wx.setStorageSync('type', type);
                    console.log(type);
                    if(type == "S") { wx.navigateTo({url: '/pages/stuIndex/stuIndex',})}
                    else
                    if(type == "T") { wx.navigateTo({url: '/pages/tutorMsgFromJwcPage/tutorMsgFromJwcPage',})}
                    else
                    if(type == "err") {
                      wx.showToast({
                        title: '出错了',
                        icon:'none',
                        duration: 1000
                      });
                    }
                    else { 
                      wx.showToast({
                        title: "尚未绑定，请注册",
                        icon: 'none',
                        duration: 1500
                      })
                    }
                 }
               });
             } 
           }
         });
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null
  }
})