# coding=gbk
import os
import re
import pymysql  # �����ж�ѧ����/��ʦ���Ƿ�Ϊ��
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB
from DataManager import DataManager

db = DataManager()


def firstInsert():
    # ����cursor�����ݿ� �ܼ򵥵�sql�ű� ������ݿ��student���Ƿ�Ϊnull
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()  # �����α�
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
    flag = InsertOrUpdate('���ⱨ��')
    next(file)
    lines = file.readlines()
    pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find status
    p1 = re.compile(r'[(](.*?)[)]', re.S)  # find studentId
    i = 1
    for line in lines:
        if i % 2 == 0:  # ��status
            result1 = pattern1.findall(line)
            status = result1[0]
            if flag:    # ��һ�β��� ��info��Ӧ��insert
                data = (studentId, tutorId, '���ⱨ��', status)
                sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                db.save_data(data, sql)
            else:
                sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                data = (status, studentId, '���ⱨ��')
                db.save_data(data, sql)
        else:   # ��tid and sid
            result2 = p1.findall(line)
            result2.reverse()
            studentId = result2[0]
            tutorId = result2[1]
        i += 1
    file.close()


def selectCheck(path):
    flag1 = firstInsert()    # ��⵽���ǲ��ǵ�һ����ȡ���ݵĴ���
    # ����� ��϶���Ҫinsert ����϶�insert���� (��Ϊ���ǵ�һ���׶� ��˿�����ô����)
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
            if flag1:
                tutorinfo[tutorId] = tutorName
                studentinfo[studentId] = studentName
                data = (studentId, tutorId, '��ʦѡ��', status)
                sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                db.save_data(data, sql)
            else:
                sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                data = (status, studentId, '��ʦѡ��')
                db.save_data(data, sql)

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


def InsertOrUpdate(phase):
    # ���info���иý׶��Ƿ�Ϊ�� ��Ϊ�� ����True ��ʾ��ҪInsert ���򷵻�False ��ʾ���¼���
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()  # �����α�
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
    flag = InsertOrUpdate('���ڼ��')    # ��⵽���ǲ��ǵ�һ����ȡ���ݵĴ���
    pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find student's name, teacher's name and status
    p1 = re.compile(r'[(](.*?)[)]', re.S)  # ���÷�̰��ģʽ�ҵ���Ӧ���ŵ����� ���ǵ�������Ŀ������ܻ�������� �����ȡ��һ����Ϊѧ��ѧ�� ������ȡ���һ����Ϊ��ʦ����
    openfile = open(file, mode='r')
    next(openfile)
    lines = openfile.readlines()
    i = 1
    for line in lines:
        if i % 2 == 0:
            # �ڶ��� ����������ʽ��ȡѧ��ID ��ʦID ��ʦ���� ��ǰ��״̬
            result2 = p1.findall(line)    # sid and tid
            sid = result2[0]    # student id
            result2.reverse()
            tid = result2[0]    # teacher's id
            result3 = pattern1.findall(line)
            result3.reverse()
            status = result3[0]
            if flag:    # ��һ�β��� ��info��Ӧ��insert
                data = (sid, tid, '���ڼ��', status)
                sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                db.save_data(data, sql)
            else:
                sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                data = (status, sid, '���ڼ��')
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
    # ����������ע����Ϣ
    for key in studentinfo:
        data = (key, studentinfo[key])
        sql = 'insert into student (StudentId, StudentName) values (%s, %s)'
        db.save_data(data, sql)

    for key in tutorinfo:
        data = (key, tutorinfo[key])
        sql = 'insert into tutor (TutorId, TutorName) values (%s, %s)'
        db.save_data(data, sql)



