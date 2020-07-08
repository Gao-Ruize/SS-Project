// pages/message/message.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    students: [],
    show: false,
    minHour: 10,
    maxHour: 20,
    minDate: new Date().getTime(),
    maxDate: new Date(2021, 6, 1).getTime(),
    currentDate: new Date().getTime(),
    timeSpanStr: new Date().getFullYear()+ "-" + (new Date().getMonth()+1) + "-" + new Date().getDate() + " " + new Date().getHours() +":" + new Date().getMinutes()
  },

  // 关闭遮罩层，并且保存时间
  onTimeConfirm(event) {
    var tmpDate = new Date(event.detail);
    var tmpStr = tmpDate.getFullYear()+"-"+(tmpDate.getMonth()+1)+"-"+tmpDate.getDate()+" "+tmpDate.getHours()+":"+tmpDate.getMinutes();
    this.setData({
      currentDate: event.detail,
      timeSpanStr: tmpStr,
      show: false,
    });
  },
  // 打开timepicker遮罩层
  bindOpenTime(event){
    this.setData({
      show: true,
    });
  },
  // 发消息的确认，传给后端
  bindQuit(event){
    wx.navigateBack({
      complete: (res) => {},
    })
  },
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
    this.setData({students: wx.getStorageSync('SendMessageTo')});
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