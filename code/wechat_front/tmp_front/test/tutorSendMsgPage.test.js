import '../pages/tutorSendMsgPage/tutorSendMsgPage'

const page = global.wxPageInstance;

describe('导师发送消息页面', ()=>{
  describe('on load', ()=>{
    beforeAll(() => {
      // spyOn后可使方法具有mock属性，同时不影响方法调用。
      jest.spyOn(page, 'getStudents');
      // 执行页面onLoad生命周期。
      page.onLoad();
    });
    it('应该执行getStudents()', () => {
      expect(page.getStudents).toBeCalled();
    });
  });
  describe('changetomessage', () => {
    page.data.result=["111111"];
    page.changetomessage();
    it('应该执行changetomessage()', () => {
      expect(wx.setStorageSync).toBeCalled();
      expect(wx.navigateTo).toBeCalled();
    });
  });
  describe('onChangeCheck', () => {
    const event = {detail: "1"};
    page.onChangeCheck(event);
    it('应该执行onChangeCheck()', () => {
      expect(page.data.result).toEqual('1');
    });
  });
  describe('onChange', () => {
    var event = {detail: 0};
    page.onChange(event);
    it('应该执行onChange()0', () => {
      expect(wx.redirectTo).toBeCalled();
    });
    event = {detail: 1};
    page.onChange(event);
    it('应该执行onChange()1', () => {
      expect(wx.redirectTo).toBeCalled();
    });
  });

  describe('getStudents_suc', ()=>{
    const res = {data: 0};
    jest.spyOn(page,'setData');
    page.getStudents_suc(res);
    it('应该执行setData()', () => {
      expect(page.setData).toBeCalled();
    });
  });
})