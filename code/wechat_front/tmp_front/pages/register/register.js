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
    radio: -1,
    type: wx.getStorageSync('type')
  },
  // 选择学生、导师身份
  onChoose(event){
    this.setData({radio: event.detail})
  },
  // 输入框，输入学号工号
  onInput(event){
    this.setData({value: event.detail})
  },
  onCommit(){
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
      // console.log(1);
      let type = this.data.type;
      if(type == "err") {
        wx.showToast({
          title: '出错了',
          duration: 1500,
          icon: 'none'
        });
        return;
      }
      // console.log(openid);
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
          onCommit_suc(res);
        }
      });
      return;
    }
  },
  onCommit_suc(res){
    // console.log(res.data);
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
    } else 
    if(result == 400) {
      wx.showToast({
        title: '无效ID',
        icon: 'none',
        duration: 1500
      });
    } else
    {
      wx.showToast({
        title: '该ID已被占用',
        icon:'none',
        duration: 1500
      })
    }
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
})
