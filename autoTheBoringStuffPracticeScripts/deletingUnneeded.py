#! /usr/bin/python3

import os, sys

path = os.path.abspath(sys.argv[1])
#print(path)

for foldername, subfolders, filenames in os.walk(path):
    foldername = os.path.abspath(foldername)
    #print(foldername)
    for filename in filenames:
        file = os.path.join(foldername, filename)
        try:
            size = os.path.getsize(file)
            if size > 10000000:
                print("File %s has size %s" % (file, size))
        except FileNotFoundError:
            print("Could not find %s" % file)
        
