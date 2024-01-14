import numpy as np
digits = ('0','1','2','3','4','5','6','7','8','9')
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

def mapping_function(input_list,map_numbers):
    output_list = []
    for input in input_list:
        output = input
        for numbers in map_numbers:
            destination = numbers[0]
            source = numbers[1]
            span = numbers[2]
            if input >= source and input < source + span:
                difference = input - source
                output = destination + difference
        output_list.append(output)

    return output_list


# Part 1
seeds_str = lines[0].split(':')[1].split(' ')
seeds = []
for seed in seeds_str:
    if seed != '':
        seeds.append(int(seed))

# Part 2

    

#print(new_seeds)
i = 0
latest_map = []
values = seeds # Part 1

for line in lines:
    if len(line) != 0 and i != 0:
        if(line[0] in digits):
            numbers_str = line.split(' ')
            numbers = []
            for number in numbers_str:
                numbers.append(int(number))
            latest_map.append(numbers)

        if (i >= len(lines) - 1) or (lines[i+1][0] not in digits):           
            values = mapping_function(values,latest_map)
            latest_map = []

    i = i + 1

print(np.min(values))