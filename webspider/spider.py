from selenium import webdriver
from setting import LOGIN_USER, LOGIN_PWD, DRIVER_PATH
from logconfig import MyLogging
import re
import time


class Spider:
    def __init__(self, root):
        self.option = webdriver.ChromeOptions()
        self.option.add_argument('headless')
        self.driver = webdriver.Chrome(DRIVER_PATH, chrome_options=self.option)
        self.root = root

    def __grabDataPhase1(self):
        self.driver.get('http://bysj.jwc.sjtu.edu.cn/')
        time.sleep(2)  # 等待缓冲
        UserId = self.driver.find_element_by_id("UserId")
        Pwd = self.driver.find_element_by_id("Pwd")
        UserId.send_keys(LOGIN_USER)
        Pwd.send_keys(LOGIN_PWD)
        Button = self.driver.find_element_by_id("LoginButton")
        Button.click()
        time.sleep(2)  # 等待缓冲
        Principal = self.driver.find_element_by_id("ImageButton4")  # 打开“专业负责人”界面 爬取任务书
        Principal.click()
        time.sleep(2)  # 等待缓冲
        Topic = self.driver.find_element_by_xpath('//*[@id="divzy1"]/table/tbody/tr[6]/td/a')
        Topic.click()
        time.sleep(2)  # 等待缓冲
        self.driver.switch_to.frame("iframe2")  # 需要切换框架
        Step = self.driver.find_element_by_xpath('/html/body/form/table[2]/tbody/tr[1]/td/div/div/table/tbody/tr[2]/td[6]/a')
        Step.click()
        time.sleep(2)  # 等待缓冲
        PageNum = self.driver.find_element_by_id('LblPageCount')
        pattern = re.compile(r'\d+')
        result1 = pattern.findall(PageNum.text)
        pageNum = int(result1[0])
        for i in range(1, pageNum):
            filename = self.root + '/selectPhase/' + str(i) + '.txt'
            file = open(filename, mode='w')
            TableInfo = self.driver.find_element_by_xpath('//*[@id="gridview"]')
            file.write(TableInfo.text)
            file.close()
            NextPage = self.driver.find_element_by_xpath('//*[@id="btnNext"]')
            NextPage.click()
            time.sleep(1)
        TableInfo = self.driver.find_element_by_xpath('//*[@id="gridview"]')
        filename = self.root + '/selectPhase/' + str(pageNum) + '.txt'
        file = open(filename, mode='w')
        file.write(TableInfo.text)
        file.close()
        MyLogging.write_logger("Phase1 finished")

    def __grabDataPhase2(self):
        self.driver.get('http://bysj.jwc.sjtu.edu.cn/')
        time.sleep(2)  # 等待缓冲
        UserId = self.driver.find_element_by_id("UserId")
        Pwd = self.driver.find_element_by_id("Pwd")
        UserId.send_keys(LOGIN_USER)
        Pwd.send_keys(LOGIN_PWD)
        Button = self.driver.find_element_by_id("LoginButton")
        Button.click()
        time.sleep(2)  # 等待缓冲
        Principal = self.driver.find_element_by_id("ImageButton4")  # 打开“专业负责人”界面 爬取任务书
        Principal.click()
        time.sleep(2)  # 等待缓冲
        Topic = self.driver.find_element_by_xpath('//*[@id="divzy1"]/table/tbody/tr[7]/td/a')  # 打开开题报告界面 爬取开题报告
        Topic.click()
        time.sleep(2)  # 等待缓冲

        self.driver.switch_to.frame("iframe2")  # 需要切换框架
        Step = self.driver.find_element_by_xpath('/html/body/form/table[2]/tbody/tr[1]/td/div/div/table/tbody/tr[2]/td[9]/a')
        Step.click()
        time.sleep(2)  # 等待缓冲

        PageNum = self.driver.find_element_by_id('LblPageCount')
        pattern = re.compile(r'\d+')
        result1 = pattern.findall(PageNum.text)
        pageNum = int(result1[0])

        for i in range(1, pageNum):
            filename = self.root + '/reportPhase/' + str(i) + '.txt'
            file = open(filename, mode='w')
            TableInfo = self.driver.find_element_by_xpath('//*[@id="gridview"]')
            file.write(TableInfo.text)
            file.close()
            NextPage = self.driver.find_element_by_xpath('//*[@id="btnNext"]')
            NextPage.click()
            time.sleep(1)

        TableInfo = self.driver.find_element_by_xpath('//*[@id="gridview"]')
        filename = self.root + '/reportPhase/' + str(pageNum) + '.txt'
        file = open(filename, mode='w')
        file.write(TableInfo.text)
        file.close()
        MyLogging.write_logger("Phase2 finished")

    def __grabDataPhase3(self):
        self.driver.get('http://bysj.jwc.sjtu.edu.cn/')
        time.sleep(2)  # 等待缓冲
        UserId = self.driver.find_element_by_id("UserId")
        Pwd = self.driver.find_element_by_id("Pwd")
        UserId.send_keys(LOGIN_USER)
        Pwd.send_keys(LOGIN_PWD)
        Button = self.driver.find_element_by_id("LoginButton")
        Button.click()
        time.sleep(2)  # 等待缓冲
        Principal = self.driver.find_element_by_id("ImageButton4")  # 打开“专业负责人”界面 爬取任务书
        Principal.click()
        time.sleep(2)  # 等待缓冲

        Topic = self.driver.find_element_by_xpath('//*[@id="divzy1"]/table/tbody/tr[8]/td/a')  # 打开中期检查界面 爬取开题报告
        Topic.click()
        time.sleep(2)  # 等待缓冲

        self.driver.switch_to.frame("iframe2")  # 需要切换框架
        Step = self.driver.find_element_by_xpath('//*[@id="gridview"]/tbody/tr[2]/td[9]/a')
        Step.click()
        time.sleep(2)  # 等待缓冲

        PageNum = self.driver.find_element_by_id('LblPageCount')
        pattern = re.compile(r'\d+')
        result1 = pattern.findall(PageNum.text)
        pageNum = int(result1[0])

        for i in range(1, pageNum):
            filename = self.root + '/middlePhase/' + str(i) + '.txt'
            file = open(filename, mode='w')
            TableInfo = self.driver.find_element_by_xpath('//*[@id="gridview"]')
            file.write(TableInfo.text)
            file.close()
            NextPage = self.driver.find_element_by_xpath('//*[@id="btnNext"]')
            NextPage.click()
            time.sleep(1)

        # 单独爬取最后一页
        TableInfo = self.driver.find_element_by_xpath('//*[@id="gridview"]')
        filename = self.root + '/middlePhase/' + str(pageNum) + '.txt'
        file = open(filename, mode='w')
        file.write(TableInfo.text)
        file.close()
        MyLogging.write_logger("Phase3 finished")

    def grabData(self):
        self.__grabDataPhase1()
        self.__grabDataPhase2()
        self.__grabDataPhase3()
        self.driver.quit()
