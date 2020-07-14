# 操作指南

- 若需要注入数据,需要以下几个文件：

  1.data文件夹

  2.DataManager.py 文件

  3.setting.py 文件 —是配置文件，其中你需要修改MYSQL_HOST,MYSQL_USER等数据,以匹配你自己的数据库,从而能够连接上自己的数据库

  4.try2.py

  与此同时,你需要安装3.x版本的python,并且在此之后于命令行输入:

  pip install pymysql

  此后运行try2.py文件完成数据注入