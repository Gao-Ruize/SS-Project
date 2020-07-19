import '../pages/stuMsgFromTutorPage/stuMsgFromTutorPage'

const page = global.wxPageInstance;

describe('学生收到导师消息页面', ()=>{
  describe('on load', ()=>{
    page.data.hasNewInfo = true;
    jest.spyOn(page, 'setData');
    page.onLoad();
    page.data.hasNewInfo = false;
    page.onLoad();
    it('应该执行setData()', () => {
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

  describe('onSearch', ()=>{
    const event = {detail: "1"};
    page.data.selectPhase = [{detail: "1"},{detail: "0"}];
    page.data.ProposalPhase = [{detail: "1"},{detail: "0"}];
    page.data.MiddlePhase = [{detail: "1"},{detail: "0"}];
    page.data.replyPhase = [{detail: "1"},{detail: "0"}];
    page.data.pigeonholePhase = [{detail: "1"},{detail: "0"}];
    jest.spyOn(page, 'setData');
    page.onSearch(event);
    it('onSearch()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onCancel', ()=>{
    beforeAll(() => {
      jest.spyOn(page, 'setData');
      page.onCancel();
    });
    it('onCancel()', () => {
      expect(page.setData).toBeCalled();
    });
  });
})