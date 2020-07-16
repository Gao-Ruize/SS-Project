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
    msgId: 0
  },
  onCollChange(event) {
    this.setData({
      activeNames: event.detail,
    });
  },
  onCommit() {
    let realid = wx.getStorageSync('realid');
    let userType = wx.getStorageSync('type');
    let senderType = this.data.senderType;
    let msgId = this.data.msgId;
    console.log(realid);
    console.log(userType);
    console.log(senderType); 
    console.log(msgId);
    let baseurl = "http://localhost:8443/api/user/readmsg";
    wx.request({
      url: baseurl,
      method:'POST',
      data: {
        userId: realid,
        msgId: msgId,
        type: senderType,
        userType: userType
      },
      success(res) {
        if(res.data.code == 200) {
          wx.showToast({
            title: '确认成功',
            icon:'none',
            duration: 1500
          });
        }
      }
    });
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
    console.log("detail page");
    console.log(msgId);
    console.log(type);
    let baseurl = 'http://localhost:8443/api/user/msgdetail/'
      + msgId + '/' + type;
    wx.request({
      url: baseurl,
      method: 'GET',
      success (res) {
        console.log(res.data);
        that.setData({
          infoForm: res.data
        });
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})