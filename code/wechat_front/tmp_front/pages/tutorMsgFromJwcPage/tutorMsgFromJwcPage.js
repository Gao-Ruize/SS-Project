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

    // show: false,
    // minDate: 946656000000,
    // startTime: new Date().getTime(),
    // endTime: 962951868000,
    // formatter(type, value) {
    //   if (type === 'year') {
    //     return `${value}年`;
    //   } else if (type === 'month') {
    //     return `${value}月`;
    //   }
    //   //console.log("formatter:"+value);
    //   return value;
    // },
    
    messages:[
      {title:"title1", detail:"detail1", read:false, messageId:1},
      {title:"title2", detail:"detail2", read:false, messageId:2},
      {title:"title3", detail:"detail3", read:true, messageId:3},
      {title:"title4", detail:"detail4", read:true, messageId:4},
      {title:"title5", detail:"detail5", read:true, messageId:5}
    ]
  },
  // 
  onChange (event) {
    if(event.detail == 1)
    {
      wx.navigateTo({
        url: '../tutorHistoryMsgPage/tutorHistoryMsgPage',
      })
    }
    if(event.detail == 2)
    {
      wx.navigateTo({
        url: '../tutorSendMsgPage/tutorSendMsgPage',
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


  // 相应搜索栏
  onSearch(value){
    console.log(value.detail);
  },

  // onInputStartDate(event) {
  //   console.log(event.detail);
  //   this.setData({
  //     startDate: event.detail,
  //   });
  // },

  // onInputEndDate(event) {
  //   console.log(event.detail);
  //   this.setData({
  //     endDate: event.detail,
  //   });
  // },

  // showPickTime(){
  //   this.setData({ show: true });
  // },

  // hidePickTime() {
  //   this.setData({ show: false });
  // },

  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
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
