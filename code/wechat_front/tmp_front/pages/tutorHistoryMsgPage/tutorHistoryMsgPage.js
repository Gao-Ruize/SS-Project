// pages/sentMsgPage/sentMsgPage.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 1,
    //全部信息
    msgItems:[],
    searchValue:"",
    //页面展示信息，即搜索过滤后的信息
    showItems:[],
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let realId = wx.getStorageSync('realid');
    let baseurl = "http://localhost:8443/api/tut/insmsgs/" + realId;
    let that = this;
    wx.request({
      url: baseurl,
      method: 'GET',
      success(res) {
        load_suc(res);
      }
    });
  },
  onLoad_suc(res){
    // console.log(res.data);
    this.setData({
      msgItems: res.data
    });
    this.setData({
      showItems: res.data
    });
  },
  onChange(event) {
    if(event.detail == 0)
    {
      wx.redirectTo({
        url: '../tutorMsgFromJwcPage/tutorMsgFromJwcPage',
      })
    }
    if(event.detail == 2)
    {
      wx.redirectTo({
        url: '../tutorSendMsgPage/tutorSendMsgPage',
      })
    }
  },
  searchChange(event) {
    this.setData({searchValue: event.detail});
    // let checkvalue = this.data.searchValue;
    // console.log("change!");
    // console.log(checkvalue);
  },
  onSearch(){
    // console.log("search!");
    let tmpArr = [];
    let value = this.data.searchValue;
    for(var i=0; i < this.data.msgItems.length; i++){
      let item = this.data.msgItems[i];
      if(item.title.includes(value) 
         || item.releasetime.includes(value)) {
        tmpArr.push(item);
      }
    };
    this.setData({showItems: tmpArr});
  },
  onCancel(){
    let tmpArr = this.data.msgItems;
    this.setData({showItems: tmpArr});
  },
  checkDetails(event) {
    let msgId = event.currentTarget.dataset.id;
    let title = event.currentTarget.dataset.title;
    wx.setStorageSync('msgTitle', title);
    // console.log(title);
    // console.log(msgId);
    wx.navigateTo({
      url: '../tutorHistoryMsgDetailPage/tutorHistoryMsgDetailPage?msgId=' + msgId
    })
  },
})