#! /usr/bin/python3

import os, shutil

folder = os.path.abspath("./pythonfiles")
if not os.path.exists(folder):
    os.makedirs(folder)
#print(folder)

for foldername, subfolders, filenames in os.walk('.'):
    #print(os.path.abspath(foldername))
    foldername = os.path.abspath(foldername)
    if foldername == folder:
        continue
    for filename in filenames:
        if filename.endswith(".py"):
            print("Copying %s to %s" % (os.path.join(foldername, filename), folder))
            shutil.copy(os.path.join(foldername, filename), folder)
    
   
        
