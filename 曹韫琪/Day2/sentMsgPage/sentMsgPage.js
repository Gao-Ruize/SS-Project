// pages/sentMsgPage/sentMsgPage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0,
    msgItems:[{id:"1",title:"选课时间通知",date:"2020"},{id:"2",title:"退课时间通知",date:"2020"}],
    searchValue:"",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  onChange(event) {
    this.setData({ active: event.detail });
  },
  searchChange(event) {
    this.setData({searchValue: event.detail});
    let checkvalue = this.data.searchValue;
    console.log("change!");
    console.log(checkvalue);
  },
  onSearch(){
    console.log("search!");
  },
  checkDetails(event) {
    let msgId = event.currentTarget.dataset.id;
    console.log(msgId);
    wx.navigateTo({
      url: '../sentMsgDetailPage/sentMsgDetailPage?msgId=' + msgId,
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