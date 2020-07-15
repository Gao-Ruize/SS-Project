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
    showStduents:[],
    searchValue:"",
    showPopUp:false,
    result:[],
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
    //获得信息后将将showStudent为全部学生信息
    let baseurl = 'http://localhost:8443/api/tut/getmsginfo/' + tmpMsgId;
    let that = this;
    wx.request({
      url: baseurl,
      method: 'GET',
      success(res) {
        that.setData({
          readStudentInfo: res.data,
          showStduents: res.data
        })
      }
    });
  },
  onChange(event) {
    // event.detail 的值为当前选中项的索引
    this.setData({ active: event.detail });
  },
  searchChange(event) {
    this.setData({
      searchValue:event.detail,
    });
    let checkdata = this.data.searchValue;
    console.log(checkdata);
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
    //将内容过滤后存入showStudents
    console.log("search!");
    let value = this.data.searchValue;
    let tmpArr = [];
    this.data.readStudentInfo.forEach(function(item,index) {
      if(item.studentname.includes(value) 
         || item.studentid.includes(value)) {
        tmpArr.push(item);
      }
    });
    this.setData({showStduents: tmpArr});
  },

  onCancel() {
    let tmpArr = this.data.readStudentInfo;
    this.setData({showStduents: tmpArr});
  },

  mulChosChange(event) {
    this.setData({result:event.detail});
    console.log("choose");
    console.log(this.data.result);
  },
  changeChosBar(event) {
    this.setData({result:event.detail})
  },
  sendMsg(){
    let students = this.data.result;
    let contain = wx.getStorageSync('msgTitle');
    let realId = wx.getStorageSync('realid');
    let time = new Date();
    console.log(time);
    contain = "请及时阅读通知：" + contain; 
    console.log(students);
    let baseurl = "http://localhost:8443/api/tut/sendmsg";
    wx.request({
      url: baseurl,
      method: 'POST',
      data: {
        toIds: students,
        title: "阅读消息提醒",
        content: contain,
        time: time,
        tutorId: realId,
      }
    })
    wx.showToast({
      title: '发送成功！',
      icon:"success",
      duration:1500
    });
    this.onClose();
    this.setData({result:[]});
  },
  getUnreadInfo() {
    let that = this;
    let tmpArr = [];
    this.data.readStudentInfo.forEach(function(item,index) {
      if(item.ifRead == 0) {
        tmpArr.push(item);
      }
    });
    this.setData({unreadStudentInfo:tmpArr});
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