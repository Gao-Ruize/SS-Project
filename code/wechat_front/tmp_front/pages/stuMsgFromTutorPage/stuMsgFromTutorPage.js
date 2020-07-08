const app = getApp()

Page({
  data:{
    activate: 'home',
    hasNewInfo: true,
    noticeInfo: '',
    selectPhase: [
      {
        name: 'JBoss',
        detail:"你好，已经收到你的申请，我正在审核!",
        hasRead: true,
        ReadStatus: '已读',
      },
      {
        name: 'JBoss',
        detail: '你好，我已通过你的申请!',
        hasRead: false,
        ReadStatus: '未读',
      }
    ],
    showSelect: [],
    ProposalPhase: [
      {
        name: 'JBoss',
        detail: '你好，你的开题报告我已收到！',
        ReadStatus: '已读',
      }
    ],
    showProposal: [],
    MiddlePhase: [
      {
        name: 'JBoss',
        detail: '你好，请填写并发送中期检查表格',
        ReadStatus: '未读',
      }
    ],
    showMiddle: [],
    replyPhase: [
      {
        name: 'JBoss',
        detail: '你好，请于2020-02-07日参加答辩',
        ReadStatus: '已读',
      }
    ],
    showReply: [],
    pigeonholePhase: [
      {
        name: 'JBoss',
        detail: '你好吗我不好',
        ReadStatus: '未读',
      }
    ],
    showPigeonhole: [],
    active: 1, // 底边导航栏
  },

  onLoad: function(options) {
    var hasNewInfo = this.data.hasNewInfo;
    this.setData({
      noticeInfo: (hasNewInfo === true) ? '您有新消息待查看' : '暂无新消息',
      showSelect: this.data.selectPhase,
      showMiddle: this.data.MiddlePhase,
      showProposal: this.data.ProposalPhase,
      showReply: this.data.replyPhase,
      showPigeonhole: this.data.pigeonholePhase,
    });
    
  },

  onChange(event) {
    if(event.detail == 0){
      wx.navigateBack({
        complete: (res) => {},
      })
    }
  },

  onSearch(e) {
    let showPhase1 = [];
    let data1 = this.data.selectPhase;
    for (var i = 0; i < data1.length; ++i)
    {
      if (data1[i].detail.indexOf(e.detail) != -1)
      {
        showPhase1.push(data1[i]);
      }
    };
    let showPhase2 = [];
    let data2 = this.data.ProposalPhase;
    for (var i = 0; i < data2.length; ++i)
    {
      if (data2[i].detail.indexOf(e.detail) != -1)
      {
        showPhase2.push(data2[i]);
      }
    };
    let showPhase3 = [];
    let data3 = this.data.MiddlePhase;
    for (var i = 0; i < data3.length; ++i)
    {
      if (data3[i].detail.indexOf(e.detail) != -1)
      {
        showPhase3.push(data3[i]);
      }
    };
    let showPhase4 = [];
    let data4 = this.data.replyPhase;
    for (var i = 0; i < data4.length; ++i)
    {
      if (data4[i].detail.indexOf(e.detail) != -1)
      {
        showPhase4.push(data4[i]);
      }
    }
    let showPhase5 = [];
    let data5 = this.data.pigeonholePhase;
    for (var i = 0; i < data5.length; ++i)
    {
      if (data5[i].detail.indexOf(e.detail) != -1)
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
    });
  }
})