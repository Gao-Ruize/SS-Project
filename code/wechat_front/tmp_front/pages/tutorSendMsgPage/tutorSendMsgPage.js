// pages/qunfaMessage/qunfaMessage.js
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    list:[],
    result:[],
    active: 2,
    jwcMsgCount: '',
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
  setJwcCount() {
    let ID = wx.getStorageSync('realid');
    let token = wx.getStorageSync('token');
    let baseurl = "http://localhost:8443/api/tut/jwcmsgcount/" + ID;
    let that = this;
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success (res) {
        if(that.errCheck(res)) {
          app.onLaunch();
          return;
        }
        that.setData({
          jwcMsgCount: res.data
        })
      }
    })
  },
  getStudents(){
    var t_id = wx.getStorageSync('realid');
    let token = wx.getStorageSync('token');
    var url = "http://localhost:8443/api/tut/students/"+t_id;
    const _this = this;
    wx.request({
      url: url,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success: function(res){
        if(_this.errCheck(res)) {
          app.onLaunch();
          return;
        }
        _this.getStudents_suc(res);
      },
    })
  },
  getStudents_suc(res){
    this.setData({list: res.data});
  },
  // 多选框结果的记录
  onChangeCheck(event) {
    // console.log(event.detail);
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
  changetomessage(){
    var nameset = [];
    for(var i=0; i<this.data.list.length; i++)
    {
      if(this.data.result.indexOf(this.data.list[i].studentId) != -1)
      {
        nameset.push(this.data.list[i].studentname);
      }
      // console.log(nameset);
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
    this.setJwcCount();
    this.getStudents();
    let that = this;
    var t_id = wx.getStorageSync('realid');
    let token = wx.getStorageSync('token');
    var url = "http://localhost:8443/api/tut/students/"+t_id;
    wx.request({
      url: url,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success: function (res) {
        if(that.errCheck(res)) {
          app.onLaunch();
          return;
        }
        that.setData({list: res.data})
      }
    })
  },
})