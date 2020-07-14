# coding=gbk
import pymysql
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB


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


conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB,  charset='utf8')
cursor = conn.cursor()  # 建立游标
sql = "update info set Status=%s where Sid=%s and Phase=%s"
data = ('审核通过', '123', '中期检查')
sql2 = "update info set Status='审核通过' where Sid='123' and Phase='中期检查'"
try:
    cursor.execute(sql, data)
    conn.commit()
except Exception as e:
    print(e)
    conn.rollback()     # 回滚

cursor.close()
conn.close()
