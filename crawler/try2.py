# coding=gbk
import os
import re
import pymysql  # 用于判断学生表/老师表是否为空
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB
from DataManager import DataManager

db = DataManager()


def firstInsert():
    # 利用cursor打开数据库 跑简单的sql脚本 检测数据库的student表是否为null
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()  # 建立游标
    sql = 'select * from student'
    try:
        cursor.execute(sql)
        if cursor is not None:
            row = cursor.fetchone()
            if row is not None:
                return False
            else:
                return True
    except Exception as e:
        print(e)

    cursor.close()
    conn.close()


def reportCheck(path):
    file = open(path, mode='r')
    flag = InsertOrUpdate('开题报告')
    next(file)
    lines = file.readlines()
    pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find status
    p1 = re.compile(r'[(](.*?)[)]', re.S)  # find studentId
    i = 1
    for line in lines:
        if i % 2 == 0:  # 爬status
            result1 = pattern1.findall(line)
            status = result1[0]
            if flag:    # 第一次插入 对info表应用insert
                data = (studentId, tutorId, '开题报告', status)
                sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                db.save_data(data, sql)
            else:
                sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                data = (status, studentId, '开题报告')
                db.save_data(data, sql)
        else:   # 爬tid and sid
            result2 = p1.findall(line)
            result2.reverse()
            studentId = result2[0]
            tutorId = result2[1]
        i += 1
    file.close()


def selectCheck(path):
    flag1 = firstInsert()    # 检测到底是不是第一个爬取数据的处理
    # 如果是 则肯定需要insert 否则肯定insert过了 (因为这是第一个阶段 因此可以这么处理)
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
            if flag1:
                tutorinfo[tutorId] = tutorName
                studentinfo[studentId] = studentName
                data = (studentId, tutorId, '导师选择', status)
                sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                db.save_data(data, sql)
            else:
                sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                data = (status, studentId, '导师选择')
                db.save_data(data, sql)

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


def InsertOrUpdate(phase):
    # 检测info表中该阶段是否为空 若为空 返回True 表示需要Insert 否则返回False 表示更新即可
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()  # 建立游标
    sql = 'select * from info where Phase=%s'
    data = phase
    try:
        cursor.execute(sql, data)
        if cursor is not None:
            row = cursor.fetchone()
            if row is not None:
                return False
            else:
                return True
    except Exception as e:
        print(e)

    cursor.close()
    conn.close()


def MiddleCheck(file):
    flag = InsertOrUpdate('中期检查')    # 检测到底是不是第一个爬取数据的处理
    pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find student's name, teacher's name and status
    p1 = re.compile(r'[(](.*?)[)]', re.S)  # 利用非贪婪模式找到对应括号的内容 考虑到论文题目本身可能会带有括号 因此先取第一个作为学生学号 再逆序取最后一个作为老师工号
    openfile = open(file, mode='r')
    next(openfile)
    lines = openfile.readlines()
    i = 1
    for line in lines:
        if i % 2 == 0:
            # 第二行 利用正则表达式提取学生ID 老师ID 老师姓名 当前的状态
            result2 = p1.findall(line)    # sid and tid
            sid = result2[0]    # student id
            result2.reverse()
            tid = result2[0]    # teacher's id
            result3 = pattern1.findall(line)
            result3.reverse()
            status = result3[0]
            if flag:    # 第一次插入 对info表应用insert
                data = (sid, tid, '中期检查', status)
                sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                db.save_data(data, sql)
            else:
                sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                data = (status, sid, '中期检查')
                db.save_data(data, sql)
        i += 1
    openfile.close()


tutorinfo = dict()
studentinfo = dict()
Path = 'data/selectPhase'
fileList = os.listdir(Path)
for item in fileList:
    filePath = Path + '/' + item
    selectCheck(filePath)

Path = 'data/reportPhase'
fileList = os.listdir(Path)
for item in fileList:
    filePath = Path + '/' + item
    reportCheck(filePath)


path = 'data/middlePhase'
fileList = os.listdir(path)
for item in fileList:
    filePath = path + '/' + item
    MiddleCheck(filePath)

flag = firstInsert()
if flag:
    # 往两个表里注入信息
    for key in studentinfo:
        data = (key, studentinfo[key])
        sql = 'insert into student (StudentId, StudentName) values (%s, %s)'
        db.save_data(data, sql)

    for key in tutorinfo:
        data = (key, tutorinfo[key])
        sql = 'insert into tutor (TutorId, TutorName) values (%s, %s)'
        db.save_data(data, sql)



