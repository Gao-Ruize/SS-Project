from setting import LOGIN_FORM_DATA, HEADER
from bs4 import BeautifulSoup
import requests


basic_url = 'http://bysj.jwc.sjtu.edu.cn/index.aspx'

session = requests.Session()
x = session.post(basic_url, headers=HEADER, data=LOGIN_FORM_DATA)
print(x.content)
soup = BeautifulSoup(x.content, 'html.parser')
print(soup.prettify())
# y = session.post(url1, headers=HEADER)
# print(y.cookies)

# response = session.get('http://bysj.jwc.sjtu.edu.cn/ZhuanYe/RepertRenWu.aspx?zyno=537&sid=Jk7UDmg/cSOg6wXocnYmog....&screen=Qd2HRMrs8oJGtA82doE7Jg....', headers=HEADER)
# soup = BeautifulSoup(response.content, 'html.parser')
# print(soup.prettify())




