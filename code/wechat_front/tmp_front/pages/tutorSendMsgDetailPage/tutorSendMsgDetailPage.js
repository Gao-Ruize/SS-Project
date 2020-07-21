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
    // console.log(event.detail);
    this.setData({titlemsg: event.detail});
  },
  // 记录文本框里的信息，保存在contentmsg里
  bindTextAreaBlur(event){
    var result = event.detail.value;
    // console.log(result);
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
  bindOpenTime(){
    this.setData({
      show: true,
    });
  },
  // 发消息的确认，传给后端
  bindQuit(){
    // 此处补充传向后端的代码数据
    var send = {
      title: this.data.titlemsg,
      content: this.data.contentmsg,
      time: this.data.currentDate,
      tutorId: wx.getStorageSync('realid'),
      toIds: wx.getStorageSync('SendMessageToStudentId'),
    }
    let that = this;
    var url = "http://localhost:8443/api/tut/sendmsg";
    wx.request({
      url: url,
      method: 'POST',
      data: send,
      success: function (res) {
        that.bindQuit_suc(res);
      },
    })
    wx.redirectTo({
      url: '../tutorSendMsgDetailPage/tutorSendMsgDetailPage',
    })
  },
  bindQuit_suc(res){
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

  onLoad: function (options) {
    var studentsname = wx.getStorageSync('SendMessageToStudentname');
    var studentsId = wx.getStorageSync('SendMessageToStudentId');
    this.setData({studentsname: studentsname, studentsId: studentsId});
  },
})