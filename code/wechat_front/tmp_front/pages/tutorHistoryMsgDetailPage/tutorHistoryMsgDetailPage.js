// pages/sentMsgDetailPage/sentMsgDetailPage.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    //信息id
    msgId:"",
    active:1,
    //全部学生信息
    readStudentInfo:[],
    //未读学生信息，在发送通知时使用
    unreadStudentInfo:[],
    //页面展示学生信息，在搜索时使用
    showStudents:[],
    searchValue:"",
    showPopUp:false,
    result:[],
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

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let tmpMsgId = options.msgId;
    this.setData({
      msgId: tmpMsgId,
    });
    //通过msgId获得对应的学生
    //将已读学生和未读学生存储到一起
    //获得信息后将showStudent为全部学生信息
    let baseurl = 'http://39.106.85.149:8080/api/tut/getmsginfo/' + tmpMsgId;
    let token = wx.getStorageSync('token');
    let that = this;
    wx.request({
      url: baseurl,
      method: 'GET',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      success(res) {
        that.onLoad_suc(res)
      }
    });
  },
  onLoad_suc(res){
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    this.setData({
      readStudentInfo: res.data,
      showStudents: res.data
    })
  }
  ,
  onChange(event) {
    // event.detail 的值为当前选中项的索引
    this.setData({ active: event.detail });
  },
  searchChange(event) {
    this.setData({
      searchValue:event.detail,
    });
    // let checkdata = this.data.searchValue;
    // console.log(checkdata);
  },
  showSendPop(){
    this.setData({showPopUp:true});
    this.getUnreadInfo();
  },
  onClose() {
    this.setData({showPopUp: false});
    this.setData({unreadStudentInfo:[]});
  },

  onSearch(){
    // 将内容过滤后存入showStudents
    // console.log("search!");
    let value = this.data.searchValue;
    let tmpArr = [];
    for(var i=0; i<this.data.readStudentInfo.length; i++){
      let item = this.data.readStudentInfo[i];
      if(item.studentname.includes(value) 
         || item.studentid.includes(value)) {
        tmpArr.push(item);
      }
    };
    this.setData({showStudents: tmpArr});
  },

  onCancel() {
    let tmpArr = this.data.readStudentInfo;
    this.setData({showStudents: tmpArr});
  },

  mulChosChange(event) {
    this.setData({result:event.detail});
    // console.log("choose");
    // console.log(this.data.result);
  },
  changeChosBar(event) {
    this.setData({result:event.detail})
  },
  successCheck(res) {
    if(this.errCheck(res)) {
      this.app.onLaunch();
      return;
    }
    if(res.data.code == 200) {
      wx.showToast({
        title: '发送成功！',
        icon:"success",
        duration:1500
      });
    }
  },
  sendMsg(){
    let that = this;
    let students = this.data.result;
    let contain = wx.getStorageSync('msgTitle');
    let realId = wx.getStorageSync('realid');
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    let time = year + '-' + month + '-' + day;
    // console.log(time);
    contain = "请及时阅读通知：" + contain; 
    // console.log(students);
    let token = wx.getStorageSync('token');
    let baseurl = "http://39.106.85.149:8080/api/tut/sendmsg";
    wx.request({
      url: baseurl,
      method: 'POST',
      header: {
        'content-type': 'application/json', // 默认值
        'token': token,
      },
      data: {
        toIds: students,
        title: "阅读消息提醒",
        content: contain,
        time: time,
        tutorId: realId,
      },
      success(res) {
        that.successCheck(res);
      }
    });
    // wx.showToast({
    //   title: '发送成功！',
    //   icon:"success",
    //   duration:1500
    // });
    this.onClose();
    this.setData({result:[]});
  },
  getUnreadInfo() {
    let that = this;
    let tmpArr = [];
    for(let i=0; i<this.data.readStudentInfo.length; i++) {
      let item = this.data.readStudentInfo[i];
      if(item.ifRead == 0) {
        tmpArr.push(item);
      }
    };
    this.setData({unreadStudentInfo:tmpArr});
  },
})