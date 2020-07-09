// pages/register/register.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    codeid: wx.getStorageSync('codeid'),
    s_id: "",
    t_id: "",
    value: "",
    redio: 1
  },
  onChoose(event){
    this.setData({redio: event.detail})
  },
  onInput(event){
    this.setData({value: event.detail})
  },
  onCommit(event){
    //向后端发消息，若后端返回成功，则继续，若返回errMsg，则报错，让其重新输入或联系管理员
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