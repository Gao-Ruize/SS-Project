import '../pages/messageDetail/messageDetailPage'

const page = global.wxPageInstance;

describe('messageDetail', ()=>{
  describe('onCollChange', ()=>{
    jest.spyOn(page,'setData')
    const event = {detail: 0};
    page.onCollChange(event);
    it('onCollChange()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onCommit', ()=>{
    page.onCommit();
    it('onCommit()', () => {
      expect(wx.request).toBeCalled();
    });
  });

  describe('onCommit_suc', ()=>{
    let res = {data: {code: 200}, statusCode: 500};
    page.onCommit_suc(res);
    res = {data: {code: 200}, statusCode: 100};
    page.data.userType = 'S';
    page.data.senderType = 'jwc';
    page.onCommit_suc(res);
    it('onCommit_suc()', () => {
      expect(wx.showToast).toBeCalled();
      expect(wx.navigateTo).toBeCalled();
    });
    page.data.userType = 'S';
    page.data.senderType = '"jc"';
    page.onCommit_suc(res);
    it('onCommit_suc()', () => {
      expect(wx.showToast).toBeCalled();
      expect(wx.navigateTo).toBeCalled();
    });
    page.data.userType = 'F';
    page.data.senderType = '"jc"';
    page.onCommit_suc(res);
    it('onCommit_suc()', () => {
      expect(wx.showToast).toBeCalled();
      expect(wx.navigateTo).toBeCalled();
    });

    res = {data: {code: 400},statusCode: 100};
    page.onCommit_suc(res);
    it('onCommit_suc()', () => {
      expect(wx.showToast).toBeCalled();
    });
  });

  describe('onLoad', ()=>{
    const option = {msgId: 0};
    jest.spyOn(page,'setData')
    page.onLoad(option);
    it('onLoad()', () => {
      expect(page.setData).toBeCalled();
      expect(wx.request).toBeCalled();
    });
  });

  describe('errCheck', ()=>{
    let res = {statusCode: 500};
    page.errCheck(res);
    res = {statusCode: 0};
    page.errCheck(res);
    it('应该执行errCheck()', () => {
      expect(wx.showToast).toBeCalled();
    });
  });

  describe('onLoad_suc', ()=>{
    page.app.onLaunch = jest.fn();
    let res = {statusCode: 500};
    page.onLoad_suc(res);
    res = {statusCode: 0};
    page.onLoad_suc(res);
    it('onLoad_suc()', () => {
      expect(page.setData).toBeCalled();
    });
  });
})