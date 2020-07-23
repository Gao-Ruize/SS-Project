const automator = require('miniprogram-automator');

describe('课堂小程序自动化测试', () => {
  let miniProgram;
  // 运行测试前调用
  beforeAll(async () => {
    miniProgram = await automator.connect({
      wsEndpoint: 'ws://localhost:9420',
    });
  });
  // 运行测试后调用
  afterAll(() => {
    miniProgram.disconnect();
  });
  // 测试内容
  it('nohost检测', async () => {
    const page = await miniProgram.reLaunch('/pages/index/index');
    const nohostButton = await page.$('nohost');
    expect(nohostButton).toBeNull();
  });
});
