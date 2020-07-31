import puppeteer from 'puppeteer';

describe('MsgList', () => {
  let browser;
  let page;

  beforeAll(async () => {
    browser = await puppeteer.launch();
    page = await browser.newPage();

    await page.goto('http://localhost:8000/user/login');
    await page.waitFor(500);
    await page.type('#username', 'admin');
    await page.type('#password', 'administrator');
    await page.click('button[type="submit"]');

    await page.waitFor(1500);
    // console.log(await page.url());
    expect(await page.url()).toBe('http://localhost:8000/msg#/');
    // const showUsers = await page.$$('.ant-menu-item');
    // await showUsers[0].click();
    // console.log(await showUsers[0].contentFrame());
    await page.waitFor(500);

  });
  afterAll(async () => {
    await page.close();
    await browser.close();
  })

  it('show data', async () => {
    const msgs = await page.$$('.ant-table-row');
    // console.log(await page.content());
    console.log(msgs.length);

    // expect(msgs.length).toBe(4);
  });


  // it('select by key word', async () => {
  //   const msgs = await page.$$('.ant-table-row');
  //   console.log(msgs.length);
  //   expect(msgs.length).toBe(4);
  //
  //   await page.type('.ant-input', '毕设开题');
  //   await page.click('.ant-btn');
  //   const msgsAfterKeyword = await page.$$('.ant-table-row');
  //   console.log(msgsAfterKeyword.length);
  //   expect(msgsAfterKeyword.length).toBe(2);
  // });

});
