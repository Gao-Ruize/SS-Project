# coding=gbk
import re
a = '23 HOKHY TANN\n'
b = '(516030910257) �ļ�ϵͳ�й��ڲ����������Զ���֤�� ������� ���ع�(zhaoguowang) ���ͨ��'

pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find student's name
pattern2 = re.compile(r'\(.+\)')
pattern3 = re.compile(r'\d{5,13}')  # find the student's id and teacher's id
p1 = re.compile(r'[(](.*?)[)]', re.S)   # ���÷�̰��ģʽ�ҵ���Ӧ���ŵ����� ���ǵ�������Ŀ������ܻ�������� �����ȡ��һ����Ϊѧ��ѧ�� ������ȡ���һ����Ϊ��ʦ����

resultT = p1.findall(b)
print(resultT)
Sid = resultT[0]
resultT.reverse()
Tid = resultT[0]
print(Sid)
print(Tid)

