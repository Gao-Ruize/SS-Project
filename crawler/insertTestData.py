# coding=gbk
import pymysql
import random
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB


def getInfo():
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    # ���򵥵�select���
    sql = 'select Sid, Tid from info where Phase=\'���ⱨ��\''
    cursor.execute(sql)
    result = cursor.fetchall()
    print(result)
    print(len(result))
    cursor.close()
    conn.close()


def insertIntoJWC_Message():    # insert test data into the JWC_Message
    conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
    cursor = conn.cursor()
    # ģ��һ�η�����Ϣ ��ע��ins_msg ��ע��read_msg
    cursor.close()
    conn.close()


getInfo()
