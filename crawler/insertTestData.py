# coding=gbk
import pymysql
import random
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB


def getInfo():
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    # 做简单的select语句
    sql = 'select Sid, Tid from info where Phase=\'开题报告\''
    cursor.execute(sql)
    result = cursor.fetchall()
    print(result)
    print(len(result))
    cursor.close()
    conn.close()


def insertIntoJWC_Message():    # insert test data into the JWC_Message
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    # 模拟一次发送消息 先注入ins_msg 再注入read_msg
    cursor.close()
    conn.close()


getInfo()
