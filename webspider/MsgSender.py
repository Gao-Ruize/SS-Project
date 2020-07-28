import requests
import json
import datetime
import pymysql
from setting import TOKEN_URL, MSG_URL, APPID, SECRET, MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB
from logconfig import MyLogging


class MsgSender:
    def __init__(self):
        self.appid = APPID
        self.secret = SECRET
        # 建立连接
        self.conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, charset='utf8')
        # 建立游标
        self.cursor = self.conn.cursor()

    def __del__(self):
        self.cursor.close()
        self.conn.close()

    def sendMsg(self, uid, msg):
        res = requests.get(url=TOKEN_URL, params={
         "grant_type": 'client_credential',
         'appid': self.appid,
         'secret': self.secret
         }).json()

        token = res.get('access_token')
        body = {
            "touser": uid,
            "msgtype": "text",
            "text": {
                "content": msg
            }
        }

        requests.post(url=MSG_URL, params={
            'access_token': token
        }, data=json.dumps(body, ensure_ascii=False).encode('utf-8'))

        MyLogging.write_logger("send msg successfully!")

    def sendDB(self, studentId, msg, phase):  # sending message to database as a JWC Message
        # 首先往jwc_message里面插入消息
        time = datetime.datetime.now().strftime('%Y-%m-%d')
        sql = 'insert into jwc_message(ReleaseTime, Title, Content, Phase) values(%s, %s, %s, %s)'
        title = '自动提醒'
        content = msg
        data = (time, title, content, phase)
        try:
            self.cursor.execute(sql, data)
            self.conn.commit()
            MyLogging.write_logger("insert data successfully!")
        except Exception as e:
            MyLogging.error_logger(e)
            self.conn.rollback()
        # then we should find the message's ID
        # 考虑到 信息的重复性,因此我们仅仅只能通过last_insert_id()来做消息处理
        sql2 = 'select last_insert_id()'
        try:
            self.cursor.execute(sql)
            row = self.cursor.fetchone()
            if row is not None:
                msgId = row[0]
        except Exception as e:
            MyLogging.error_logger(e)

        sql3 = 'insert into read_jwc_msg(StudentId, ifRead, MsgId) values(%s, %s, %s)'
        data2 = (studentId, 0, msgId)
        try:
            self.cursor.execute(sql3, data2)
            self.conn.commit()
            MyLogging.write_logger("insert data successfully!")
        except Exception as e:
            MyLogging.error_logger(e)
            self.conn.rollback()
