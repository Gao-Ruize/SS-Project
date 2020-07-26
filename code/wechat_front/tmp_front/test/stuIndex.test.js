import '../pages/stuIndex/stuIndex'

const page = global.wxPageInstance;

describe('stuIndex', ()=>{
  describe('onLoad', ()=>{
    jest.spyOn(page, 'setPhaseId');
    jest.spyOn(page, 'setInsNum');
    jest.spyOn(page, 'setJwcNum');

    page.onLoad();
    it('onLoad()', () => {
      expect(page.setPhaseId).toBeCalled();
      expect(page.setInsNum).toBeCalled();
      expect(page.setJwcNum).toBeCalled();
    });
  });

  describe('setPhaseId', ()=>{
    page.setPhaseId(20200101);
    page.setPhaseId(20200302);
    page.setPhaseId(20200503);
    page.setPhaseId(20200704);
    page.setPhaseId(20200905);
    page.setPhaseId(20201331);
    page.setPhaseId();
    jest.spyOn(page,'setData')
    it('onLoad()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('toStudentPage', ()=>{
    page.toStudentPage();
    it('toStudentPage()', () => {
      expect(wx.navigateTo).toBeCalled();
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

  describe('setJwcNum_suc', ()=>{
    let res = {data: 0, statusCode: 500};
    jest.spyOn(page, 'setData')
    page.setJwcNum_suc(res);
    res = {data: 0, statusCode: 100};
    page.setJwcNum_suc(res);
    it('setJwcNum_suc()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('setInsNum_suc', ()=>{
    let res = {data: 0, statusCode: 500};
    jest.spyOn(page, 'setData')
    page.setInsNum_suc(res);
    res = {data: 0, statusCode: 100};
    page.setInsNum_suc(res);
    it('setInsNum_suc()', () => {
      expect(page.setData).toBeCalled();
    });
  });
})