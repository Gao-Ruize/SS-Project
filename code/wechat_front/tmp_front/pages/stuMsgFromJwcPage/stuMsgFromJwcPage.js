//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World!',
    show: false,
    date: '',
    active: 0, // 底边导航栏指向
    minDate: new Date(2018, 0, 1).getTime(),
    maxDate: new Date(2022, 0, 31).getTime(),    
    allMsgs:[],
  }, 
  onChange (event) {
    if(event.detail == 1)
    {
      wx.redirectTo({
        url: '../stuMsgFromTutorPage/stuMsgFromTutorPage',
      })
    }
    if(event.detail == 2) 
    {
      wx.redirectTo({
        url: '../stuChooseTutor/stuChooseTutor',
      })
    }
  },
  onDisplay() {
    this.setData({ show: true });
  },
  onClose() {
    this.setData({ show: false });
  },
  formatDate(date) {
    date = new Date(date);
    return `${date.getYear() + 1900}/${date.getMonth() + 1}/${date.getDate()}`;
  },
  onConfirm(event) {
    const [start, end] = event.detail;
    this.setData({
      show: false,
      date: `${this.formatDate(start)} - ${this.formatDate(end)}`,
    });
    // console.log(this.data.date);
  },
  showDetails(event) {
    let type = 'jwc';
    let msgid = event.currentTarget.dataset.id;
    // console.log(msgid);
    wx.navigateTo({
      url: '/pages/messageDetail/messageDetailPage?msgId=' + msgid + 
      '&type=' + type,
    });
  },
  onLoad: function () {
    let that = this;
    let type = wx.getStorageSync('type');
    let realid = wx.getStorageSync('realid');
    // console.log("get msgs");
    // console.log(type);
    // console.log(realid);
    let baseurl = "http://localhost:8443/api/user/typejwcmsg/" 
      + type + "/" + realid;
    wx.request({
      url: baseurl,
      method: 'GET',
      success (res) {
        that.setData({
          allMsgs: res.data,
        });
        // console.log(res.data);
      }
    })
  }
})
