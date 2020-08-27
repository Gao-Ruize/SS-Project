// pages/sentMsgPage/sentMsgPage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 1,
    //全部信息
    msgItems:[],
    searchValue:"",
    //页面展示信息，即搜索过滤后的信息
    showItems:[],
    jwcMsgCount: '',
    value1: 0,
    option1: [
      { text: '全部通知', value: 0 },
      { text: '导师选择通知', value: 1 },
      { text: '开题报告通知', value: 2 },
      { text: '中期检查通知', value: 3 },
      { text: '答辩通知', value: 4 },
      { text: '归档通知', value: 5 },
    ],
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setJwcCount();
    let realId = wx.getStorageSync('realid');
    let token = wx.getStorageSync('token');
    //let baseurl = "http://39.106.85.149:8080/api/tut/insmsgs/" + realId;
    let baseurl = this.app.baseUrl + "/api/tut/insmsgs/" + realId;
    let that = this;
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success(res) {
        that.onLoad_suc(res);
      }
    });
  },
  setJwcCount() {
    let ID = wx.getStorageSync('realid');
    let token = wx.getStorageSync('token');
    // let baseurl = "http://39.106.85.149:8080/api/tut/jwcmsgcount/" + ID;
    let baseurl = this.app.baseUrl + "/api/tut/jwcmsgcount/" + ID;
    let that = this;
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res) {
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
      jwcMsgCount: res.data
    })
  },
  onLoad_suc(res){
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    // console.log(res.data);
    this.setData({
      msgItems: res.data
    });
    this.setData({
      showItems: res.data
    });
  },
  onChange(event) {
    if(event.detail == 0)
    {
      wx.redirectTo({
        url: '../tutorMsgFromJwcPage/tutorMsgFromJwcPage',
      })
    }
    if(event.detail == 2)
    {
      wx.redirectTo({
        url: '../tutorSendMsgPage/tutorSendMsgPage',
      })
    }
  },
  searchChange(event) {
    this.setData({searchValue: event.detail});
    // let checkvalue = this.data.searchValue;
    // console.log("change!");
    // console.log(checkvalue);
  },
  onSearch(){
    // console.log("search!");
    let tmpArr = [];
    let value = this.data.searchValue;
    for(var i=0; i < this.data.msgItems.length; i++){
      let item = this.data.msgItems[i];
      if(item.title.includes(value) 
         || item.releasetime.includes(value)) {
        tmpArr.push(item);
      }
    };
    this.setData({showItems: tmpArr});
  },
  onCancel(){
    let tmpArr = this.data.msgItems;
    this.setData({showItems: tmpArr});
  },
  checkDetails(event) {
    let msgId = event.currentTarget.dataset.id;
    let title = event.currentTarget.dataset.title;
    wx.setStorageSync('msgTitle', title);
    // console.log(title);
    // console.log(msgId);
    wx.navigateTo({
      url: '../tutorHistoryMsgDetailPage/tutorHistoryMsgDetailPage?msgId=' + msgId
    })
  },
  changeDropItem(e) {
    let check = e.detail;
    this.filter(check);
  },

  filter(value) {
    if(value == 0) {
      this.setData({
        showItems: this.data.msgItems
      });
      return;
    }
    let phase = value - 1;
    let arrRec = [];
      for(var i = 0; i < this.data.msgItems.length; ++i) {
        let item = this.data.msgItems[i];
        if(item.phase == phase)
          arrRec.push(item);
      }
      this.setData({
        showItems: arrRec
      });
      return;
  }
})