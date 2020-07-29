# coding=gbk
import string
import pymysql
import time
import random
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB

conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, charset='utf8')
cursor = conn.cursor()


# ����1k��ѧ��,�Լ���uid
def generate_student_info():
    student_info = []
    while len(student_info) < 1000:
        studentId = ''.join(random.choice("0123456789") for i in range(12))
        student_info.append(studentId)
    return student_info


# ����100����ʦ,�Լ���uid
def generate_tutor_info():
    tutor_info = []
    while len(tutor_info) < 100:
        tutor_id = ''.join(random.choice("0123456789") for i in range(5))
        tutor_info.append(tutor_id)
    return tutor_info


def fill_table():
    studentInfo = generate_student_info()
    for item in studentInfo:
        sql = 'insert into student(studentid, uid) values (%s, %s)'
        data = (item, item)
        try:
            cursor.execute(sql, data)
            conn.commit()
        except Exception as e:
            print e
            conn.rollback()

    tutroInfo = generate_tutor_info()
    for item in tutroInfo:
        sql = 'insert into tutor(tutorid, uid) values (%s, %s)'
        data = (item, item)
        try:
            cursor.execute(sql, data)
            conn.commit()
        except Exception as e:
            print e
            conn.rollback()

    # ��д�����˶����յ��Ľ�����Ϣ
    for i in range(1, 15):
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
        msgId = row[0]  # �����������ϢID
        # ������Ϣ�������˶����յ���
        for item in studentInfo:
            sql = 'insert into read_jwc_msg(StudentId, ifstudent, ifRead, MsgId) values(%s, %s, %s, %s)'
            data = (item, 1, str(random.randint(0, 1)), msgId)
            try:
                cursor.execute(sql, data)
                conn.commit()
            except Exception as e:
                print e
                conn.rollback()

        for item in tutroInfo:
            sql = 'insert into read_jwc_msg(tutorid, ifstudent, ifRead, MsgId) values(%s, %s, %s, %s)'
            data = (item, 1, str(random.randint(0, 1)), msgId)
            try:
                cursor.execute(sql, data)
                conn.commit()
            except Exception as e:
                print e
                conn.rollback()

    # ��ʼ��д ins_message ͬ�� ��Ҫ�Ƕ���һ����ʦ��Ӧ10��ѧ�� �ټ���һ��ѧ������10����Ϣ
    for i in range(0, 100):
        for k in range(0, 10):
            # ��������Ϣ
            a = random.randint(2, 20)
            ran_str = ''.join(random.sample(string.ascii_letters + string.digits, a))
            releaseTime = getRandomTime()
            b = str(random.randint(1, 5))
            c = random.randint(0, 20)
            Title = 'test' + str(c) + '_level' + str(random.randint(0, 10))
            data = (releaseTime, Title, ran_str, b, tutroInfo[i])
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
            msgId = row[0]  # �����������ϢID
            for j in range(10 * i, 10 * i + 10):
                sql2 = 'insert into read_ins_msg(ifread, msgid, studentid) VALUES (%s, %s, %s)'
                data = (str(random.randint(0, 1)), msgId, studentInfo[j])
                try:
                    cursor.execute(sql2, data)
                    conn.commit()
                except Exception as e:
                    print e
                    conn.rollback()






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
    # ���ڽ�����Ϣ����
    # 20k �������� = 10k ����ʦ������ + 10k ��ѧ������Ϣ
    for i in range(1, 10001):
        # ����һ��2-20λ ������+��ĸ��ɵ�����ַ��� �������Ϣ
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
        msgId = row[0]  # �����������ϢID
        # �ȹ��췢��ѧ����jwc��Ϣ
        sql3 = 'insert into read_jwc_msg(StudentId, ifstudent, ifRead, MsgId) values(%s, %s, %s, %s)'
        # �������ѧ�ţ� 12λ������
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
        msgId = row[0]  # �����������ϢID
        sql3 = 'insert into read_jwc_msg(tutorid, ifstudent, ifRead, MsgId) values(%s, %s, %s, %s)'
        # �������ѧ�ţ� 12λ������
        ran_str = ''.join(random.choice("0123456789") for i in range(12))
        data2 = (ran_str, 0, str(random.randint(0, 1)), msgId)
        try:
            cursor.execute(sql3, data2)
            conn.commit()
        except Exception as e:
            print e
            conn.rollback()


def InsertTutorMessage():
    # 10k �������� = 500λ��ʦ <-> ѧ�� * 20����Ϣ��
    # ���ȴ���50����ʦ�Լ�500��ѧ�� - ��1��10
    tutorInfo = []
    studentInfo = []
    while len(tutorInfo) < 50:
        tutorId = ''.join(random.choice("0123456789") for i in range(5))
        tutorInfo.append(tutorId)
    while len(studentInfo) < 500:
        studentId = ''.join(random.choice("0123456789") for i in range(12))
        studentInfo.append(studentId)
    # ��ʼע������
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
            msgId = row[0]  # �����������ϢID
            for j in range(10 * i, 10 * i + 10):
                sql2 = 'insert into read_ins_msg(ifread, msgid, studentid) VALUES (%s, %s, %s)'
                data = (str(random.randint(0, 1)), msgId, studentInfo[j])
                try:
                    cursor.execute(sql2, data)
                    conn.commit()
                except Exception as e:
                    print e
                    conn.rollback()


# InsertTutorMessage()
# InsertJwcMessage()
fill_table()
cursor.close()
conn.close()
