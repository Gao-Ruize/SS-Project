from spider import Spider
from UpdateChecker import UpdateChecker
from MsgChecker import MsgChecker

updateChecker = UpdateChecker()
spider = Spider()
msgChecker = MsgChecker()
spider.grabData()
msgChecker.checkSelectPhase()
msgChecker.checkReportPhase()
msgChecker.checkMiddelePhase()
updateChecker.selectCheck()
updateChecker.reportCheck()
updateChecker.middleCheck()
