// pages/message/message.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    studentsname: [],
    studentsId: [],
    show: false,
    minHour: 10,
    maxHour: 20,
    minDate: new Date().getTime(),
    maxDate: new Date(2021, 6, 1).getTime(),
    currentDate: new Date().getTime(),
    timeSpanStr: new Date().getFullYear()+ "-" + (new Date().getMonth()+1) + "-" + new Date().getDate() + " " + new Date().getHours() +":" + new Date().getMinutes(),
    titlemsg: "",
    contentmsg: ""
  },
  // 记录文本框里的信息，保存在titlemsg里
  onTitleChange(event){
    console.log(event.detail);
    this.setData({titlemsg: event.detail});
  },
  // 记录文本框里的信息，保存在contentmsg里
  bindTextAreaBlur(event){
    var result = event.detail.value;
    console.log(result);
    this.setData({contentmsg: result});
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
    // 此处补充传向后端的代码数据
    var send = {
      title: this.data.titlemsg,
      content: this.data.contentmsg,
      time: this.data.currentDate,
      tutorId: wx.getStorageSync('realid'),
      toIds: wx.getStorageSync('SendMessageToStudentId'),
    }
    var url = "http://localhost:8443/api/tut/sendmsg";
    wx.request({
      url: url,
      method: 'POST',
      data: send,
      success: function (res) {
        if(res.data.code == 200)
        {
          wx.showToast({
            title: '发送成功',
            icon: 'success',
            duration: 2000
          })
        }
        if(res.data.code == 400)
        {
          wx.showToast({
            title: '发送失败，请联系管理员',
            icon: 'none',
            duration: 2000
          })
        }
      },
    })
    wx.navigateBack({
      complete: (res) => {},
    })
  },
  onLoad: function (options) {
    var studentsname = wx.getStorageSync('SendMessageToStudentname');
    var studentsId = wx.getStorageSync('SendMessageToStudentId');
    this.setData({studentsname: studentsname, studentsId: studentsId});
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
    var studentsname = wx.getStorageSync('SendMessageToStudentname');
    var studentsId = wx.getStorageSync('SendMessageToStudentId');
    this.setData({studentsname: studentsname, studentsId: studentsId});
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