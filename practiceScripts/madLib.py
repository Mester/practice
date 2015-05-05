#! /usr/bin/python3
import sys, re

adjRegex = re.compile("ADJECTIVE")
nounRegex = re.compile("NOUN")
verbRegex = re.compile("VERB")
file = open(sys.argv[1])

#words = file.read().split(" ")
words = re.compile(r"[\s\W]").split(file.read())
#print(words)

for i in range(len(words)):
    found = False
    if adjRegex.search(words[i]) != None:
        print("Enter an adjective: ", end='')
        found = True
    elif nounRegex.search(words[i]) != None:
        print("Enter a noun: ", end='')
        found = True
    elif verbRegex.search(words[i]) != None:
        print("Enter a verb: ", end='')
        found = True

    if found:
        newWord = input()
        words[i] = newWord
for i in range(len(words)):
    if words[i] == '':
        words[i] = '.'

file.close()
file = open(sys.argv[1] + ".MADLIBS", 'w')
file.write(" ".join(words))
file.write("\n")
file.close()
print(" ".join(words))
