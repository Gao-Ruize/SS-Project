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
    await page.waitFor(1500);
    const currentPage = await miniProgram.currentPage();
    // console.log(desc3);
    // console.log(currentPage.path);
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

    let mockTutors = [{
        'tutorid': 1,
        'tutorid': 'tutorid1',
        'tutorname': 'tutorname1',
        'uid': 'uid1'
      },
      {
        'tutorid': 2,
        'tutorid': 'tutorid2',
        'tutorname': 'tutorname2',
        'uid': 'uid2'
      },
      {
        'tutorid': 3,
        'tutorid': 'tutorid3',
        'tutorname': 'tutorname3',
        'uid': 'uid3'
      },
    ];
    await page.setData({
      tutors: mockTutors,
    });

    // console.log(await page.data());
    expect(await page.data('showPop')).toEqual(false);
    await selectTutorButton.tap();
    expect(await page.data('showPop')).toEqual(true);

  })


  it('stuIndex', async () => {
    let page = await miniProgram.reLaunch('/pages/messageDetail/messageDetailPage');

    let notice = await page.$('.notice');
    expect(await notice.wxml()).toContain('阅读后请点击确认按钮');

    let confirmButton = await page.$('.buttons');
    expect(await confirmButton.wxml()).toContain('确认收到')

    // console.log(await page.data());
    let info = {
      'title': 'MsgTitle',
      'content': 'MsgContent',
      'time': 'time',
      'senderName': 'sender'
    }
    await page.setData({
      infoForm: info
    });
    let showMsg = await page.$('.msgDetail');
    expect(await showMsg.wxml()).toContain('MsgTitle');
    expect(await showMsg.wxml()).toContain('MsgContent');
    expect(await showMsg.wxml()).toContain('time');
    expect(await showMsg.wxml()).toContain('sender');

  })

  it('stuMsgFromIns', async () => {
    let page = await miniProgram.reLaunch('/pages/stuMsgFromIns/stuMsgFromInsPage');

    let searchBar = await page.$('van-search');

    expect(await searchBar.wxml()).toContain('请输入搜索关键词');
    let input = await searchBar.$('input');
    // await input.input('Input String');
    // expect(await searchBar.wxml()).toContain('Input String');

    let mockData = [
      {'releasetime': 'release time 1', 'id': '1', 'title': 'MsgTitle1', 'ifRead': true},
      {'releasetime': 'release time 2', 'id': '2', 'title': 'MsgTitle2', 'ifRead': false},
      {'releasetime': 'release time 3', 'id': '3', 'title': 'MsgTitle3', 'ifRead': true},
    ];
    page.setData({
      showMsgs: mockData,
    })

    let msgs =await page.$$('van-cell');

    expect(await msgs[3].wxml()).toContain('release time 3');
    expect(await msgs[1].wxml()).toContain('MsgTitle1');

    await msgs[1].tap();
    await page.waitFor(1500);
    const currentPage = await miniProgram.currentPage();
    expect(currentPage.path).toContain('pages/messageDetail/messageDetailPage');
    await miniProgram.navigateBack();
  }, 30000)

  it('stuMsgFromJwcPage', async () =>{
    let page = await miniProgram.reLaunch('/pages/stuMsgFromJwcPage/stuMsgFromJwcPage');

    let mockData = [
      {'releasetime': 'release time 1', 'id': '1', 'title': 'MsgTitle1', 'ifRead': true},
      {'releasetime': 'release time 2', 'id': '2', 'title': 'MsgTitle2', 'ifRead': false},
      {'releasetime': 'release time 3', 'id': '3', 'title': 'MsgTitle3', 'ifRead': true},
    ];
    page.setData({
      showMsgs: mockData,
    })

    let msgs =await page.$$('van-cell');

    expect(await msgs[3].wxml()).toContain('release time 3');
    expect(await msgs[1].wxml()).toContain('MsgTitle1');

    await msgs[1].tap();
    await page.waitFor(1500);
    const currentPage = await miniProgram.currentPage();
    expect(currentPage.path).toContain('pages/messageDetail/messageDetailPage');
    await miniProgram.navigateBack();

  }, 30000)

  it('stuMsgFromTutorPage', async () => {
    let page = await miniProgram.reLaunch('/pages/stuMsgFromTutorPage/stuMsgFromTutorPage');

    let mockShowSelect = [
      {'title': 'select Tutor', 'content': 'select Tutor Content', 'phase': 0}
    ];
    page.setData({
      showSelect: mockShowSelect,
    });

    let mockShowProposal = [
      {'title': 'Proposal', 'content': 'Proposal Content', 'phase': 1}
    ];
    page.setData({
      showProposal: mockShowProposal,
    });

    let mockShowMiddle = [
      {'title': 'Middle', 'content': 'Middle Content', 'phase': 2}
    ];
    page.setData({
      showMiddle: mockShowMiddle,
    });

    let mockShowReply = [
      {'title': 'Reply', 'content': 'Reply Content', 'phase': 3}
    ];
    page.setData({
      showReply: mockShowReply,
    });

    let mockShowPigeonhole = [
      {'title': 'Pigeonhole', 'content': 'Pigeonhole Content', 'phase': 4}
    ];
    page.setData({
      showPigeonhole: mockShowPigeonhole,
    });

    let searchBar = await page.$('van-search');

    expect(await searchBar.wxml()).toContain('请输入搜索关键词');
    let input = await searchBar.$('input');
    // await input.input('Input String');
    // expect(await searchBar.wxml()).toContain('Input String');

    let contents =await page.$$('van-panel');
    //console.log(contents);
    expect(await contents[0].wxml()).toContain('select Tutor');
    expect(await contents[2].wxml()).toContain('Middle Content');

  }, 30000)

  it('tutorHistoryMsgPage', async () => {
    let page = await miniProgram.reLaunch('/pages/tutorHistoryMsgPage/tutorHistoryMsgPage');

    let searchBar = await page.$('van-search');
    expect(await searchBar.wxml()).toContain('通过时间或项目名搜索');
    let input = await searchBar.$('input');
    // await input.input('Input String');
    // expect(await searchBar.wxml()).toContain('Input String');

    let mockShowItems = [{
        'id': '1',
        'title': 'Title1',
        'releasetime': 'ReleaseTime1'
      },
      {
        'id': '2',
        'title': 'Title2',
        'releasetime': 'ReleaseTime2'
      },
    ];
    page.setData({
      showItems: mockShowItems,
    })

    let items = await page.$$('van-cell');
    expect(await items[1].wxml()).toContain('Title1');
    expect(await items[2].wxml()).toContain('ReleaseTime2');
    expect(await items[1].wxml()).toContain('查看详情');

  }, 30000)

  it('tutorHistoryMsgDetailPage', async () => {
    let page = await miniProgram.reLaunch('/pages/tutorHistoryMsgDetailPage/tutorHistoryMsgDetailPage');

    let searchBar = await page.$('van-search');
    expect(await searchBar.wxml()).toContain('通过学生姓名或学号搜索');
    let input = await searchBar.$('input');
    // await input.input('Input String');
    // expect(await searchBar.wxml()).toContain('Input String');

    let mockShowStudents = [{
        'studentid': '1',
        'studentname': 'name1',
        'ifRead': false
      },
      {
        'studentid': '2',
        'studentname': 'name2',
        'ifRead': false
      },
      {
        'studnetid': '3',
        'studentname': 'name3',
        'ifRead': true
      },
    ];
    page.setData({
      showStudents: mockShowStudents
    })
    console.log(await page.data());
    // console.log(await page.$$('van-cell'))

    let mockUnreadStudentInfo = [{
        studentid: '1',
        studentname: 's_name1'
      },
      {
        studentid: '2',
        studentname: 's_name2'
      },
    ];
    page.setData({
      unreadStudentInfo: mockUnreadStudentInfo,
    })

  }, 30000)

  it('tutorMsgFromJwcPage', async () => {
    let page = await miniProgram.reLaunch('/pages/tutorMsgFromJwcPage/tutorMsgFromJwcPage');

    let searchBar = await page.$('van-search');
    expect(await searchBar.wxml()).toContain('请输入搜索标题或时间');
    let input = await searchBar.$('input');
    // await input.input('Input String');
    // expect(await searchBar.wxml()).toContain('Input String');

    let mockShowMsgs = [{
        releasetime: 'time1',
        id: '1',
        title: 'Title1',
        ifRead: true
      },
      {
        releasetime: 'time2',
        id: '2',
        title: 'Title2',
        ifRead: false
      },
    ];
    page.setData({
      showMsgs: mockShowMsgs,
    });

    let msgs = await page.$$('van-cell');
    expect(await msgs[1].wxml()).toContain('time1');
    expect(await msgs[2].wxml()).toContain('Title2');

    await msgs[1].tap();
    await page.waitFor(1500);
    const currentPage = await miniProgram.currentPage();
    expect(currentPage.path).toContain('pages/messageDetail/messageDetailPage');
    await miniProgram.navigateBack();

  }, 30000)

  it('tutorSendMsgPage', async () => {
    let page = await miniProgram.reLaunch('/pages/tutorSendMsgPage/tutorSendMsgPage');

    let mockList = [
      {studentid: 1, studentname: 'student1'},
      {studentid: 2, studentname: 'student2'},
    ];
    page.setData({
      list: mockList,
    })

    let checkboxes = await page.$$('van-checkbox');
    let oneCheckBox = await checkboxes[1].$('.van-checkbox__label')
    await oneCheckBox.tap();

    let button = await page.$('van-button');
    await button.tap();
    await page.waitFor(1500);
    const currentPage = await miniProgram.currentPage();
    expect(currentPage.path).toContain('pages/tutorSendMsgDetailPage/tutorSendMsgDetailPage');
    let ids = await currentPage.$('.TargetText');
    expect(await ids.wxml()).toContain('2');
    await miniProgram.navigateBack();

  }, 30000)

  it('tutorSendMsgDetailPage', async () => {
    let page = await miniProgram.reLaunch('/pages/tutorSendMsgDetailPage/tutorSendMsgDetailPage');

    let TargetText = await page.$('.TargetText');
    expect(await TargetText.wxml()).toContain('即将发送给以下几位同学');
    let TimeText = await page.$('.TimeText');
    expect(await TimeText.wxml()).toContain('选择发送的时间为');

    let van_field = await page.$('van-field');
    expect(await van_field.wxml()).toContain('请输入标题');
    let detailText = await page.$('.detailText');
    expect(await detailText.wxml()).toContain('输入您将要发送的消息内容');
    
    let ConfirmButton = await page.$('.confirm');
    await ConfirmButton.tap();
    await page.waitFor(1500);
    const currentPage = await miniProgram.currentPage();
    expect(currentPage.path).toContain('pages/tutorSendMsgPage/tutorSendMsgPage');
    await miniProgram.navigateBack();

  }, 30000)

})