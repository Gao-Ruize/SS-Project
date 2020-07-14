# coding=gbk
import os
import re


def reportCheck(path):
    file = open(path, mode='r')
    next(file)
    lines = file.readlines()
    pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find status
    p1 = re.compile(r'[(](.*?)[)]', re.S)  # find studentId
    i = 1
    for line in lines:
        if i % 2 == 0:  # ��status
            result1 = pattern1.findall(line)
            status = result1[0]
            print(studentId)
            print(tutorId)
            print(status)
        else:   # ��tid and sid
            result2 = p1.findall(line)
            result2.reverse()
            studentId = result2[0]
            tutorId = result2[1]
        i += 1
    file.close()


def selectCheck(path):
    file = open(path, mode='r')
    next(file)
    lines = file.readlines()
    i = 1
    pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find status
    p1 = re.compile(r'[(](.*?)[)]', re.S)  # find studentId
    for line in lines:
        if i % 2 == 0:  # �ڶ��� ��¼ѧ��ѧ���Լ��׶�״̬������Ϣ
            result1 = pattern1.findall(line)
            result2 = p1.findall(line)
            studentId = result2[0]
            status = result1[0]
            print(studentId)
            print(studentName)
            print(tutorId)
            print(tutorName)
            print(status)
        else:   # ��һ�� ��ȡ ѧ������ ��ʦ���� ��ʦ���� ������Ϣ (��ʵ��Ҳֻ��Ҫ������׶ζ���ȡ���� ��ע��student/tutor��)
            result2 = p1.findall(line)
            result2.reverse()
            tutorId = result2[0]
            result1 = pattern1.findall(line)
            result1.reverse()
            studentName = result1[0]
            tutorName = result1[1]
        i += 1
    file.close()


Path = 'data/reportPhase'
fileList = os.listdir(Path)
for item in fileList:
    filePath = Path + '/' + item
    reportCheck(filePath)
