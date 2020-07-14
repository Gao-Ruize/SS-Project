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


def test(file, phase):
    flag = firstInsert()    # ��⵽���ǲ��ǵ�һ����ȡ���ݵĴ���
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
            teachername = result3[1]
            status = result3[0]
            if flag:    # ��һ�β��� ��info��Ӧ��insert ͬʱ��set/dictionary����ѧ��/��ʦ��Ϣ ������student/tutor���ע��
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
            # ��һ�� ֱ�����������빤��֮��Ŀո�ָ��ַ�������
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
    test(filePath, '���ڼ��')

flag = firstInsert()
if flag:
    # ����������ע����Ϣ
    for key in studentinfo:
        data = (key, studentinfo[key])
        sql = 'insert into student (student_id, student_name) values (%s, %s)'
        db.save_data(data, sql)

    for key in tutorinfo:
        data = (key, tutorinfo[key])
        sql = 'insert into tutor (tutor_id, tutor_name) values (%s, %s)'
        db.save_data(data, sql)



