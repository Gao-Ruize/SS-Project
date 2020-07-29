# coding=gbk
import string
import pymysql
import time
import random
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB

conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, charset='utf8')
cursor = conn.cursor()

def getRandomTime():
    a1 = (2020, 1, 1, 0, 0, 0, 0, 0, 0)  # 设置开始日期时间元组（1976-01-01 00：00：00）
    a2 = (2020, 12, 31, 23, 59, 59, 0, 0, 0)  # 设置结束日期时间元组（1990-12-31 23：59：59）

    start = time.mktime(a1)  # 生成开始时间戳
    end = time.mktime(a2)  # 生成结束时间戳

    t = random.randint(start, end)  # 在开始和结束时间戳中随机取出一个
    date_touple = time.localtime(t)  # 将时间戳生成时间元组
    date = time.strftime("%Y-%m-%d", date_touple)  # 将时间元组转成格式化字符串（1976-05-21）
    return date


def test():
    sql = 'insert into instruct(studentid, tutorid) VALUES(%s, %s)'
    data = ('1', '1')
    try:
        cursor.execute(sql, data)
        conn.commit()
    except Exception as e:
        print e
        conn.rollback()

    sql2 = 'select last_insert_id()'
    cursor.execute(sql2)
    row = cursor.fetchone()
    if row is not None:
        print row[0]


def InsertJwcMessage():
    # generate jwc message randomly
    # 对于教务处消息而言
    # 20k 的数据量 = 10k 给老师的数据 + 10k 给学生的消息
    for i in range(1, 10001):
        # 生成一个2-20位 由数字+字母组成的随机字符串 以填充消息
        a = random.randint(2, 20)
        ran_str = ''.join(random.sample(string.ascii_letters + string.digits, a))
        releaseTime = getRandomTime()
        b = str(random.randint(1, 5))
        c = random.randint(0, 20)
        Title = 'test' + str(c) + '_level' + str(random.randint(0, 10))
        data = (releaseTime, Title, ran_str, b)
        sql1 = 'insert into jwc_message(ReleaseTime, Title, Content, Phase) values(%s, %s, %s, %s)'
        try:
            cursor.execute(sql1, data)
            conn.commit()
        except Exception as e:
            print e
            conn.rollback()
        sql2 = 'select last_insert_id()'
        cursor.execute(sql2)
        row = cursor.fetchone()
        msgId = row[0]  # 即最后插入的消息ID
        # 先构造发给学生的jwc消息
        sql3 = 'insert into read_jwc_msg(StudentId, ifstudent, ifRead, MsgId) values(%s, %s, %s, %s)'
        # 随机生成学号， 12位纯数字
        ran_str = ''.join(random.choice("0123456789") for i in range(12))
        data2 = (ran_str, 1, str(random.randint(0, 1)), msgId)
        try:
            cursor.execute(sql3, data2)
            conn.commit()
        except Exception as e:
            print e
            conn.rollback()

    for i in range(1, 10001):
        a = random.randint(2, 20)
        ran_str = ''.join(random.sample(string.ascii_letters + string.digits, a))
        releaseTime = getRandomTime()
        b = str(random.randint(1, 5))
        c = random.randint(0, 20)
        Title = 'test' + str(c) + '_level' + str(random.randint(0, 10))
        data = (releaseTime, Title, ran_str, b)
        sql1 = 'insert into jwc_message(ReleaseTime, Title, Content, Phase) values(%s, %s, %s, %s)'
        try:
            cursor.execute(sql1, data)
            conn.commit()
        except Exception as e:
            print e
            conn.rollback()
        sql2 = 'select last_insert_id()'
        cursor.execute(sql2)
        row = cursor.fetchone()
        msgId = row[0]  # 即最后插入的消息ID
        sql3 = 'insert into read_jwc_msg(tutorid, ifstudent, ifRead, MsgId) values(%s, %s, %s, %s)'
        # 随机生成学号， 12位纯数字
        ran_str = ''.join(random.choice("0123456789") for i in range(12))
        data2 = (ran_str, 0, str(random.randint(0, 1)), msgId)
        try:
            cursor.execute(sql3, data2)
            conn.commit()
        except Exception as e:
            print e
            conn.rollback()


def InsertTutorMessage():
    # 10k 的数据量 = 500位导师 <-> 学生 * 20条消息？
    # 首先创造50个老师以及500个学生 - 拟1对10
    tutorInfo = []
    studentInfo = []
    while len(tutorInfo) < 50:
        tutorId = ''.join(random.choice("0123456789") for i in range(5))
        tutorInfo.append(tutorId)
    while len(studentInfo) < 500:
        studentId = ''.join(random.choice("0123456789") for i in range(12))
        studentInfo.append(studentId)
    # 开始注入数据
    for i in range(0, 50):
        for k in range(0, 20):
            a = random.randint(2, 20)
            ran_str = ''.join(random.sample(string.ascii_letters + string.digits, a))
            releaseTime = getRandomTime()
            b = str(random.randint(1, 5))
            c = random.randint(0, 20)
            Title = 'test' + str(c) + '_level' + str(random.randint(0, 10))
            data = (releaseTime, Title, ran_str, b, tutorInfo[i])
            sql1 = 'insert into ins_message(ReleaseTime, Title, Content, Phase, tutorid) values(%s, %s, %s, %s, %s)'
            try:
                cursor.execute(sql1, data)
                conn.commit()
            except Exception as e:
                print e
                conn.rollback()
            sql2 = 'select last_insert_id()'
            cursor.execute(sql2)
            row = cursor.fetchone()
            msgId = row[0]  # 即最后插入的消息ID
            for j in range(10 * i, 10 * i + 10):
                sql2 = 'insert into read_ins_msg(ifread, msgid, studentid) VALUES (%s, %s, %s)'
                data = (str(random.randint(0, 1)), msgId, studentInfo[j])
                try:
                    cursor.execute(sql2, data)
                    conn.commit()
                except Exception as e:
                    print e
                    conn.rollback()


InsertTutorMessage()
#InsertJwcMessage()
cursor.close()
conn.close()

