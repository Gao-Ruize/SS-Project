const app = getApp()

Page({
  data:{
    activate: 'home',
    selectPhase: [

    ],
    showSelect: [],
    ProposalPhase: [

    ],
    showProposal: [],
    MiddlePhase: [

    ],
    showMiddle: [],
    replyPhase: [

    ],
    showReply: [],
    pigeonholePhase: [

    ],
    showPigeonhole: [],
    active: 1, // 底边导航栏
  },

  onLoad: function(options) {
    var hasNewInfo = this.data.hasNewInfo;
    var str = (hasNewInfo === true) ? '您有新消息待查看' : '暂无新消息';
    this.setData({
      noticeInfo: str,
      showSelect: this.data.selectPhase,
      showMiddle: this.data.MiddlePhase,
      showProposal: this.data.ProposalPhase,
      showReply: this.data.replyPhase,
      showPigeonhole: this.data.pigeonholePhase,
    let that = this;
    let realid = wx.getStorageSync('realid');
    let baseurl ='http://localhost:8443/api/stu/insmsg/' + realid;
    wx.request({
      url: baseurl,
      method: 'GET',
      success(res) {
        let info = res.data;
        phase1 = [];
        phase2 = [];
        phase3 = [];
        phase4 = [];
        phase5 = [];
        for (var i = 0; i < info.length; ++i)
        {
          switch(info[i].phase) {
            case 1:
              phase1.push(info[i])
              break;
            case 2:
              phase2.push(info[i])
              break;
            case 3:
              phase3.push(info[i])
              break;
            case 4:
              phase4.push(info[i])
              break;
            case 5:
              phase5.push(info[i])
              break;
          }
        }
        that.setData({
          selectPhase: phase1,
          ProposalPhase: phase2,
          MiddlePhase: phase3,
          replyPhase: phase4,
          pigeonholePhase: phase5
        }, () => {
            that.setData({
              showSelect: this.data.selectPhase,
              showMiddle: this.data.MiddlePhase,
              showProposal: this.data.ProposalPhase,
              showReply: this.data.replyPhase,
              showPigeonhole: this.data.pigeonholePhase,
          });
        })
      }
    });

    
  },

  onChange(event) {
    if(event.detail == 0){
      wx.redirectTo({
        url: '../stuMsgFromJwcPage/stuMsgFromJwcPage',
      })
    }
    if(event.detail == 2) 
    {
      wx.redirectTo({
        url: '../stuChooseTutor/stuChooseTutor',
      })
    }
  },

  onSearch(e) {
    let showPhase1 = [];
    let data1 = this.data.selectPhase;
    for (var i = 0; i < data1.length; ++i)
    {
      if (data1[i].detail.includes(e.detail))
      {
        showPhase1.push(data1[i]);
      }
    };
    let showPhase2 = [];
    let data2 = this.data.ProposalPhase;
    for (var i = 0; i < data2.length; ++i)
    {
      if (data2[i].detail.includes(e.detail))
      {
        showPhase2.push(data2[i]);
      }
    };
    let showPhase3 = [];
    let data3 = this.data.MiddlePhase;
    for (var i = 0; i < data3.length; ++i)
    {
      if (data3[i].detail.includes(e.detail))
      {
        showPhase3.push(data3[i]);
      }
    };
    let showPhase4 = [];
    let data4 = this.data.replyPhase;
    for (var i = 0; i < data4.length; ++i)
    {
      if (data4[i].detail.includes(e.detail))
      {
        showPhase4.push(data4[i]);
      }
    }
    let showPhase5 = [];
    let data5 = this.data.pigeonholePhase;
    for (var i = 0; i < data5.length; ++i)
    {
      if (data5[i].detail.includes(e.detail))
      {
        showPhase5.push(data5[i]);
      }
    }
    this.setData({
      showSelect: showPhase1,
      showProposal: showPhase2,
      showMiddle: showPhase3,
      showReply: showPhase4,
      showPigeonhole: showPhase5
    })

  },

  onCancel(e) {
    this.setData({
      showSelect: this.data.selectPhase,
      showMiddle: this.data.MiddlePhase,
      showProposal: this.data.ProposalPhase,
      showReply: this.data.replyPhase,
      showPigeonhole: this.data.pigeonholePhase
    });
  }
})