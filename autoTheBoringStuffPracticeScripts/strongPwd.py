#! /usr/bin/python3

import re, sys

digitRegex = re.compile(r"\d+")
upperAndLowerRegex = re.compile(r"([A-Z]+\d?[a-z]+|[a-z]+\d?[A-Z])")
altRegex = re.compile(r'''(
^                         #Start anchor
(?=.*[A-Z])        #Ensure string has one uppercase letters.
#(?=.*[!@#$&*])            #Ensure string has one special case letter.
(?=.*[0-9])        #Ensure string has one digits.
(?=.*[a-z]) #Ensure string has one lowercase letters.
.{8,}                      #Ensure string is of length 8.
$                         #End anchor.
)''', re.VERBOSE)


password = sys.argv[1]

if len(password) < 8:
    print("Password not long enough")
elif digitRegex.search(password) == None:
    print("Needs to have at least one digit")
elif upperAndLowerRegex.search(password) == None:
    print("Must contain both upper and lower case characters")
else:
    print("Strong password!")

if altRegex.search(password) == None:
    print("Alt failed")
else:
    print("Alt passed")
