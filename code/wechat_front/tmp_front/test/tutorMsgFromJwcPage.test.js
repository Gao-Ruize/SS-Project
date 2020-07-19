import '../pages/tutorMsgFromJwcPage/tutorMsgFromJwcPage'

const page = global.wxPageInstance;

describe('导师接收教务处消息页面', ()=>{
  describe('onChange', () => {
    var event = {detail: 2};
    page.onChange(event);
    it('应该执行onChange()2', () => {
      expect(wx.redirectTo).toBeCalled();
    });
    event = {detail: 1};
    page.onChange(event);
    it('应该执行onChange()1', () => {
      expect(wx.redirectTo).toBeCalled();
    });
  });
  describe('showDetails', ()=>{
    const event = {currentTarget: {dataset: {id: 1}}};
    page.showDetails(event);
    it('应该执行getStudents()', () => {
      expect(wx.navigateTo).toBeCalled();
    });
  });
  describe('onDisplay', ()=>{
    beforeAll(() => {
      jest.spyOn(page, 'setData');
    });
    page.onDisplay();
    it('应该执行onDisplay()', () => {
      expect(page.setData).toBeCalled();
    });
  });
  describe('onClose', ()=>{
    beforeAll(() => {
      jest.spyOn(page, 'setData');
    });
    page.onClose();
    it('onClose()', () => {
      expect(page.setData).toBeCalled();
    });
  });
  describe('formatDate', ()=>{
    const date = new Date(100012301);
    it('formatDate()', () => {
      expect(page.formatDate(date)).toMatch(/1/);
    });
  });
  describe('onConfirm', ()=>{
    beforeAll(() => {
      jest.spyOn(page, 'setData');
      jest.spyOn(page, 'formatDate');
    });
    const event = {detail: [123,456]};
    page.onConfirm(event);
    it('onConfirm()', () => {
      expect(page.setData).toBeCalled();
    });
  });
  describe('onload', ()=>{
    page.onLoad();
    it('formatDate()', () => {
      expect(wx.request).toBeCalled;
    });
  });
})