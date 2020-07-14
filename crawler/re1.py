# coding=gbk
import re
a = '23 HOKHY TANN\n'
b = '(516030910257) 文件系统中关于并发条件的自动化证明 软件工程 王肇国(zhaoguowang) 审核通过'

pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find student's name
pattern2 = re.compile(r'\(.+\)')
pattern3 = re.compile(r'\d{5,13}')  # find the student's id and teacher's id
p1 = re.compile(r'[(](.*?)[)]', re.S)   # 利用非贪婪模式找到对应括号的内容 考虑到论文题目本身可能会带有括号 因此先取第一个作为学生学号 再逆序取最后一个作为老师工号

resultT = p1.findall(b)
print(resultT)
Sid = resultT[0]
resultT.reverse()
Tid = resultT[0]
print(Sid)
print(Tid)

