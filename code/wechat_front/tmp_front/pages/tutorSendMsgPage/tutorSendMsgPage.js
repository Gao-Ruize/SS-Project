// pages/qunfaMessage/qunfaMessage.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    list:[
      {
        id:0,
        studentname:"学生A",
        studentId:"111111",
        uid:"u1"
      },
      {
        id:1,
        studentname:"学生B",
        studentId:"222222",
        uid:"u2"
      },
      {
        id:2,
        studentname:"学生C",
        studentId:"333333",
        uid:"u3"
      }
    ],
    result:[],
    active: 2,
  },
  // 多选框结果的记录
  onChangeCheck(event) {
    console.log(event.detail);
    this.setData({
      result: event.detail,
    });
  },
  // 底部导航栏的路由
  onChange(event) {
    if(event.detail == 1)
    {
      wx.redirectTo({
        url: '../tutorHistoryMsgPage/tutorHistoryMsgPage',
      })
    }
    if(event.detail == 0)
    {
      wx.redirectTo({
        url: '../tutorMsgFromJwcPage/tutorMsgFromJwcPage',
      })
    }
  },
  // 转到群发消息detail界面
  changetomessage(event){
    var nameset = [];
    for(var i=0; i<this.data.list.length; i++)
    {
      
      if(this.data.result.indexOf(this.data.list[i].studentId) != -1)
      {
        nameset.push(this.data.list[i].studentname);
      }
        console.log(nameset);
    }
    wx.setStorageSync('SendMessageToStudentname', nameset);
    wx.setStorageSync('SendMessageToStudentId', this.data.result);
    wx.navigateTo({
      url: '../tutorSendMsgDetailPage/tutorSendMsgDetailPage',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var t_id = wx.getStorageSync('realid');
    var url = "http://localhost:8443/tut/students/"+t_id;
    wx.request({
      url: url,
      method: 'GET',
      data: t_id,
      success: function (res) {
        this.setData({list: res.data})
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