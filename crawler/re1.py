# coding=gbk
import re
a = '23 HOKHY TANN'
b = '(516030910257) 文件系统中关于并发条件的自动化证明 软件工程 王肇国(zhaoguowang) 审核通过'

pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find student's name
pattern2 = re.compile(r'[\u4e00-\u9fa5]+')
pattern3 = re.compile(r'\d{5,13}')  # find the student's id and teacher's id

result1 = pattern1.findall(a)
result2 = pattern2.findall(b)
result2.reverse()   # 利用逆序直接抓前两个数据 不管后面的
result3 = pattern3.findall(b)
print(result3)

