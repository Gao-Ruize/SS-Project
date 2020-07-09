// pages/stuChooseTutor/stuChooseTutor.js
// 学院及其专业，如果从后端找数据会太慢，影响效率。并且这些数据不用考虑安全性问题
const dept = {
  电院: ['自动化', '计算机科学', '软件工程', '信息安全', '信息工程','人工智能','精密仪器'],
  机动: ['汽修', '厨师', '美容美发', '挖掘机','机械工程','能源与动力工程'],
};

Page({

  /**
   * 页面的初始数据
   */
  data: {
    deptshow: false,
    tutorshow: false,
    columns: [
      {
        values: Object.keys(dept),
        className: 'column1',
      },
      {
        values: dept['电院'],
        className: 'column2',
        defaultIndex: 2,
      },
    ],
    tutors: []
  },
  bindOpenDept(){
    this.setData({deptshow: true})
  },
  bindOpenTutor(){
    this.setData({tutorshow: true})
  },
  onDeptConfirm(event){
    const { picker, value, index } = event.detail;
    var dept = value[0];
    var perfession = value[1];
    console.log(perfession);
    this.setData({deptshow: false});
    // 从后端读该专业的教师数据


  },
  onTutorConfirm(event){
    const { picker, value, index } = event.detail;
    var tutor = value[0];
    console.log(tutor);
    this.setData({tutorshow: false});
    // 向后端写入教师学生绑定


  },
  onChange(event) {
    const { picker, value, index } = event.detail;
    picker.setColumnValues(1, dept[value[0]]);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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