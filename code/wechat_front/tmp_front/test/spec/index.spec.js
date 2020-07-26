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
    // await desc3.tap();
  });


  // it('messageDetail', async () => {
  //   let page = await miniProgram.reLaunch('/pages/stuChooseTutor/stuChooseTutor');
  //   // 先观察基本组件是否存在
  //   let desc1 = await page.$('.page');
  //   let desc2 = await page.$('.popup');
  //   // console.log(await desc2.wxml())
  //   expect(await desc1.wxml()).toContain('绑定导师');
  //   expect(await desc2.wxml()).toContain('overlay');
  //   // 检测按钮
  // }, 30000);


  it('stuIndex', async () => {
    let page = await miniProgram.reLaunch('/pages/stuIndex/stuIndex');
    // 先观察基本组件是否存在
    let desc1 = await page.$('.title');
    let desc2 = await page.$('.msgContainer');
    let showDetailButton = await page.$('.showDetail');

    expect(await desc1.wxml()).toContain(await page.data('phaseName'));
    expect(await desc2.wxml()).toContain('未读教务处信息');
    expect(await desc2.wxml()).toContain('未读导师信息');
    expect(await showDetailButton.wxml()).toContain('查看详情');
    // 检测按钮
    
    await showDetailButton.tap();
    await page.waitFor(3000);
    const currentPage = await miniProgram.currentPage();
    // console.log(desc3);
    console.log(currentPage.path);
    expect(currentPage.path).toContain('pages/stuMsgFromJwcPage/stuMsgFromJwcPage');
    await miniProgram.navigateBack();
  }, 30000)
  

  it('stuChooseTutor', async () => {
    let page = await miniProgram.reLaunch('/pages/stuChooseTutor/stuChooseTutor');

    let bindTutorText = await page.$('.bindTutor');
    let selectedTutorText = await page.$('.selectedTutor');
    let selectTutorButton = await page.$('.selectTutor');
    let submitTutorButton = await page.$('.buttonClass');

    expect(await bindTutorText.wxml()).toContain('绑定导师：');
    expect(await selectedTutorText.wxml()).toContain('您的导师是：');
    expect(await selectTutorButton.wxml()).toContain('选择导师');
    expect(await submitTutorButton.wxml()).toContain('确认提交');

    let mockTutors=[
      {'tutorid': 1, 'tutorid': 'tutorid1', 'tutorname': 'tutorname1', 'uid': 'uid1'},
      {'tutorid': 2, 'tutorid': 'tutorid2', 'tutorname': 'tutorname2', 'uid': 'uid2'},
      {'tutorid': 3, 'tutorid': 'tutorid3', 'tutorname': 'tutorname3', 'uid': 'uid3'},
    ];
    await page.setData({
      tutors: mockTutors,
    });

    // console.log(await page.data());
    expect(await page.data('showPop')).toEqual(false);
    await selectTutorButton.tap();
    expect(await page.data('showPop')).toEqual(true);

    let tutorPicker = await page.$('.tutorpicker');
    // console.log(tutorPicker);
    let tutorOptions = await tutorPicker.$$('view');
    // console.log(tutorOptions);
    // await tutorOptions[1].tap();

  })

  // it('stuMsgFromJwcPage', async () => {
  //   let page = await miniProgram.reLaunch('/pages/stuMsgFromJwcPage/stuMsgFromJwcPage');


  // })

})
