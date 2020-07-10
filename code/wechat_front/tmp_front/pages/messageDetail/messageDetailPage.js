// pages/messageDetail/messageDetailPage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeNames: ['1'],
    //从后端msgForm得出
    title:"选课通知",
    date:"2020-07-01",
    content:"记得选课",
    sender_name:"jwc",
    msg_type:"jwc",
    msg_id:0,
    //从localstorage或者路由参数读出
    //用于更新阅读情况
    //type 为 tutor 时只需要msg_id, type 为 jwc 时需要 msg_id 与 receiver_id,
    //receiver_type
    receiver_id:"",
    receiver_type:"",
  },
  onCollChange(event) {
    this.setData({
      activeNames: event.detail,
    });
  },
  onCommit() {
    let type = this.data.type;
    if(type == "jwc") {
      //发送receiver_id, receiver_type, msg_id到后端，修改状态
    } else if(type == "tutor") {
      //发送msg_id至后端修改状态
    }
    //跳转至主页面
    console.log("finish");
    return;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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