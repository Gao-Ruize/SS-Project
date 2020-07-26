const automater = require('miniprogram-automator');

describe('stuMsgFromJwcPage测试', () => {
  let miniProgram;
  
  beforeAll(async () => {
    miniProgram = await automater.connect({
      wsEndpoint: 'ws://localhost:9420',
    });
  });
  afterAll(() => {
    miniProgram.disconnect();
  });

  it('输入框检测', async () => {
    const page = await miniProgram.reLaunch('/pages/stuMsgFromJwcPage/stuMsgFromJwcPage');
    // const searchComp = await page.$('van-search');
    // console.log(await searchComp.wxml());
    const oneMsg = await page.$('.serchBar');
    console.log(oneMsg);
    // await oneMsg.input("ab");
    await page.waitFor(1500);
    const currentPage = await miniProgram.currentPage();
    console.log(currentPage.path);
  })
})

