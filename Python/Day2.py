import numpy as np
# Advent of code day 2


# Store max values of each color:
color_map = {'red': 12, 'green': 13, 'blue': 14}

# Define a function to check if a game is possible for assigned color map

def isPossible(shows_string):
    shows = shows_string.split(';')
    for show in shows:
        color_and_value = show.split(',')
        for cv in color_and_value:
            cv = cv.lstrip()
            color = cv.split(' ')[-1]
            value = int(cv.split(' ')[0])
            if (color_map[color] < value):
                return False
    
    return True

def findMaximums(shows_string):
    reds = []
    greens = []
    blues = []
    shows = shows_string.split(';')
    for show in shows:
        color_and_value = show.split(',')
        for cv in color_and_value:
            cv = cv.lstrip()
            color = cv.split(' ')[-1]
            value = int(cv.split(' ')[0])
            if (color == "red"):
                reds.append(value)
            elif (color == "green"):
                greens.append(value)
            elif (color == "blue"):
                blues.append(value)
    maximums = [np.max(reds),np.max(greens),np.max(blues)]
    return maximums

def product(set):
    assert len(set) > 0, "The set is empty"
    product = 1
    for value in set:
        product *= value
    return product


# Read input:

text = 'day2_input.txt'
with open(text,'r') as file:
    lines = file.readlines()

# Go trough each line to check every game. Add ID to sum if possible:

sum = 0
product_sum = 0
for line in lines:
    line = line.rstrip('\n')
    games_and_shows = line.split(':')
    if (games_and_shows[0] != ''):
        ID = int(games_and_shows[0].split(' ')[-1])
        if(isPossible(games_and_shows[1])):
            sum += ID
        maximums = findMaximums(games_and_shows[1]) # max seen amount of cubes on each game is the minimum number of cubes in the bag
        product_of_max = product(maximums)
        product_sum += product_of_max

print(sum)
print(product_sum)


