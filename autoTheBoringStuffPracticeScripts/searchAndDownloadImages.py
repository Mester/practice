#! /usr/bin/python3
# searchAndDownloadImages.py - Searches imgur and then downloads images from result
# Usage: ./searchAndDownloadImages.py search term

import requests, bs4, os, sys

os.makedirs("imgurResults", exist_ok=True)

if len(sys.argv) < 2:
    print("Usage: ./searchAndDownloadImages.py search term")
    sys.exit()

print("Searching imgur for %s..." % " ".join(sys.argv[1:]))
res = requests.get("http://imgur.com/search/score?q=" + " ".join(sys.argv[1:]))
res.raise_for_status()
soup = bs4.BeautifulSoup(res.text)
amount = 0

# Go through each image element
for imageLink in soup.select('.post a'):
    # Each element first links to the gallery
    galleryUrl = "http://imgur.com" + imageLink.get("href")
    if galleryUrl[7] == 's':
        continue
    amount += 1

    # Go to the gallery to find the actual image url
    res = requests.get(galleryUrl)
    res.raise_for_status()
    gallerySoup = bs4.BeautifulSoup(res.text)

    # Check if it's an image or a video
    finalUrl = "http:"
    if gallerySoup.select('#image img') != []:
        finalUrl = finalUrl + gallerySoup.select('#image img')[0].get("src")
    elif gallerySoup.select('#image source[type="video/mp4"]') != []:
         finalUrl = finalUrl + gallerySoup.select('#image source[type="video/mp4"]')[0].get("src")
    else:
        continue
    
    print("Downloading file number %s %s..." % (str(amount), finalUrl))
    res = requests.get(finalUrl)
    res.raise_for_status()
    imageFile = open(os.path.join("imgurResults", os.path.basename(finalUrl)), "wb")
    for chunk in res.iter_content(100000):
        imageFile.write(chunk)
    imageFile.close()
    

print("Done! A total of %s files downloaded." % str(amount))
