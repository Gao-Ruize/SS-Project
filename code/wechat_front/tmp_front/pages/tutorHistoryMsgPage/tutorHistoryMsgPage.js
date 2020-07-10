// pages/sentMsgPage/sentMsgPage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 1,
    //全部信息
    msgItems:[{id:"1",title:"选课时间通知",date:"2020"},{id:"2",title:"退课时间通知",date:"2020"}],
    searchValue:"",
    //页面展示信息，即搜索过滤后的信息
    showItems:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let tmp = this.data.msgItems;
    this.setData({showItems: tmp});
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
    let checkvalue = this.data.searchValue;
    console.log("change!");
    console.log(checkvalue);
  },
  onSearch(){
    console.log("search!");
    let tmpArr = [];
    let value = this.data.searchValue;
    this.data.msgItems.forEach(function(item,index) {
      if(item.title.includes(value) 
         || item.date.includes(value)) {
        tmpArr.push(item);
      }
    });
    this.setData({showItems: tmpArr});
  },
  onCancel(){
    let tmpArr = this.data.msgItems;
    this.setData({showItems: tmpArr});
  },
  checkDetails(event) {
    let msgId = event.currentTarget.dataset.id;
    console.log(msgId);
    wx.navigateTo({
      url: '../tutorHistoryMsgDetailPage/tutorHistoryMsgDetailPage?msgId=' + msgId,
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