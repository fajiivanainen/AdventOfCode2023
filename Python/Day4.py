import numpy as np

text = 'day4_input.txt'
with open(text,'r') as file:
    lines = file.readlines()


# return how many matches two lists have
def noOfMatches(winners,owns):
    matches = 0
    for own in owns:
        for winner in winners:
            if winner == own:
                matches += 1
    return matches


sum = 0
copies = np.ones(len(lines) - 1)

i = 0
for line in lines:
    if ':' in line:
        winners_and_owns = line.split(':')[1].split('|')
        winners_str = winners_and_owns[0]
        owns_str = winners_and_owns[1]

        winners = []
        owns = []

        for char in winners_str.split(' '):
            if char != '':
                winners.append(int(char))
        
        for char in owns_str.split(' '):
            if char != '':
                owns.append(int(char))

        matches = noOfMatches(winners,owns)

        # Part b
        if i + matches < len(lines) - 1:
            for j in range(matches):
                for k in range(int(copies[i])):
                    copies[i+(j+1)] += 1
        
        # Part a
        line_points = 0
        for match in range(matches):
            if line_points == 0:
                line_points += 1
            else:
                line_points *= 2
        sum += line_points
    i = i + 1

print(int(np.sum(copies)))
print(sum)


