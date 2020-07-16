// pages/stuIndex/stuIndex.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    phaseId: '',
    phaseName:'',
    jwcMsgNum: 0,
    insMsgNum: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setPhaseId();
    this.setInsNum();
    this.setJwcNum();
  },
  setPhaseId() {
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let checkDate = year * 10000 + month * 100 + day;
    let point1 = 20200100;
    let point2 = 20200300;
    let point3 = 20200500;
    let point4 = 20200700;
    let point5 = 20200900;
    let point6 = 20201231;
    let result = 0;
    let name = '';
    if(checkDate >= point1 && checkDate <= point2) 
      {result = 0; name = '导师选择阶段';}
    else if(checkDate > point2 && checkDate <= point3)
      {result = 1; name = '开题报告阶段';}
    else if(checkDate > point3 && checkDate <= point4)
      {result = 2; name = '中期检查阶段';}
    else if(checkDate > point4 && checkDate <= point5) 
      {result = 3; name = '答辩阶段';}
    else if(checkDate > point5 && checkDate <= point6)
      {result = 4; name = '归档阶段';}
    else {result = 100; name = '非毕业设计阶段'}
    this.setData({
      phaseId: result,
      phaseName: name
    })
  },
  toStudentPage () {
    wx.navigateTo({
      url: '/pages/stuMsgFromJwcPage/stuMsgFromJwcPage',
    })
  },
  setJwcNum () {
    let that = this;
    //let studentId = wx.getStorageSync('realid');
    let studentId = "123";
    let baseurl = "http://localhost:8443/api/stu/unreadjwcmsg/" + studentId;
    wx.request({
      url: baseurl,
      success (res) {
        that.setData({
          jwcMsgNum: res.data
        })
      }
    })
  },
  setInsNum () {
    let that = this;
    //let studentId = wx.getStorageSync('realid');
    let studentId = "123";
    let baseurl = "http://localhost:8443/api/stu/unreadinsmsg/" + studentId;
    wx.request({
      url: baseurl,
      success (res) {
        that.setData({
          insMsgNum: res.data,
        })
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