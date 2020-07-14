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
        if i % 2 == 0:  # 爬status
            result1 = pattern1.findall(line)
            status = result1[0]
            print(studentId)
            print(tutorId)
            print(status)
        else:   # 爬tid and sid
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
        if i % 2 == 0:  # 第二行 记录学生学号以及阶段状态两种信息
            result1 = pattern1.findall(line)
            result2 = p1.findall(line)
            studentId = result2[0]
            status = result1[0]
            print(studentId)
            print(studentName)
            print(tutorId)
            print(tutorName)
            print(status)
        else:   # 第一行 提取 学生姓名 老师姓名 老师工号 三种信息 (事实上也只需要在这个阶段多提取姓名 以注入student/tutor表)
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
