# coding=gbk
import pymysql
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB


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


conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
cursor = conn.cursor()  # �����α�
sql = "update info set Status=%s where Sid=%s and Phase=%s"
data = ('���ͨ��', '123', '���ڼ��')
sql2 = "update info set Status='���ͨ��' where Sid='123' and Phase='���ڼ��'"
try:
    cursor.execute(sql, data)
    conn.commit()
except Exception as e:
    print(e)
    conn.rollback()     # �ع�

cursor.close()
conn.close()
