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
    radio: -1
  },
  // 选择学生、导师身份
  onChoose(event){
    this.setData({radio: event.detail})
  },
  // 输入框，输入学号工号
  onInput(event){
    this.setData({value: event.detail})
  },
  onCommit(event){
    //向后端发消息，若后端返回成功，则继续，若返回errMsg，则报错，让其重新输入或联系管理员
    if(this.data.value == "") {
      wx.showToast({
        title: '请输入学号/工号！',
        icon:'none',
      });
      return;
    }
    let indentity = this.data.radio;
    console.log(indentity);
    if(indentity == -1) {
      wx.showToast({
        title: '请选择身份！',
        icon:'none'
      }) }
      else
      {
        let baseurl = 'http://localhost:8443/api/user/bind';
        let openid = wx.getStorageSync('openid');
        console.log(openid);
        let type = wx.getStorageSync('type');
        if(type == "err") {
          wx.showToast({
            title: '出错了',
            duration: 1500,
            icon: 'none'
          });
          return;
        }
        console.log(openid);
        wx.setStorageSync('realid', this.data.value);
        wx.request({
          url: baseurl,
          method: 'POST',
          data: {
            type: indentity,
            code: openid,
            realId: this.data.value
          },
          success(res) {
            console.log(res.data);
            let result = res.data.code;
            if(result == 200) {
              wx.setStorageSync('type', "S");
              wx.navigateTo({
                url: '../stuMsgFromJwcPage/stuMsgFromJwcPage',
              });
            } else
            if(result == 201) {
              wx.setStorageSync('realid', "T");
              wx.navigateTo({
                url: '../tutorMsgFromJwcPage/tutorMsgFromJwcPage',
              });
            } else {
              wx.showToast({
                title: '该ID已被占用',
                icon:'none',
                duration: 1500
              })
            }
          }
        });
        return;
      }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  onChange(event) {
    this.setData({
      radio: event.detail,
    });
  },

  onClick(event) {
    const { name } = event.currentTarget.dataset;
    this.setData({
      radio: name,
    });
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