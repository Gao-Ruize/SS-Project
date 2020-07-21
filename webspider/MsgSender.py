import requests
import json
from setting import TOKEN_URL, MSG_URL, APPID, SECRET


class MsgSender:
    def __init__(self):
        self.appid = APPID
        self.secret = SECRET

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
