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
    let res = {data: {code: 200}};
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

    res = {data: {code: 400}};
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
})