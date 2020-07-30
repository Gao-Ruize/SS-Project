//index.js
//获取应用实例
Page({
  data: {
    motto: 'Hello World!',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    show: false,
    date: '',
    active: 1, // 底边导航栏指向
    minDate: new Date(2018, 0, 1).getTime(),
    maxDate: new Date(2022, 0, 31).getTime(),    
    allMsgs:[],
    serachValue:'',
    showMsgs:[],
    jwcMsgCount: '',
    tutMsgCount: '',
  },
  app: getApp(),
  // 
  errCheck(res) {
    let errCheck = res.statusCode;
        if(errCheck == 500) {
          wx.showToast({
            title: '登陆超时，请重新登陆',
            icon: 'none'
          });  
          return 1;  
        }
        return 0;
  },
  setJwcCount () {
    let that = this;
    let ID = wx.getStorageSync('realid');
    let token = wx.getStorageSync('token');
    let baseUrl = "http://39.106.85.149:8080/api/stu/unreadjwcmsg/" + ID;
    wx.request({
      url: baseUrl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success(res) {
        if(that.errCheck(res)) {
          app.onLaunch();
          return;
        }
        that.setData({
          jwcMsgCount: res.data,
        })
      }
    })
  },
  setTutCount () {
    let that = this;
    let ID = wx.getStorageSync('realid');
    let token = wx.getStorageSync('token');
    let baseUrl = "http://39.106.85.149:8080/api/stu/unreadinsmsg/" + ID;
    wx.request({
      url: baseUrl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res) {
        if(that.errCheck(res)) {
          this.app.onLaunch();
          return;
        }
        that.setData({
          tutMsgCount: res.data,
        })
      }
    })
  },
  onChange (event) {
    if(event.detail == 0)
    {
      wx.redirectTo({
        url: '../stuMsgFromJwcPage/stuMsgFromJwcPage',
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
    return `${date.getYear() + 1900}-${date.getMonth() + 1}-${date.getDate()}`;
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
    let type = 'tutor';
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
  searchChange(event) {
    this.setData({
      searchValue: event.detail,
    })
  },
  onCancel () {
    let old = this.data.allMsgs;
    this.setData({
      showMsgs: old,
      searchValue: ''
    })
  },
  onSearch () {
    //过滤allMsgs得到showMsgs进行展示
    let searchKey = this.data.searchValue;
    let arrRec = [];
    for(var i = 0; i < this.data.allMsgs.length; ++i) {
      let item = this.data.allMsgs[i];
      if(item.title.includes(searchKey) || 
         item.releasetime.includes(searchKey))
           arrRec.push(item);
    }
    this.setData({
      showMsgs: arrRec
    });
  },
  onLoad: function () {
    this.setJwcCount();
    this.setTutCount();
    let that = this;
    let type = wx.getStorageSync('type');
    let realid = wx.getStorageSync('realid');
    console.log("get msgs");
    console.log(type);
    console.log(realid);
    let token = wx.getStorageSync('token');
    let baseurl = "http://39.106.85.149:8080/api/stu/insmsg/" 
      + "/" + realid;
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res) {
        if(that.errCheck(res)) {
          this.app.onLaunch();
          return;
        }
        that.setData({
          allMsgs: res.data,
          showMsgs: res.data
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
