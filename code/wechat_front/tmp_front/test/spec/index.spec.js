import automator from 'miniprogram-automator';

describe('index', () => {
  let miniProgram
  beforeAll(async () => {
    miniProgram = await automator.connect({
      wsEndpoint: 'ws://localhost:9420',
    })
  });
  afterAll(() => {
    miniProgram.disconnect();
  });
  it('register', async () => {
    const page = await miniProgram.reLaunch('/pages/register/register');
    // 先观察基本组件是否存在
    const desc1 = await page.$('.titleText');
    const desc2 = await page.$('.inputForm');
    const desc3 = await page.$('.bottomButton');
    // console.log(await desc3.wxml())
    expect(await desc1.wxml()).toContain('注册');
    expect(await desc2.wxml()).toContain('请输入学号/工号');
    expect(await desc3.wxml()).toContain('确认');

    // 检测按钮
  });

  it('messageDetail', async () => {
    let page = await miniProgram.reLaunch('/pages/stuChooseTutor/stuChooseTutor');
    // 先观察基本组件是否存在
    let desc1 = await page.$('.page');
    let desc2 = await page.$('.popup');
    // console.log(await desc2.wxml())
    expect(await desc1.wxml()).toContain('绑定导师');
    expect(await desc2.wxml()).toContain('overlay');
    // 检测按钮
  }, 30000);

  it('stuIndex', async () => {
    let page = await miniProgram.reLaunch('/pages/stuIndex/stuIndex');
    // 先观察基本组件是否存在
    let desc1 = await page.$('.title');
    let desc2 = await page.$('.msgContainer');
    let desc3 = await page.$('.buttonContainer');

    expect(await desc1.wxml()).toContain(await page.data('phaseName'));
    expect(await desc2.wxml()).toContain('未读教务处信息');
    expect(await desc3.wxml()).toContain('查看详情');
    // 检测按钮
  }, 30000)
  
})
