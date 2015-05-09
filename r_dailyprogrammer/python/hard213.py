#! /usr/bin/python3

text = open("hard213.txt")
lines = text.readlines()
text.close()
#print(lines)

def value(char):
	if char == 'a':
		return 1
	else:
		return -1
	

for line in lines:
	maxDiscrepancy = 0
	length = len(line)
	for step in range(1, length):
		if length / step < maxDiscrepancy:
			break
		
		for offset in range(0, step):
			currentPositive = 0
			currentNegative = 0
			for i in range(offset, length, step):
				currentPositive += value(line[i])
				if currentPositive < 0:
					currentPositive = 0
				elif currentPositive > maxDiscrepancy:
					maxDiscrepancy = currentPositive
					
				currentNegative += value(line[i])
				if currentNegative > 0:
					currentNegative = 0
				elif abs(currentNegative) > maxDiscrepancy:
					maxDiscrepancy = abs(currentNegative)
				
	print(maxDiscrepancy)