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


def test(file, phase):
    flag = firstInsert()    # 检测到底是不是第一个爬取数据的处理
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
            teachername = result3[1]
            status = result3[0]
            if flag:    # 第一次插入 对info表应用insert 同时用set/dictionary保存学生/老师信息 最后进行student/tutor表的注入
                tutorinfo[tid] = teachername
                studentinfo[sid] = studentname
                data = (sid, tid, phase, status)
                sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                db.save_data(data, sql)
            else:
                sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                data = (status, sid, phase)
                db.save_data(data, sql)

        else:
            # 第一行 直接利用数字与工号之间的空格分割字符串即可
            result1 = line.split(' ', 1)
            studentname = result1[1].strip('\n')
        i += 1
    openfile.close()


path = 'data/middlePhase'
tutorinfo = dict()
studentinfo = dict()
fileList = os.listdir(path)
for item in fileList:
    filePath = path + '/' + item
    test(filePath, '中期检查')

flag = firstInsert()
if flag:
    # 往两个表里注入信息
    for key in studentinfo:
        data = (key, studentinfo[key])
        sql = 'insert into student (student_id, student_name) values (%s, %s)'
        db.save_data(data, sql)

    for key in tutorinfo:
        data = (key, tutorinfo[key])
        sql = 'insert into tutor (tutor_id, tutor_name) values (%s, %s)'
        db.save_data(data, sql)



