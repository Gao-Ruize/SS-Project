//index.js
//获取应用实例
Page({
  data: {
    motto: 'Hello World!',
    show: false,
    date: '',
    active: 0, // 底边导航栏指向
    minDate: new Date(2018, 0, 1).getTime(),
    maxDate: new Date(2022, 0, 31).getTime(),    
    allMsgs:[],
    showMsgs: [],
    searchValue:'',
    jwcMsgCount: '',
    tutMsgCount: '',
  }, 
  app: getApp(),
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
    let baseUrl = "http://localhost:8443/api/stu/unreadjwcmsg/" + ID;
    wx.request({
      url: baseUrl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success(res) {
        that.setJwcCount_suc(res)
      }
    })
  },
  setJwcCount_suc(res){
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    this.setData({
      jwcMsgCount: res.data,
    })
  }
  ,
  setTutCount () {
    let that = this;
    let ID = wx.getStorageSync('realid');
    let token = wx.getStorageSync('token');
    let baseUrl = "http://localhost:8443/api/stu/unreadinsmsg/" + ID;
    wx.request({
      url: baseUrl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res) {
        that.setTutCount_suc(res)
        // console.log(res);
      }
    })
  },
  setTutCount_suc(res){
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    this.setData({
      tutMsgCount: res.data,
    });
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
    // console.log(this.data.date);
  },
  showDetails(event) {
    let type = 'jwc';
    let msgid = event.currentTarget.dataset.id;
    // console.log(msgid);
    wx.navigateTo({
      url: '/pages/messageDetail/messageDetailPage?msgId=' + msgid + 
      '&type=' + type,
    });
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
  onLoad: function () {
    this.setJwcCount();
    this.setTutCount();
    let that = this;
    let type = wx.getStorageSync('type');
    let realid = wx.getStorageSync('realid');
    // console.log("get msgs");
    // console.log(type);
    // console.log(realid);
    let token = wx.getStorageSync('token');
    let baseurl = "http://localhost:8443/api/user/typejwcmsg/" 
      + type + "/" + realid;
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res) {
        that.onLoad_suc(res)
        // console.log(res.data);
      }
    })
  },
  onLoad_suc(res){
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    this.setData({
      allMsgs: res.data,
      showMsgs: res.data,
    });
  }
})
