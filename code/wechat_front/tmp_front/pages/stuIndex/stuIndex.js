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
  setPhaseId(num = 0) {
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let checkDate = 0;
    if(num == 0)
      checkDate = year * 10000 + month * 100 + day;
    else
      checkDate = num;
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
    let studentId = wx.getStorageSync('realid');
    //let studentId = "123";
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
    let studentId = wx.getStorageSync('realid');
    //let studentId = "123";
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
})