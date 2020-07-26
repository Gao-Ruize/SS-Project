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

  describe('errCheck', ()=>{
    let res = {statusCode: 500};
    page.errCheck(res);
    res = {statusCode: 0};
    page.errCheck(res);
    it('应该执行errCheck()', () => {
      expect(wx.showToast).toBeCalled();
    });
  });

  describe('searchChange', ()=>{
    jest.spyOn(page, 'setData')
    let res = {detail: 500};
    page.searchChange(res);
    res = {detail: 0};
    page.errCheck(res);
    it('searchChange()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onSearch', ()=>{
    page.data.searchValue = "1";
    page.data.allMsgs = [{title: "1", releasetime: "2"}, {title: "2", releasetime: "1"}];
    page.onSearch();
    it('onSearch()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onCancel', ()=>{
    page.data.searchValue = "1";
    page.data.allMsgs = [{title: "1", releasetime: "2"}, {title: "2", releasetime: "1"}];
    page.onCancel();
    it('onCancel()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('setJwcCount_suc', ()=>{
    page.app.onLaunch = jest.fn();
    let res = {statusCode: 500};
    page.setJwcCount_suc(res);
    res = {statusCode: 0};
    page.setJwcCount_suc(res);
    it('setJwcCount_suc()', () => {
      expect(page.setData).toBeCalled();
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