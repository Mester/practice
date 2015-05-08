#! /usr/bin/python3

text = open("hard213.txt")
lines = text.readlines()
text.close()
#print(lines)

def discrepancy(stepstring):	
	return abs(stepstring.count('a') - stepstring.count('b'))
	
for line in lines:
	largestDiscrepancy = 0
	for x in range(len(line)):
		for y in range(len(line), x, -1):
			if len(line[x:y]) <= largestDiscrepancy:
				break
			for n in range(1, y-x):
				if len(line[x:y:n]) <= largestDiscrepancy:
					break
				newDiscrepancy = discrepancy(line[x:y:n])
				if largestDiscrepancy < newDiscrepancy:
					largestDiscrepancy = newDiscrepancy
					#print(largestDiscrepancy)
	print(largestDiscrepancy)