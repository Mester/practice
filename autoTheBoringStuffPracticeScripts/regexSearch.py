#! /usr/bin/python3
import sys, os, re

directoryPath = sys.argv[1]
regex = re.compile(r""+sys.argv[2])
fileRegex = re.compile(r"\.txt$")
directory = os.listdir(directoryPath)
textFiles = [x for x in list(directory) if fileRegex.search(x)]

#print(directory)
print(textFiles)

for textFile in textFiles:
    file = open(directoryPath + textFile)
    lines = file.readlines()
    for str in lines:
        if regex.search(str):
            print(str)
    #print(str)
    file.close()
