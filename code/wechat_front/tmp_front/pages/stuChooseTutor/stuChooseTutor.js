Page({
  /**
   * 页面的初始数据
   */
  data: {
    active: 2, // 底边导航栏指向
    deptshow: false,
    tutorshow: false,
    tutorid:'',
    tutors:[],
    profession: "",
    tutor: "",
    showPop: false,
  },
  // 绑定确认提交按钮
  onConfirm(){
    //向后端发消息：
    //回到上一个页面
  },
  chooseTutor(event) {
    let tutorid = event.currentTarget.dataset.tutorid;
    let tutorname = event.currentTarget.dataset.tutorname;
    this.setData({
      tutorid: tutorid,
      tutor: tutorname,
    });
    console.log(tutorid);
    this.setData({
      showPop: false,
    })
  },
  guide(event) {
    if(event.detail == 1)
    {
      wx.redirectTo({
        url: '../stuMsgFromTutorPage/stuMsgFromTutorPage',
      })
    }
    if(event.detail == 0)
    {
      wx.redirectTo({
        url: '../stuMsgFromJwcPage/stuMsgFromJwcPage',
      })
    }
  },
  // 打开选择专业遮罩层按钮
  bindOpenDept(){
    this.setData({deptshow: true})
  },
  // 打开选择导师遮罩层按钮
  bindOpenTutor(){
    this.setData({tutorshow: true});
    this.setData({showPop: true});
  },
  onClose() {
    this.setData({
      showPop: false
    })
  },
  // 确认专业，关闭遮罩层
  // 确认导师， 关闭遮罩层
  onTutorConfirm(event){
    const { picker, value, index } = event.detail;
    var tutor = value;
    console.log(tutor);
    this.setData({tutorshow: false, tutor: tutor});
    // 向后端写入教师学生绑定


  },
  // 选学院时根据学院换专业分支
  onChange(event) {
    const { picker, value, index } = event.detail;
    picker.setColumnValues(1, dept[value[0]]);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    let baseurl = "http://localhost:8443/api/user/tutors";
    wx.request({
      url: baseurl,
      method:'GET',
      success (res) {
        console.log("tutus");
        console.log(res.data);
        that.setData({
          tutors :res.data,
        })
      }
    })
  },
  bindConfirm() {
    let studentid = wx.getStorageSync('realid');
    console.log(studentid);
    let tutorid = this.data.tutorid;
    if(tutorid == '')
    {
      wx.showToast({
        title: '请选择导师',
        icon:'none',
        duration:1000
      });
      return;
    }
    console.log(tutorid);
    let baseurl = "http://localhost:8443/api/stu/choosetutor";
    wx.request({
      url: baseurl,
      method: 'POST',
      data:{
        studentId: studentid,
        tutorId: tutorid,
      },
      success(res) {
        if(res.data.code == 200)
        {
          wx.showToast({
            title: '绑定成功',
            icon:'success',
            duration:1500
          })
        } else {
          wx.showToast({
            title: '请勿重复绑定',
            icon:'none',
            duration:1500
          })
        }
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