import puppeteer from 'puppeteer';

describe('Login', () => {
  it('login success', async () => {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto('http://localhost:8000/user/login');
    await page.waitFor(500);
    await page.type('#username', 'admin');
    await page.type('#password', 'administrator');
    await page.click('button[type="submit"]');
    // await page.waitForSelector('.ant-alert-error');
    await page.waitFor(1500);
    // console.log(await page.url());
    expect(await page.url()).toBe('http://localhost:8000/msg#/');
    await page.close();
    await browser.close();
  });

  it('login fail', async () => {
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto('http://localhost:8000/user/login');
    await page.waitFor(500);
    await page.type('#username', 'wrongname');
    await page.type('#username', 'wrongpsw');
    await page.click('button[type="submit"]');
    await page.waitFor(1500);
    expect(await page.url()).toBe('http://localhost:8000/user/login')
    // await page.waitForSelector('.ant-alert-error'); // should display error
    await page.close();
    await browser.close();
  })
});
