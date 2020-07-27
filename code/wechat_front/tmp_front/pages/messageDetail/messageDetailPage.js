// pages/messageDetail/messageDetailPage.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    activeNames: ['1'],
    //从后端msgForm得出
    title:'',
    date:'',
    content:'',
    sender_name:'',
    msg_type:'',
    msg_id:0,
    senderType:'',
    //从localstorage或者路由参数读出
    //用于更新阅读情况
    //type 为 tutor 时只需要msg_id, type 为 jwc 时需要 msg_id 与 receiver_id,
    //receiver_type
    receiver_id:"",
    receiver_type:"",
    infoForm:[],
    msgId: 0,
    userType: wx.getStorageSync('type')
  },
  app: getApp(),
  onCollChange(event) {
    this.setData({
      activeNames: event.detail,
    });
  },
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
  onCommit() {
    let realid = wx.getStorageSync('realid');
    let userType = wx.getStorageSync('type');
    let senderType = this.data.senderType;
    let msgId = this.data.msgId;
    let that = this;
    // console.log(realid);
    // console.log(userType);
    // console.log(senderType); 
    // console.log(msgId);
    let baseurl = "http://localhost:8443/api/user/readmsg";
    let token = wx.getStorageSync('token');
    wx.request({
      url: baseurl,
      method:'POST',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      data: {
        userId: realid,
        msgId: msgId,
        type: senderType,
        userType: userType
      },
      success(res) {
        that.onCommit_suc(res);
      }
    });
  },
  onCommit_suc(res){
    if(this.errCheck(res)) {
      app.onLaunch();
      return;
    }
    if(res.data.code == 200) {
      let userType = this.data.userType;
    let senderType = this.data.senderType;
    // console.log(userType);
    // console.log(senderType);
    if(userType == 'S') {
      if(senderType == 'jwc') 
        wx.navigateTo({
          url: '/pages/stuMsgFromJwcPage/stuMsgFromJwcPage',
        });
      else 
        wx.navigateTo({
          url: '/pages/stuMsgFromIns/stuMsgFromInsPage',
        })
    } else {
      wx.navigateTo({
        url: '/pages/tutorMsgFromJwcPage/tutorMsgFromJwcPage',
      })
    };
      wx.showToast({
        title: '确认成功',
        icon:'none',
        duration: 1500
      });
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    let msgId = options.msgId;
    let type = options.type;
    this.setData({
      senderType: type,
      msgId: msgId,
    });
    // console.log("detail page");
    // console.log(msgId);
    // console.log(type);
    let baseurl = 'http://localhost:8443/api/user/msgdetail/'
      + msgId + '/' + type;
    let token = wx.getStorageSync('token');
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res) {
        // console.log(res.data);
        that.onLoad_suc(res)
      }
    })
  },
  onLoad_suc(res){
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    this.setData({
      infoForm: res.data
    });
  }
})