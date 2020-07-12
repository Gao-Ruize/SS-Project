import os
path = 'data/selectPhase'
fileList = os.listdir(path)
print(fileList)
for item in fileList:
    filePath = path + '/' + item
    file = open(filePath, mode='r')
    next(file)
    for line in file.readlines():
        line = line.strip()
        print(line)
    file.close()
