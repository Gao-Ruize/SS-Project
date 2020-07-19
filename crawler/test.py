# coding=gbk
import os
import pymysql
import re
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB

pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # ƥ���������״̬
p1 = re.compile(r'[(](.*?)[)]', re.S)   # student_id �� ƥ��ģʽ ��Ϊ��̰��

# ��ͬ�Ľ׶���Ҫ������ͬ �����Ҫ��ͬ����
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
            if i % 2 == 0:  # �ڶ��� ��ȡѧ��ID + ״̬���� ��������Ҫ��ȡ��һ�е���Ϣ
                result1 = p1.findall(line)
                studentId = result1[0]
                result2 = pattern1.findall(line)
                result2.reverse()
                status = result2[0]
                # �õ���Ӧ��data֮���ȥsql�����ѯ
                sql = 'select Status from info where Sid=%s'
                data = studentId
                cursor.execute(sql, data)
                result_status = cursor.fetchone()[0]
                if status != result_status:
                    return True
            # ��������ȥ�ܵ�һ�е���Ϣ
            i += 1
        file.close()
    cursor.close()
    return False


conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, charset='utf8')
print(checkMiddlePhase(conn))
conn.close()
