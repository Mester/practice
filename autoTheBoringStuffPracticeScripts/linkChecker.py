#! /usr/bin/python3
# linkChecker.py - Checks all links on a page to see if they are valid
# Usage: ./linkChecker.py website

import requests, bs4, os, sys

if len(sys.argv) < 2:
    print("Usage: ./linkChecker.py website")
    sys.exit()

# Get the main page
url = sys.argv[1]
res = requests.get(url)
soup = bs4.BeautifulSoup(res.text)
# Iterate through all links
for link in soup.select('a'):
    linkUrl = link.get("href")
    # Check what kind of link the href attribute gives
    # and make sure that it's formatted properly
    if not linkUrl.startswith("http"):
        if not linkUrl.startswith("/"):
            linkUrl = "/" + linkUrl
        linkUrl = url + linkUrl

    
    worksString = ""
    #res = requests.get(linkUrl)
    try:
        res = requests.get(linkUrl)
        res.raise_for_status()
        worksString = "works!"
    except:
        worksString = "does not work! Status code: " + str(res.status_code)

    print("Link %s %s" % (linkUrl, worksString))
