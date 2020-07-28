import logging


class MyLogging:
    def __init__(self):
        self.logPath = 'my.log'
        logging.basicConfig(
            level=logging.DEBUG,
            format='%(asctime)s - %(filename)s[line:%(lineno)d] - %(levelname)s: %(message)s',
            datefmt='%Y-%m-%d %H:%M:%S',
            filename=self.logPath,
            filemode='a'
        )

    @staticmethod
    def write_logger(content):
        logging.debug(content)

    @staticmethod
    def error_logger(content):
        logging.error(content)
