# coding=gbk
from selenium import webdriver
from setting import LOGIN_USERID, LOGIN_PWD
import re
import time

option = webdriver.ChromeOptions()
option.add_argument('headless')
driver = webdriver.Chrome("E:\seproject\chromedriver.exe", chrome_options=option)
driver.get('http://bysj.jwc.sjtu.edu.cn/')
time.sleep(2)  # 等待缓冲

UserId = driver.find_element_by_id("UserId")
Pwd = driver.find_element_by_id("Pwd")
UserId.send_keys(LOGIN_USERID)
Pwd.send_keys(LOGIN_PWD)
Button = driver.find_element_by_id("LoginButton")
Button.click()
time.sleep(2)  # 等待缓冲

Principal = driver.find_element_by_id("ImageButton4")  # 打开“专业负责人”界面 爬取任务书
Principal.click()
time.sleep(2)  # 等待缓冲

Topic = driver.find_element_by_xpath('//*[@id="divzy1"]/table/tbody/tr[8]/td/a') # 打开中期检查界面 爬取开题报告
Topic.click()
time.sleep(2)  # 等待缓冲

driver.switch_to.frame("iframe2")  # 需要切换框架
Step = driver.find_element_by_xpath('//*[@id="gridview"]/tbody/tr[2]/td[9]/a')
Step.click()
time.sleep(2)  # 等待缓冲

PageNum = driver.find_element_by_id('LblPageCount')
pattern = re.compile(r'\d+')
result1 = pattern.findall(PageNum.text)
pageNum = int(result1[0])

for i in range(1, pageNum):
    filename = 'data/middlePhase/' + str(i) + '.txt'
    file = open(filename, mode='w')
    TableInfo = driver.find_element_by_xpath('//*[@id="gridview"]')
    file.write(TableInfo.text)
    file.close()
    NextPage = driver.find_element_by_xpath('//*[@id="btnNext"]')
    NextPage.click()
    time.sleep(1)

# 单独爬取最后一页
TableInfo = driver.find_element_by_xpath('//*[@id="gridview"]')
filename = 'data/middlePhase/' + str(pageNum) + '.txt'
file = open(filename, mode='w')
file.write(TableInfo.text)
file.close()
driver.quit()
