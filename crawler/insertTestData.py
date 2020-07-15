# coding=gbk
import string
import pymysql
import time
import random
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB

switcher = {
    0: '��ʦѡ��',
    1: '���ⱨ��',
    2: '���ڼ��'
}

title = {
    0: 'test1',
    1: 'test2',
    2: 'test3'
}


def getRandomTime():
    a1 = (2020, 1, 1, 0, 0, 0, 0, 0, 0)  # ���ÿ�ʼ����ʱ��Ԫ�飨1976-01-01 00��00��00��
    a2 = (2020, 12, 31, 23, 59, 59, 0, 0, 0)  # ���ý�������ʱ��Ԫ�飨1990-12-31 23��59��59��

    start = time.mktime(a1)  # ���ɿ�ʼʱ���
    end = time.mktime(a2)  # ���ɽ���ʱ���

    t = random.randint(start, end)  # �ڿ�ʼ�ͽ���ʱ��������ȡ��һ��
    date_touple = time.localtime(t)  # ��ʱ�������ʱ��Ԫ��
    date = time.strftime("%Y-%m-%d", date_touple)  # ��ʱ��Ԫ��ת�ɸ�ʽ���ַ�����1976-05-21��
    return date


def test():
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    sql = 'select Sid, Tid from info where Phase=\'���ⱨ��\''
    cursor.execute(sql)
    result = cursor.fetchone()
    print(result[1])
    cursor.close()
    conn.close()


def insertInstructor():
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    sql = 'select Sid, Tid from info where Phase=\'���ⱨ��\''
    cursor.execute(sql)
    result = cursor.fetchall()  # we can use it to fill the instructor table
    for item in result:
        sql = 'insert into instruct(StudentId, TutorId) values(%s, %s)'
        try:
            cursor.execute(sql, item)
            conn.commit()
        except Exception as e:
            print(e)
            conn.rollback()
    cursor.close()
    conn.close()


def insertIntoJWC_Message():    # insert test data into the JWC_Message
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    # ģ��һ�η�����Ϣ ��ע��ins_msg ��ע��read_msg
    # �ȴ�instruct�����ó�ÿһ��ѧ��-��ʦ��tuple
    sql = 'select StudentId, TutorId from instruct'
    cursor.execute(sql)
    result = cursor.fetchall()
    for item in result:
        # ����jwc_message���������
        # ����һ��2-20λ ������+��ĸ��ɵ�����ַ��� �������Ϣ
        a = random.randint(2, 20)
        ran_str = ''.join(random.sample(string.ascii_letters + string.digits, a))
        # ������һ�������ʱ��
        releaseTime = getRandomTime()
        # αswitch������ɽ׶�
        b = str(random.randint(1, 3))
        c = random.randint(0, 2)
        Title = title[c]
        data = (releaseTime, Title, ran_str, b)
        sql1 = 'insert into jwc_message(ReleaseTime, Title, Content, Phase) values(%s, %s, %s, %s)'
        try:
            cursor.execute(sql1, data)
            conn.commit()
        except Exception as e:
            print(e)
            conn.rollback()
        # �����ȥ����message��ID
        sql2 = 'select Id from jwc_message where Content=%s'
        data2 = ran_str
        cursor.execute(sql2, data2)
        result = cursor.fetchone()
        message_id = result[0]
        print("message id is ", message_id)
        # ��ʼ���췢��read_jwc_msg�����
        data3 = (item[0], item[1], str(random.randint(0, 1)), message_id)
        sql3 = 'insert into read_jwc_msg(StudentId, TutorId, ifRead, MsgId) values(%s, %s, %s, %s)'
        try:
            cursor.execute(sql3, data3)
            conn.commit()
        except Exception as e:
            print(e)
            conn.rollback()
    cursor.close()
    conn.close()


def insertIntoIns_Message():
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    # ģ��һ�η�����Ϣ ��ע��ins_msg ��ע��read_msg
    # �ȴ�instruct�����ó�ÿһ��ѧ��-��ʦ��tuple
    sql = 'select StudentId, TutorId from instruct'
    cursor.execute(sql)
    result = cursor.fetchall()
    for item in result:
        # ����ins_message���������
        # ����һ��2-20λ ������+��ĸ��ɵ�����ַ��� �������Ϣ
        a = random.randint(2, 20)
        ran_str = ''.join(random.sample(string.ascii_letters + string.digits, a))
        # ������һ�������ʱ��
        releaseTime = getRandomTime()
        # αswitch������ɽ׶�
        b = str(random.randint(1, 3))
        c = random.randint(0, 2)
        Title = title[c]
        data = (item[1], releaseTime, Title, ran_str, b)
        sql1 = 'insert into ins_message(TutorId, ReleaseTime, Title, Content, Phase) values(%s, %s, %s, %s, %s)'
        try:
            cursor.execute(sql1, data)
            conn.commit()
        except Exception as e:
            print(e)
            conn.rollback()
        # �����ȥ����message��ID
        sql2 = 'select Id from ins_message where Content=%s'
        data2 = ran_str
        cursor.execute(sql2, data2)
        result = cursor.fetchone()
        message_id = result[0]
        # ��ʼ���췢��read_ins_msg�����
        data3 = (item[0], str(random.randint(0, 1)), message_id)
        sql3 = 'insert into read_ins_msg(StudentId, ifRead, MsgId) values(%s, %s, %s)'
        try:
            cursor.execute(sql3, data3)
            conn.commit()
        except Exception as e:
            print(e)
            conn.rollback()
    cursor.close()
    conn.close()


# insertInstructor()
# insertIntoJWC_Message()
insertIntoIns_Message()
# test()
