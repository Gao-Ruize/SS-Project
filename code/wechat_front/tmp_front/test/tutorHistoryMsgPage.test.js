import '../pages/tutorHistoryMsgPage/tutorHistoryMsgPage'

const page = global.wxPageInstance;

describe('导师历史消息页面', ()=>{

  describe('on load', ()=>{
    page.onLoad();
    it('应该执行on load()', () => {
      expect(wx.request).toBeCalled();
    });
  });
  describe('onload_suc', ()=>{
    const res = {data: 0};
    jest.spyOn(page, 'setData')
    page.onLoad_suc(res);
    it('应该执行onload_suc()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onChange', () => {
    var event = {detail: 0};
    page.onChange(event);
    it('应该执行onChange()0', () => {
      expect(wx.redirectTo).toBeCalled();
    });
    event = {detail: 2};
    page.onChange(event);
    it('应该执行onChange()2', () => {
      expect(wx.redirectTo).toBeCalled();
    });
  });

  describe('searchChange', ()=>{
    jest.spyOn(page,'setData')
    const event= {detail: 1}
    page.searchChange(event);
    it('应该执行searchChange', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onSearch', ()=>{
    page.data.msgItems=[{title: "1", releasetime: "2"}];
    page.data.searchValue = "1";
    jest.spyOn(page,'setData');
    page.onSearch();
    page.data.msgItems=[{title: "2", releasetime: "1"}];
    page.onSearch();
    it('应该执行onSearch', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onCancel', ()=>{
    jest.spyOn(page,'setData');
    page.onCancel();
    it('应该执行onCancel()', () => {
      expect(page.setData).toBeCalled();
    });
  });
  
  describe('checkDetails', ()=>{
    const event = {currentTarget: {dataset: {id: 1, title: 2}}};
    page.checkDetails(event);
    it('应该执行checkDetails()', () => {
      expect(wx.navigateTo).toBeCalled();
    });
  });
})