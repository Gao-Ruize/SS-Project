//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World!',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    show: false,
    date: '',
    active: 0, // 底边导航栏指向
    minDate: new Date(2018, 0, 1).getTime(),
    maxDate: new Date(2022, 0, 31).getTime(),    
    allMsgs:[],
  },
  // 
  onChange (event) {
    if(event.detail == 1)
    {
      wx.redirectTo({
        url: '../stuMsgFromIns/stuMsgFromInsPage',
      })
    }
    if(event.detail == 2) 
    {
      wx.redirectTo({
        url: '../stuChooseTutor/stuChooseTutor',
      })
    }
  },
  onDisplay() {
    this.setData({ show: true });
  },
  onClose() {
    this.setData({ show: false });
  },
  formatDate(date) {
    date = new Date(date);
    return `${date.getYear() + 1900}/${date.getMonth() + 1}/${date.getDate()}`;
  },
  onConfirm(event) {
    const [start, end] = event.detail;
    this.setData({
      show: false,
      date: `${this.formatDate(start)} - ${this.formatDate(end)}`,
    });
    console.log(this.data.date);
  },
  showDetails(event) {
    let type = 'jwc';
    let msgid = event.currentTarget.dataset.id;
    console.log(msgid);
    wx.navigateTo({
      url: '/pages/messageDetail/messageDetailPage?msgId=' + msgid + 
      '&type=' + type,
    });
  },
  // 相应搜索栏
  onSearch(value){
    console.log(value.detail);
  },

  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    let that = this;
    let type = wx.getStorageSync('type');
    let realid = wx.getStorageSync('realid');
    console.log("get msgs");
    console.log(type);
    console.log(realid);
    let baseurl = "http://localhost:8443/api/user/typejwcmsg/" 
      + type + "/" + realid;
    wx.request({
      url: baseurl,
      method: 'GET',
      success (res) {
        that.setData({
          allMsgs: res.data,
        });
        console.log(res.data);
      }
    })
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse){
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function(e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
