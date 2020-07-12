# coding=gbk
from selenium import webdriver
from setting import LOGIN_USERID, LOGIN_PWD
import re
import time

option = webdriver.ChromeOptions()
option.add_argument('headless')
driver = webdriver.Chrome("E:\seproject\chromedriver.exe", chrome_options=option)
driver.get('http://bysj.jwc.sjtu.edu.cn/')
time.sleep(2)  # �ȴ�����

UserId = driver.find_element_by_id("UserId")
Pwd = driver.find_element_by_id("Pwd")
UserId.send_keys(LOGIN_USERID)
Pwd.send_keys(LOGIN_PWD)
Button = driver.find_element_by_id("LoginButton")
Button.click()
time.sleep(2)  # �ȴ�����

Principal = driver.find_element_by_id("ImageButton4")  # �򿪡�רҵ�����ˡ����� ��ȡ������
Principal.click()
time.sleep(2)  # �ȴ�����

Topic = driver.find_element_by_xpath('//*[@id="divzy1"]/table/tbody/tr[8]/td/a') # �����ڼ����� ��ȡ���ⱨ��
Topic.click()
time.sleep(2)  # �ȴ�����

driver.switch_to.frame("iframe2")  # ��Ҫ�л����
Step = driver.find_element_by_xpath('//*[@id="gridview"]/tbody/tr[2]/td[9]/a')
Step.click()
time.sleep(2)  # �ȴ�����

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

# ������ȡ���һҳ
TableInfo = driver.find_element_by_xpath('//*[@id="gridview"]')
filename = 'data/middlePhase/' + str(pageNum) + '.txt'
file = open(filename, mode='w')
file.write(TableInfo.text)
file.close()
driver.quit()
