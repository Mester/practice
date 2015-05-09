#! /usr/bin/python3
# cliEmailer.py - Send emails from cli
# Usage: ./cliEmailer.py "toEmail" "subject" "body"

from selenium import webdriver
import sys, time

if len(sys.argv) < 4:
    print('Usage: ./cliEmailer.py "toEmail" "subject" "text"')
    sys.exit()

email = "adrian.hintermaier@gmail.com"
password = "***"
toEmail, subject, body = sys.argv[1:]

# Setup a webdriver and open gmail
browser = webdriver.Firefox()
browser.get('http://gmail.com')

# Find the email text field and insert login
emailElem = browser.find_element_by_id('Email')
emailElem.clear()
emailElem.send_keys(email)

# Find the password field and insert the password
passwordElem = browser.find_element_by_id('Passwd')
passwordElem.clear()
passwordElem.send_keys(password)
passwordElem.submit()

# Switch to gmail's basic html view for easier processing
browser.get("https://mail.google.com/mail/u/0/h/s32qz81kdosc/?zy=h&f=1")

# Find the button to compose and click it
composeElem = browser.find_element_by_link_text("Compose Mail")
composeElem.click()

# Find the email, subject and body text fields to input text from cli
toElem = browser.find_element_by_id("to")
toElem.clear()
toElem.send_keys(toEmail)
subjectElem = browser.find_element_by_name("subject")
subjectElem.clear()
subjectElem.send_keys(subject)
bodyElem = browser.find_element_by_name("body")
bodyElem.clear()
bodyElem.send_keys(body)

# Send email!
browser.find_element_by_name("nvp_bu_send").click()
time.sleep(1)

# Log out
browser.find_element_by_id("gb_71").click()

time.sleep(1)
browser.close()
