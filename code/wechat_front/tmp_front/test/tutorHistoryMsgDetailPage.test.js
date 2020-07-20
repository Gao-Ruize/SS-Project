import '../pages/tutorHistoryMsgDetailPage/tutorHistoryMsgDetailPage'

const page = global.wxPageInstance;

describe('导师发送消息页面', ()=>{
  describe('on load', ()=>{
    const event = {msgId: 1};
    jest.spyOn(page, 'setData');
    page.onLoad(event);
    it('应该执行onload()', () => {
      expect(page.setData).toBeCalled();
      expect(wx.request).toBeCalled();
    });
  });

  describe('onChange', ()=>{
    const event = {detail: 1};
    jest.spyOn(page, 'setData');
    page.onChange(event);
    it('应该执行onChange()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('searchChange', ()=>{
    const event = {detail: 1};
    jest.spyOn(page, 'setData');
    page.searchChange(event);
    it('searchChange()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('showSendPop', ()=>{
    jest.spyOn(page, 'getUnreadInfo');
    jest.spyOn(page, 'setData');
    page.showSendPop();
    it('showSendPop()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onClose', ()=>{
    jest.spyOn(page, 'setData');
    page.onClose();
    it('onClose()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onSearch', ()=>{
    page.data.searchValue = "1";
    page.data.readStudentInfo = [{studentname: "1", studentid: "2"},{studentname: "2", studentid: "1"},{studentname: "2", studentid: "2"}];
    jest.spyOn(page, 'setData');
    page.onSearch();
    it('onSearch()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onCancel', ()=>{
    jest.spyOn(page, 'setData');
    page.onCancel();
    it('onCancel()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('mulChosChange', ()=>{
    const event = {detail: 1};
    jest.spyOn(page, 'setData');
    page.mulChosChange(event);
    it('mulChosChange()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('changeChosBar', ()=>{
    const event = {detail: 1};
    jest.spyOn(page, 'setData');
    page.changeChosBar(event);
    it('changeChosBar()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('sendMsg', ()=>{
    jest.spyOn(page, 'setData');
    jest.spyOn(page, 'onClose');
    page.sendMsg();
    it('sendMsg()', () => {
      expect(page.setData).toBeCalled();
      expect(page.onClose).toBeCalled();
      expect(wx.request).toBeCalled();
      expect(wx.showToast).toBeCalled();
    });
  });

  describe('getUnreadInfo', ()=>{
    jest.spyOn(page, 'setData');
    page.data.readStudentInfo = [{ifRead: 1},{ifRead: 0}];
    page.getUnreadInfo();
    it('getUnreadInfo()', () => {
      expect(page.setData).toBeCalled();
    });
  });
})