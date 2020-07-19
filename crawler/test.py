# coding=gbk
import os
import pymysql
import re
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB

pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # 匹配最后的审核状态
p1 = re.compile(r'[(](.*?)[)]', re.S)   # student_id 的 匹配模式 且为非贪婪

# 不同的阶段需要的正则不同 因此需要不同处理
def checkMiddlePhase(connect):
    path = 'data/middlePhase'
    fileList = os.listdir(path)
    cursor = connect.cursor()
    for item in fileList:
        filepath = path + '/' + item
        file = open(filepath, mode='r')
        next(file)
        lines = file.readlines()
        i = 1
        for line in lines:
            if i % 2 == 0:  # 第二行 提取学生ID + 状态即可 甚至不需要提取第一行的信息
                result1 = p1.findall(line)
                studentId = result1[0]
                result2 = pattern1.findall(line)
                result2.reverse()
                status = result2[0]
                # 拿到相应的data之后就去sql里面查询
                sql = 'select Status from info where Sid=%s'
                data = studentId
                cursor.execute(sql, data)
                result_status = cursor.fetchone()[0]
                if status != result_status:
                    return True
            # 甚至不想去管第一行的信息
            i += 1
        file.close()
    cursor.close()
    return False


conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, charset='utf8')
print(checkMiddlePhase(conn))
conn.close()
