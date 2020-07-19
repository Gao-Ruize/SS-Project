# coding=gbk
import string
import pymysql
import time
import random
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB

switcher = {
    0: '导师选择',
    1: '开题报告',
    2: '中期检查'
}

title = {
    0: 'test1',
    1: 'test2',
    2: 'test3'
}


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
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    sql = 'select Sid, Tid from info where Phase=\'开题报告\''
    cursor.execute(sql)
    result = cursor.fetchone()
    print(result[1])
    cursor.close()
    conn.close()


def insertInstructor():
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    sql = 'select Sid, Tid from info where Phase=\'开题报告\''
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
    # 模拟一次发送消息 先注入ins_msg 再注入read_msg
    # 先从instruct里面拿出每一对学生-老师的tuple
    sql = 'select StudentId, TutorId from instruct'
    cursor.execute(sql)
    result = cursor.fetchall()
    for item in result:
        # 先往jwc_message里面插数据
        # 生成一个2-20位 由数字+字母组成的随机字符串 以填充消息
        a = random.randint(2, 20)
        ran_str = ''.join(random.sample(string.ascii_letters + string.digits, a))
        # 再生成一个随机的时间
        releaseTime = getRandomTime()
        # 伪switch语句生成阶段
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
        # 插入后去查找message的ID
        sql2 = 'select Id from jwc_message where Content=%s'
        data2 = ran_str
        cursor.execute(sql2, data2)
        result = cursor.fetchone()
        message_id = result[0]
        print("message id is ", message_id)
        # 开始构造发给read_jwc_msg的语句
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
    # 模拟一次发送消息 先注入ins_msg 再注入read_msg
    # 先从instruct里面拿出每一对学生-老师的tuple
    sql = 'select StudentId, TutorId from instruct'
    cursor.execute(sql)
    result = cursor.fetchall()
    for item in result:
        # 先往ins_message里面插数据
        # 生成一个2-20位 由数字+字母组成的随机字符串 以填充消息
        a = random.randint(2, 20)
        ran_str = ''.join(random.sample(string.ascii_letters + string.digits, a))
        # 再生成一个随机的时间
        releaseTime = getRandomTime()
        # 伪switch语句生成阶段
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
        # 插入后去查找message的ID
        sql2 = 'select Id from ins_message where Content=%s'
        data2 = ran_str
        cursor.execute(sql2, data2)
        result = cursor.fetchone()
        message_id = result[0]
        # 开始构造发给read_ins_msg的语句
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
