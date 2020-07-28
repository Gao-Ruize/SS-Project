# coding=utf-8
import pymysql
import os
import re
from setting import MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, ROOT
from MsgSender import MsgSender
from logconfig import MyLogging


class MsgChecker:

    def __init__(self):
        # 建立连接
        self.conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, charset='utf8')
        # 建立游标
        self.cursor = self.conn.cursor()
        self.msgSender = MsgSender()
        self.root = ROOT

    def __del__(self):
        self.cursor.close()
        self.conn.close()

    def checkSelectPhase(self):
        path = self.root + '/selectPhase'
        filelist = os.listdir(path)
        # 哪怕是第一次爬取fileList也必不可能为空
        for item in filelist:
            filepath = path + '/' + item
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
                    sql = 'select Status from info where Sid = %s and Phase = %s'
                    data = (studentId, '导师选择')
                    try:
                        self.cursor.execute(sql, data)
                        if self.cursor is not None:
                            row = self.cursor.fetchone()
                            if row is not None:  # which means we may find the answer
                                result_status = row[0]
                                if status != result_status: # 发消息辽！
                                    sql2 = 'select uid from student where studentid = %s'
                                    data = studentId
                                    self.cursor.execute(sql2, data)
                                    row2 = self.cursor.fetchone()
                                    if row2 is not None:  # 已经绑定好了
                                        uid = row2[0]
                                        msg = '您好，您在导师选择阶段的状态有改变！'
                                        self.msgSender.sendMsg(uid, msg)
                                        MyLogging.write_logger("status change detected")
                    except Exception as e:
                        MyLogging.error_logger(e)
                i += 1
            file.close()

    def checkMiddelePhase(self):
        path = self.root + '/middlePhase'
        filelist = os.listdir(path)
        # 哪怕是第一次爬取fileList也必不可能为空
        for item in filelist:
            filepath = path + '/' + item
            file = open(filepath, mode='r')
            next(file)
            lines = file.readlines()
            i = 1
            status_pattern = re.compile(r'[\u4e00-\u9fa5]+')
            id_pattern = re.compile(r'[(](.*?)[)]', re.S)
            for line in lines:
                if i % 2 == 0:
                    result1 = id_pattern.findall(line)
                    studentId = result1[0]
                    result2 = status_pattern.findall(line)
                    result2.reverse()
                    status = result2[0]
                    sql = 'select Status from info where Sid = %s and Phase = %s'
                    data = (studentId, '中期检查')
                    try:
                        self.cursor.execute(sql, data)
                        if self.cursor is not None:
                            row = self.cursor.fetchone()
                            if row is not None:  # which means we may find the answer
                                result_status = row[0]
                                if status != result_status:  # 发消息辽！
                                    sql2 = 'select uid from student where studentid = %s'
                                    data = studentId
                                    self.cursor.execute(sql2, data)
                                    row2 = self.cursor.fetchone()
                                    if row2 is not None:  # 已经绑定好了
                                        uid = row2[0]
                                        msg = '您好，您在中期检查阶段的状态有改变！'
                                        self.msgSender.sendMsg(uid, msg)
                                        MyLogging.write_logger("status change detected")
                    except Exception as e:
                        MyLogging.error_logger(e)
                i += 1
            file.close()

    def checkReportPhase(self):
        path = self.root + '/reportPhase'
        filelist = os.listdir(path)
        # 哪怕是第一次爬取fileList也必不可能为空
        for item in filelist:
            filepath = path + '/' + item
            file = open(filepath, mode='r')
            next(file)
            lines = file.readlines()
            i = 1
            status_pattern = re.compile(r'[\u4e00-\u9fa5]+')
            id_pattern = re.compile(r'[(](.*?)[)]', re.S)
            for line in lines:
                if i % 2 == 0:  # 爬status
                    result1 = status_pattern.findall(line)
                    status = result1[0]
                    sql = 'select Status from info where Sid = %s and Phase = %s'
                    data = (studentId, '开题报告')
                    try:
                        self.cursor.execute(sql, data)
                        if self.cursor is not None:
                            row = self.cursor.fetchone()
                            if row is not None:  # which means we may find the answer
                                result_status = row[0]
                                if status != result_status:  # 发消息辽！
                                    sql2 = 'select uid from student where studentid = %s'
                                    data = studentId
                                    self.cursor.execute(sql2, data)
                                    row2 = self.cursor.fetchone()
                                    if row2 is not None:  # 已经绑定好了
                                        uid = row2[0]
                                        msg = '您好，您在开题报告阶段的状态有改变！'
                                        self.msgSender.sendMsg(uid, msg)
                                        MyLogging.write_logger("status change detected")
                    except Exception as e:
                        MyLogging.error_logger(e)
                else:  # 爬tid and sid
                    result2 = id_pattern.findall(line)
                    result2.reverse()
                    studentId = result2[0]
                    tutorId = result2[1]
                i += 1
            file.close()



