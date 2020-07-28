# coding = gbk
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, ROOT
import pymysql  # 用于判断学生表/老师表是否为空
import os
import re
from DataManager import DataManager
from logconfig import MyLogging


class UpdateChecker:

    def __init__(self):
        self.db = DataManager()
        self.tutorInfo = dict()
        self.studentInfo = dict()
        self.conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, charset='utf8')
        self.cursor = self.conn.cursor()
        self.root = ROOT

    def __del__(self):
        # 关闭游标
        self.cursor.close()
        # 关闭连接
        self.conn.close()

    def firstInsertCheck(self):
        sql = 'select * from student'
        try:
            self.cursor.execute(sql)
            if self.cursor is not None:
                row = self.cursor.fetchone()
                if row is not None:
                    return False
                else:
                    return True
        except Exception as e:
            MyLogging.error_logger(e)

    def InsertOrUpdate(self, phase):
        sql = 'select * from info where Phase=%s'
        try:
            self.cursor.execute(sql, phase)
            if self.cursor is not None:
                row = self.cursor.fetchone()
                if row is not None:
                    return False
                else:
                    return True
        except Exception as e:
            MyLogging.error_logger(e)

    def selectCheck(self):
        path = self.root + '/selectPhase'
        filelist = os.listdir(path)
        for item in filelist:
            filepath = path + '/' + item
            flag = self.firstInsertCheck()
            file = open(filepath, mode='r')
            next(file)
            lines = file.readlines()
            i = 1
            status_pattern = re.compile(r'[\u4e00-\u9fa5]+')
            id_pattern = re.compile(r'[(](.*?)[)]', re.S)
            for line in lines:
                if i % 2 == 0:
                    Status = status_pattern.findall(line)
                    ids = id_pattern.findall(line)
                    studentId = ids[0]
                    status = Status[0]
                    if flag:
                        self.tutorInfo[tutorId] = tutorName
                        self.studentInfo[studentId] = studentName
                        data = (studentId, tutorId, '导师选择', status)
                        sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                        self.db.save_data(data, sql)
                    else:
                        sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                        data = (status, studentId, '导师选择')
                        self.db.save_data(data, sql)
                else:
                    info = id_pattern.findall(line)
                    info.reverse()
                    tutorId = info[0]
                    info2 = status_pattern.findall(line)
                    info2.reverse()
                    studentName = info2[1]
                    tutorName = info2[0]
                i += 1
            file.close()
        insertFlag = self.firstInsertCheck()
        MyLogging.write_logger("select Check Finished")
        if insertFlag:
            self.InsertRoleTable()
            MyLogging.write_logger("insert Finished")

    def InsertRoleTable(self):
        for key in self.studentInfo:
            data = (key, self.studentInfo[key])
            sql = 'insert into student (StudentId, StudentName) values (%s, %s)'
            self.db.save_data(data, sql)

        for key in self.tutorInfo:
            data = (key, self.tutorInfo[key])
            sql = 'insert into tutor (TutorId, TutorName) values (%s, %s)'
            self.db.save_data(data, sql)
        MyLogging.write_logger("Role table insert finished")

    def reportCheck(self):
        path = self.root + '/reportPhase'
        filelist = os.listdir(path)
        flag = self.InsertOrUpdate()
        for item in filelist:
            filepath = path + '/' + item
            file = open(filepath, mode='r')
            next(file)
            lines = file.readlines()
            i = 1
            pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find status
            p1 = re.compile(r'[(](.*?)[)]', re.S)  # find studentId
            for line in lines:
                if i % 2 == 0:  # 爬status
                    result1 = pattern1.findall(line)
                    status = result1[0]
                    if flag:  # 第一次插入 对info表应用insert
                        data = (studentId, tutorId, '开题报告', status)
                        sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                        self.db.save_data(data, sql)
                    else:
                        sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                        data = (status, studentId, '开题报告')
                        self.db.save_data(data, sql)
                else:  # 爬tid and sid
                    result2 = p1.findall(line)
                    result2.reverse()
                    studentId = result2[0]
                    tutorId = result2[1]
                i += 1
            file.close()
        MyLogging.write_logger("Phase2 check finished")

    def middleCheck(self):
        path = self.root + '/reportPhase'
        filelist = os.listdir(path)
        flag = self.InsertOrUpdate()
        for item in filelist:
            filepath = path + '/' + item
            file = open(filepath, mode='r')
            next(file)
            lines = file.readlines()
            i = 1
            pattern1 = re.compile(r'[\u4e00-\u9fa5]+')  # find status
            p1 = re.compile(r'[(](.*?)[)]', re.S)  # find studentId
            for line in lines:
                if i % 2 == 0:
                    # 第二行 利用正则表达式提取学生ID 老师ID 老师姓名 当前的状态
                    result2 = p1.findall(line)  # sid and tid
                    sid = result2[0]  # student id
                    result2.reverse()
                    tid = result2[0]  # teacher's id
                    result3 = pattern1.findall(line)
                    result3.reverse()
                    status = result3[0]
                    if flag:  # 第一次插入 对info表应用insert
                        data = (sid, tid, '中期检查', status)
                        sql = 'insert into info(Sid, Tid, Phase, Status) values(%s, %s, %s, %s)'
                        self.db.save_data(data, sql)
                    else:
                        sql = 'update info set Status=%s where Sid=%s and Phase=%s'
                        data = (status, sid, '中期检查')
                        self.db.save_data(data, sql)
                i += 1
            file.close()
        MyLogging.write_logger("Phase3 check finished")
