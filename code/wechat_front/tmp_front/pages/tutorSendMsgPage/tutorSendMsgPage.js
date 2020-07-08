// pages/qunfaMessage/qunfaMessage.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    list:[
      {
        id:0,
        name:"学生A",
        value:"学生A"
      },
      {
        id:1,
        name:"学生B",
        value:"学生B"
      },
      {
        id:2,
        name:"学生C",
        value:"学生C"
      }
    ],
    result:[],
    active: 2,
  },
  onChangeCheck(event) {
    this.setData({
      result: event.detail,
    });
  },
  onChange(event) {
    if(event.detail == 1)
    {
      wx.navigateTo({
        url: '../tutorHistoryMsgPage/tutorHistoryMsgPage',
      })
    }
    if(event.detail == 0)
    {
      wx.navigateTo({
        url: '../tutorMsgFromJwcPage/tutorMsgFromJwcPage',
      })
    }
  },
  changetomessage(event){
    wx.setStorageSync('SendMessageTo', this.data.result);
    wx.navigateTo({
      url: '../tutorSendMsgDetailPage/tutorSendMsgDetailPage',
    })
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