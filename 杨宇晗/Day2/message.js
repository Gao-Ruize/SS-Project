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
  },

  onLoad: function(options) {
    var hasNewInfo = this.data.hasNewInfo;
    this.setData({
      noticeInfo: (hasNewInfo === true) ? '您有新消息待查看' : '暂无新消息',
      showSelect: this.data.selectPhase,
      showMiddle: this.data.MiddlePhase,
      showProposal: this.data.ProposalPhase,
    });
    
  },

  onChange(event) {
    this.setData({ active: event.detail });
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
        showPhase2.push(data1[i]);
      }
    }
    this.setData({
      showSelect: showPhase1,
      showProposal: showPhase2,
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