import automator from 'miniprogram-automator';
import { expect } from 'chai';

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
    expect(await desc1.wxml()).contain('注册');
    expect(await desc2.wxml()).contain('请输入学号/工号');
    expect(await desc3.wxml()).contain('确认');
  })
  
})
