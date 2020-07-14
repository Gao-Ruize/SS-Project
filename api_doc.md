

# 1
##### 简要描述

- 用户登陆接口

##### 请求URL
- ` http://localhost:8443/api/user/login `
  
##### 请求方式
- POST 

##### 参数

- String code

##### 返回示例 

``` 
  {
    "data": {
      "realId": "123",
      "type": "S"
    }
  }
```

##### 返回参数说明 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|type |String  |学生：S 教师：T 未注册：N  |

##### 备注 

- 从code到uid的逻辑尚未实现


# 2
##### 简要描述

- 用户注册接口

##### 请求URL
- ` http://localhost:8443/api/user/bind `
  
##### 请求方式
- POST 

##### 参数

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|code |Y  |string |微信用户code   |
|realId |Y  |string | 真实ID    |
|type     |Y  |string | 学生/老师    |

##### 返回示例 

``` 
  {
    "data": {
      "code": 200,
    }
  }
```

##### 返回参数说明 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code |int   |学生：200 老师：201 绑定失败：300  |

##### 备注 

- 从code到uid逻辑尚未实现

# 3


    
##### 简要描述

- 解除绑定

##### 请求URL
- ` http://localhost:8443/api/admin/unbind
  
##### 请求方式
- POST 

##### 参数

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|realId |Y  |string |ID   |
|type|Y  |string | 学生/教师    |

##### 返回示例 

``` 
  {
    "data": {
     "code" : 200
    }
  }
```

##### 返回参数说明 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code |int   |成功：200 失败：400  |

# 4
##### 简要描述

- 选导师时获取导师信息

##### 请求URL
- ` http://localhost:8443/api/user/tutors`
  
##### 请求方式
- GET

##### 参数

无

##### 返回示例 

``` 
"data" : [
{id: 1, tutorid:"xxx", uid:"xxx", tutorname:"xxx"},{......}
]
```

##### 返回参数说明 

tutor数组

# 5


    
##### 简要描述

- 选导师

##### 请求URL
- ` http://xx.com/api/stu/choosetutor `
  
##### 请求方式
- POST 

##### 参数

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|studentId|是  |string |学生ID   |
|tutorId |是  |string | 教师ID    |

##### 返回示例 

``` 
  {
    "data": {
     code: 200
    }
  }
```

##### 返回参数说明 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code |int   |200:成功 400:失败  |

# 6


    
##### 简要描述

- 获取教务处信息

##### 请求URL
- ` http://xx.com/api/user/jwcmsgs `
  
##### 请求方式
- POST 

##### 参数

无

##### 返回示例 

``` 
  {
    "data": [{id: 1, releasetime: "2020-01-01, title:"tt", "content: "cc", phase: 1},{...}]
  }
```

##### 返回参数说明 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|phase |int   |时间段说明  |

##### 备注 

-返回JwcMessage数组

# 7


    
##### 简要描述

- 将信息由未读改为已读

##### 请求URL
- ` http://xx.com/api/user/readmsg `
  
##### 请求方式
- POST 

##### 参数

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|userId |是  |string |学生/教师ID   |
|msgId |是  |string | 信息ID    |
|type     |是  |string | jwc/tutor    |

##### 返回示例 

``` 
  {
    "data": {
      "code": 200,
    }
  }
```

##### 返回参数说明 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code |int   |200:成功 400:失败  |

##### 备注 

- type为tutor时只需要msgId，jwc需要msgId与userId
- 返回值问题，是否需要直接返回List<Msg>?

# 8


    
##### 简要描述

- 学生获取对应导师消息

##### 请求URL
- ` http://xx.com/api/stu/insmsg/{studentId} ` 
##### 请求方式
- POST 

##### 参数

@PathVariable("studentId") String studentId

##### 返回示例 

``` 
  {
    "data":[{id:1, tutorId:"xx", studentId:"xx", title, content, releasetime, phase, ifread },{...}]
  }
```

##### 返回参数说明 

InsMessage数组


# 9


    
##### 简要描述

- 导师发送信息接口

##### 请求URL
- ` http://xx.com/api/tut/sendmsg `
  
##### 请求方式
- POST 

##### 参数

|参数名|必选|类型|说明|
|:----    |:---|:----- |-----   |
|title |是  |string | 标题 |
|content |是  |string | 内容  |
|time     |是  |string | 时间 |
|toId |是|String| 发送目标Id|

##### 返回示例 

``` 
  {
    "data": {
      "code": 200
    }
  }
```

##### 返回参数说明 

|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code |int   |200:成功 400:失败|

# 10
##### 简要描述

- 导师浏览对应信息阅读情况

##### 请求URL
- ` http://xx.com/api/tut/getmsginfo/{msgid}`
  
##### 请求方式
- GET

##### 参数

@Pathvariable("msgid) String msgId

##### 返回示例 

``` 
  {
    "data": [{student数组}]
  }
```

##### 返回参数说明 

student数组，附加ifRead参数







