import '../pages/tutorSendMsgDetailPage/tutorSendMsgDetailPage'

const page = global.wxPageInstance;

describe('导师发送具体消息页面', ()=>{
  describe('onload', ()=>{
    beforeAll(() => {
      // spyOn后可使方法具有mock属性，同时不影响方法调用。
      jest.spyOn(page, 'setData');
      page.onLoad();
    });
    it('应该执行setData', () => {
      expect(page.setData).toBeCalled();
    });
  });
  describe('onTitleChange', ()=>{
    const event = {detail: 0};
    beforeAll(() => {
      // spyOn后可使方法具有mock属性，同时不影响方法调用。
      jest.spyOn(page, 'setData');
      page.onTitleChange(event);
    });
    it('应该执行setData', () => {
      expect(page.setData).toBeCalled();
    });
  });
  describe('bindTextAreaBlur', ()=>{
    const event = {detail: {value: 0}};
    beforeAll(() => {
      // spyOn后可使方法具有mock属性，同时不影响方法调用。
      jest.spyOn(page, 'setData');
      page.bindTextAreaBlur(event);
    });
    it('应该执行setData', () => {
      expect(page.setData).toBeCalled();
    });
  });
  describe('onTimeConfirm', ()=>{
    const event = {detail: 1283718923};
    beforeAll(() => {
      // spyOn后可使方法具有mock属性，同时不影响方法调用。
      jest.spyOn(page, 'setData');
      page.onTimeConfirm(event);
    });
    it('应该执行setData', () => {
      expect(page.setData).toBeCalled();
    });
  });
  describe('bindOpenTime', ()=>{
    beforeAll(() => {
      // spyOn后可使方法具有mock属性，同时不影响方法调用。
      jest.spyOn(page, 'setData');
      page.bindOpenTime();
    });
    it('应该执行setData', () => {
      expect(page.setData).toBeCalled();
    });
  });
  describe('bindQuit', ()=>{
    beforeAll(() => {
      // spyOn后可使方法具有mock属性，同时不影响方法调用。
      page.bindQuit();
    });
    it('应该执行bindQuit', () => {
      expect(wx.request).toBeCalled();
      expect(wx.redirectTo).toBeCalled();
    });
  });

  describe('bindQuit_suc', ()=>{
    let res = {data: {code: 200}, statusCode: 500};
    page.bindQuit_suc(res);
    it('应该执行bindQuit_suc1', () => {
      expect(wx.showToast).toBeCalled();
    });
    res = {data: {code: 200}, statusCode: 100};
    page.bindQuit_suc(res);
    it('应该执行bindQuit_suc1', () => {
      expect(wx.showToast).toBeCalled();
    });
    res = {data: {code: 400}, statusCode: 100};
    page.bindQuit_suc(res);
    it('应该执行bindQuit_suc1', () => {
      expect(wx.showToast).toBeCalled();
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
})
