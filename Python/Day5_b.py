import numpy as np
digits = ('0','1','2','3','4','5','6','7','8','9')
characters = ('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
# text = """seeds: 79 14 55 13

# seed-to-soil map:
# 50 98 2
# 52 50 48

# soil-to-fertilizer map:
# 0 15 37
# 37 52 2
# 39 0 15

# fertilizer-to-water map:
# 49 53 8
# 0 11 42
# 42 0 7
# 57 7 4

# water-to-light map:
# 88 18 7
# 18 25 70

# light-to-temperature map:
# 45 77 23
# 81 45 19
# 68 64 13

# temperature-to-humidity map:
# 0 69 1
# 1 0 69

# humidity-to-location map:
# 60 56 37
# 56 93 4"""

# lines = text.splitlines()

text = 'day5_input.txt'
with open(text,'r') as file:
    lines = file.readlines()

def backwards_map(output,map_numbers):
    returnValue = output
    for numbers in map_numbers:
        destination = numbers[0]
        source = numbers[1]
        span = numbers[2]
        if (output >= destination) and (output < destination + span):
            difference = output - destination
            returnValue = source + difference
    return returnValue

seeds_str = lines[0].split(':')[1].split(' ')
seeds = []
for seed in seeds_str:
    if seed != '':
        seeds.append(int(seed))


for candidate in range(1000000000):
    value = candidate
    i = len(lines) - 1
    latest_map = []
    while i >= 0:
        line = lines[i]
        if len(line) != 0 and i != 0:
            if(line[0] in digits):
                numbers_str = line.split(' ')
                numbers = []
                for number in numbers_str:
                    numbers.append(int(number))
                latest_map.append(numbers)

            if (i <= 1) or (len(lines[i-1]) > 0 and lines[i-1][0] not in digits):
                #print(value,latest_map)
                value = backwards_map(value,latest_map)
                latest_map = []
        i = i - 1
    print(value,candidate)
    if value in seeds:
        print(value, candidate)
        break
        