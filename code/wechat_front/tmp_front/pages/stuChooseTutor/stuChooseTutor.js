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
    jwcMsgCount: '',
    tutMsgCount: 0,
  },
  setJwcCount () {
    let that = this;
    let ID = wx.getStorageSync('realid');
    let baseUrl = "http://localhost:8443/api/stu/unreadjwcmsg/" + ID;
    wx.request({
      url: baseUrl,
      method: 'GET',
      success(res) {
        that.setData({
          jwcMsgCount: res.data,
        })
      }
    })
  },
  setTutCount () {
    let that = this;
    let ID = wx.getStorageSync('realid');
    let baseUrl = "http://localhost:8443/api/stu/unreadinsmsg/" + ID;
    wx.request({
      url: baseUrl,
      success (res) {
        that.setData({
          tutMsgCount: res.data,
        })
      }
    })
  },
  chooseTutor(event) {
    let tutorid = event.currentTarget.dataset.tutorid;
    let tutorname = event.currentTarget.dataset.tutorname;
    this.setData({
      tutorid: tutorid,
      tutor: tutorname,
    });
    // console.log(tutorid);
    this.setData({
      showPop: false,
    })
  },
  guide(event) {
    if(event.detail == 1)
    {
      wx.redirectTo({
        url: '../stuMsgFromIns/stuMsgFromInsPage',
      })
    }
    if(event.detail == 0)
    {
      wx.redirectTo({
        url: '../stuMsgFromJwcPage/stuMsgFromJwcPage',
      })
    }
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setJwcCount();
    this.setTutCount();
    let that = this;
    let baseurl = "http://localhost:8443/api/user/tutors";
    wx.request({
      url: baseurl,
      method:'GET',
      success (res) {
        // console.log("tutus");
        // console.log(res.data);
        that.setData({
          tutors :res.data,
        })
      }
    })
  },
  bindConfirm() {
    let studentid = wx.getStorageSync('realid');
    // console.log(studentid);
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
    // console.log(tutorid);
    let baseurl = "http://localhost:8443/api/stu/choosetutor";
    wx.request({
      url: baseurl,
      method: 'POST',
      data:{
        studentId: studentid,
        tutorId: tutorid,
      },
      success(res) {
        bindConfirm_suc(res);
      }
    })
  },
  bindConfirm_suc(res){
    if(res.data.code == 200)
    {
      wx.showToast({
        title: '绑定成功',
        icon:'success',
        duration:1500
      })
    } 
    else {
      wx.showToast({
        title: '请勿重复绑定',
        icon:'none',
        duration:1500
      })
    }
  }
})