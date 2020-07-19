import requests
import json

url_token = 'https://api.weixin.qq.com/cgi-bin/token?'
res = requests.get(url=url_token,params={
         "grant_type": 'client_credential',
         'appid': 'wx16dcaeb17865172d',
         'secret': '2b516b5b3d199459eb04b0bfd808bee2'
         }).json()
token = res.get('access_token')

url_msg = 'https://api.weixin.qq.com/cgi-bin/message/custom/send?'
body = {
    "touser": 'o9M7BwYLQ4t7sQj7RHbCfC-6XOgY',
    "msgtype": "text",
    "text": {
        "content": "Hello World!"
    }
}

res = requests.post(url=url_msg, params={
    'access_token': token
}, data=json.dumps(body, ensure_ascii=False).encode('utf-8'))

result = res.json()
print(result)

