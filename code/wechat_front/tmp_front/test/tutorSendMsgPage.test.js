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
    page.data.list = [{studentId:"111111", studentname: "1"}];
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

  describe('errCheck', ()=>{
    let res = {statusCode: 500};
    page.errCheck(res);
    res = {statusCode: 0};
    page.errCheck(res);
    it('应该执行errCheck()', () => {
      expect(wx.showToast).toBeCalled();
    });
  });

  describe('setJwcCount_suc', ()=>{
    page.app.onLaunch = jest.fn();
    let res = {statusCode: 500};
    page.setJwcCount_suc(res);
    res = {statusCode: 0};
    page.setJwcCount_suc(res);
    it('setJwcCount_suc()', () => {
      expect(wx.showToast).toBeCalled();
    });
  });

  describe('getStudents_suc', ()=>{
    jest.spyOn(page,'setData')
    let res = {statusCode: 500};
    page.getStudents_suc(res);
    res = {statusCode: 0};
    page.getStudents_suc(res);
    it('getStudents_suc()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onLoad_suc', ()=>{
    jest.spyOn(page,'setData')
    let res = {statusCode: 500};
    page.onLoad_suc(res);
    res = {statusCode: 0};
    page.onLoad_suc(res);
    it('onLoad_suc()', () => {
      expect(page.setData).toBeCalled();
    });
  });
})