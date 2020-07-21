import '../pages/stuMsgFromJwcPage/stuMsgFromJwcPage'

const page = global.wxPageInstance;

describe('学生接收教务处消息页面', ()=>{

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

  describe('onSearch', ()=>{
    page.data.allMsgs = [{title: "1", releasetime: "2"}];
    page.data.searchvalue = "1";
    jest.spyOn(page, 'setData');
    page.onSearch();
    page.data.allMsgs = [{title: "2", releasetime: "1"}];
    page.onSearch();
    page.data.allMsgs = [{title: "2", releasetime: "2"}];
    page.onSearch();
    it('onSearch()', () => {
      expect(page.setData).toBeCalled;
    });
  });

  describe('searchChange', ()=>{
    const event = {detail: 1};
    jest.spyOn(page, 'setData');
    page.searchChange(event);
    it('searchChange()', () => {
      expect(page.setData).toBeCalled;
    });
  });

  describe('onCancel', ()=>{
    const event = {allMsgs: 1};
    jest.spyOn(page, 'setData');
    page.onCancel(event);
    it('onCancel()', () => {
      expect(page.setData).toBeCalled;
    });
  });
})