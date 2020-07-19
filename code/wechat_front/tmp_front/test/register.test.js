import '../pages/register/register'

const page = global.wxPageInstance;

describe('register', ()=>{
  describe('onChoose', ()=>{
    jest.spyOn(page,'setData')
    const event = {detail: 0};
    page.onChoose(event);
    it('onChoose()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onInput', ()=>{
    jest.spyOn(page,'setData')
    const event = {detail: 0};
    page.onInput(event);
    it('onInput()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onChange', ()=>{
    jest.spyOn(page,'setData')
    const event = {detail: 0};
    page.onChange(event);
    it('onChange()', () => {
      expect(page.setData).toBeCalled();
    });
  });

  describe('onClick', ()=>{
    jest.spyOn(page,'setData')
    const event = {currentTarget: {dataset: []}};
    page.onClick(event);
    it('onClick()', () => {
      expect(page.setData).toBeCalled();
    });
  });


  describe('onCommit', ()=>{
    page.data.value = "";
    page.onCommit();
    it('onCommit()0', () => {
      expect(wx.showToast).toBeCalled();
    });

    page.data.value = "1";
    page.data.radio = "-1"
    page.onCommit();
    it('onCommit()1', () => {
      expect(wx.showToast).toBeCalled();
    });

    page.data.value = "1";
    page.data.radio = "1";
    page.data.type = "err";
    page.onCommit();
    it('onCommit()2', () => {
      expect(wx.showToast).toBeCalled();
    });

    page.data.value = "1";
    page.data.radio = "1";
    page.data.type = "else";
    page.onCommit();
    it('onCommit()3', () => {
      expect(wx.request).toBeCalled();
    });

  });

  describe('onCommit_suc', ()=>{
    let res = {data: {code: 200}};
    page.onCommit_suc(res);
    it('onClick()', () => {
      expect(wx.navigateTo).toBeCalled();
    });

    res = {data: {code: 201}};
    page.onCommit_suc(res);
    it('onClick()', () => {
      expect(wx.navigateTo).toBeCalled();
    });

    res = {data: {code: 400}};
    page.onCommit_suc(res);
    it('onClick()', () => {
      expect(wx.showToast).toBeCalled();
    });

    res = {data: {code: 401}};
    page.onCommit_suc(res);
    it('onClick()', () => {
      expect(wx.showToast).toBeCalled();
    });
  });
})