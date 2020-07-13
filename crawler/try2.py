# coding=gbk
import os
import re


def test(file):
    pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find student's name, teacher's name and status
    pattern2 = re.compile(r'\d{5,13}')  # find the student's id and teacher's id
    openfile = open(file, mode='r')
    next(openfile)
    lines = openfile.readlines()
    i = 1
    for line in lines:
        if i % 2 == 0:
            # �ڶ��� ����������ʽ��ȡѧ��ID ��ʦID ��ʦ���� ��ǰ��״̬
            result2 = pattern2.findall(line)    # sid and tid
            print(result2[0])   # sid
            if len(result2) != 1:   #
                print(result2[1])   # tid
            result3 = pattern1.findall(line)
            result3.reverse()
            print(result3[1])   # teacher's name
            print(result3[0])   # status
        else:
            # ��һ�� ����������ʽ��ȡ ѧ������
            result1 = pattern1.findall(line)
            print(result1[0])
        i += 1
    openfile.close()


path = 'data/middlePhase'
fileList = os.listdir(path)
print(fileList)
for item in fileList:
    filePath = path + '/' + item
    print(filePath)
    test(filePath)




