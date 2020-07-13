import pymysql
import threading
from setting import MYSQL_DB ,MYSQL_PWD, MYSQL_HOST, MYSQL_USER

# 需要自己写sql脚本 自己指定需要连接的数据库


class DataManager:
    # 单例模式，确保每次实例化都调用一个对象。
    # 在这里出现的变量是一个类变量，它的值将在这个类的所有实例中共享
    _instance_lock = threading.Lock()

    def __new__(cls, *args, **kwargs):
        if not hasattr(DataManager, "_instance"):
            with DataManager._instance_lock:
                DataManager._instance = object.__new__(cls)
                return DataManager._instance

        return DataManager._instance

    def __init__(self):
        # 建立连接
        self.conn = pymysql.connect(MYSQL_HOST, MYSQL_USER, MYSQL_PWD, MYSQL_DB, charset='utf8')

        # 建立游标
        self.cursor = self.conn.cursor()

    def save_data(self, data, sql):
        try:
            self.cursor.execute(sql, data)
            self.conn.commit()
        except Exception as e:
            print('插入数据失败', e)
            self.conn.rollback()  # 回滚

    def __del__(self):
        # 关闭游标
        self.cursor.close()
        # 关闭连接
        self.conn.close()

