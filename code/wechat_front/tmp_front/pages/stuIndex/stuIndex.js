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
  app: getApp(),
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
  errCheck(res) {
    let errCheck = res.statusCode;
        if(errCheck == 500) {
          wx.showToast({
            title: '登陆超时，请重新登陆',
            icon: 'none'
          });  
          return 1;  
        }
        return 0;
  },
  setJwcNum () {
    let that = this;
    let studentId = wx.getStorageSync('realid');
    //let studentId = "123";
    let baseurl = this.app.baseUrl + "/api/stu/unreadjwcmsg/" + studentId;
    // let baseurl = "http://39.106.85.149:8080/api/stu/unreadjwcmsg/" + studentId;
    let token = wx.getStorageSync('token');
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res) {
        that.setJwcNum_suc(res)
      }
    })
  },
  setJwcNum_suc(res){
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    this.setData({
      jwcMsgNum: res.data
    })
  },
  setInsNum () {
    let that = this;
    let studentId = wx.getStorageSync('realid');
    //let studentId = "123";
    let token = wx.getStorageSync('token');
    // let baseurl = "http://39.106.85.149:8080/api/stu/unreadinsmsg/" + studentId;
    let baseurl = this.app.baseUrl + "/api/stu/unreadinsmsg/" + studentId;
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res){
        that.setInsNum_suc(res)
      }
    })
  },
  setInsNum_suc(res) {
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    this.setData({
      insMsgNum: res.data,
    })
  }
})