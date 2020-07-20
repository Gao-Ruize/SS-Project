import '../pages/stuChooseTutor/stuChooseTutor'

const page = global.wxPageInstance;

describe('stuChooseTutor', ()=>{
  describe('chooseTutor', ()=>{
    jest.spyOn(page,'setData')
    const event = {currentTarget: {dataset: {tutorid: 1, tutorname: 2}}};
    page.chooseTutor(event);
    it('chooseTutor()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('guide', ()=>{
    let event = {detail: 1};
    page.guide(event);
    event = {detail: 0};
    page.guide(event);

    it('guide()', () => {
      expect(wx.redirectTo).toBeCalled();
    });
  });

  describe('bindOpenTutor', ()=>{
    jest.spyOn(page,'setData');
    page.bindOpenTutor();
    it('guide()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onClose', ()=>{
    jest.spyOn(page,'setData');
    page.onClose();
    it('guide()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onLoad', ()=>{
    page.onLoad();
    it('onLoad()', () => {
      expect(wx.request).toBeCalled();
    });
  });

  describe('bindConfirm', ()=>{
    page.data.tutorid = '';
    page.bindConfirm();
    it('bindConfirm()0', () => {
      expect(wx.showToast).toBeCalled();
    });
    page.data.tutorid = '1';
    page.bindConfirm();
    it('bindConfirm()1', () => {
      expect(wx.request).toBeCalled();
    });
  });

  describe('bindConfirm_suc', ()=>{
    let res = {data: {code: 200}};
    page.bindConfirm_suc(res);
    it('bindConfirm_suc()1', () => {
      expect(wx.showToast).toBeCalled();
    });

    res = {data: {code: 400}};
    page.bindConfirm_suc(res);
    it('bindConfirm_suc()2', () => {
      expect(wx.showToast).toBeCalled();
    });
  });

})