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
    contentmsg: "",
    phase: 0,
    value1: 0,
    option1: [
      { text: '请选择信息发送阶段', value: 0 },
      { text: '导师选择通知阶段', value: 1 },
      { text: '开题报告通知阶段', value: 2 },
      { text: '中期检查通知阶段', value: 3 },
      { text: '答辩通知阶段', value: 4 },
      { text: '归档通知阶段', value: 5 },
    ],
  },
  app: getApp(),
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
    let tmpPhase = this.data.phase;
    if(tmpPhase == 0) {
      wx.showToast({
        title: '请选择阶段!',
        icon:'none',
        duration: 1500
      });
      return;
    }
    tmpPhase = tmpPhase - 1;
    var send = {
      title: this.data.titlemsg,
      content: this.data.contentmsg,
      time: this.data.timeSpanStr,
      tutorId: wx.getStorageSync('realid'),
      toIds: wx.getStorageSync('SendMessageToStudentId'),
      phase: tmpPhase
    }
    let that = this;
    let token = wx.getStorageSync('token');
    // var url = "http://39.106.85.149:8080/api/tut/sendmsg";
    var url = this.app.baseUrl + "/api/tut/sendmsg";
    wx.request({
      url: url,
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      data: send,
      success: function (res) {
        that.bindQuit_suc(res);
      },
    })
    wx.redirectTo({
      url: '../tutorSendMsgPage/tutorSendMsgPage',
    })
  },
  bindQuit_suc(res){
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
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

  changeDropItem (e) {
    let tmp = e.detail;
    this.setData({
      phase: tmp
    });
    console.log(this.data.phase);
  }
})