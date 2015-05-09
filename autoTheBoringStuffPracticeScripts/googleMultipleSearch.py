#! /usr/bin/python3
# lucky.py - Opens several Google search results.

import sys, requests, webbrowser, bs4, time

print("Googling...")
res = requests.get("http://www.google.com/search?q=" + " ".join(sys.argv[1:]))
res.raise_for_status()

# Retrieve top search result links.
soup = bs4.BeautifulSoup(res.text)

# Open a browser tab for each result.
elems = soup.select(".r a")
numResults = min(5, len(elems)) #In case of less than 5 results

webbrowser.open('http://google.com' + elems[0].get('href'), new=1)

time.sleep(2) #Wait a bit for browser to open

# Open the rest of the results in tabs
for i in range(1, numResults):
    webbrowser.open_new_tab('http://google.com' + elems[i].get('href'))
