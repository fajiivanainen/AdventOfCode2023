import numpy as np
# Advent of code, day 3

digits = ('0','1','2','3','4','5','6','7','8','9')

# Function to find all numbers that are maximum 1 index away from any index on list

def findNumbers(text,index_list):
    char_list = list(text)
    current = []
    number_list = []
    isCloseToChar = False
    i = 0
    for char in char_list:
        if char in digits:
            current.append(char)            
            for index in index_list:
                if(index in [i-1,i,i+1]):
                    isCloseToChar = True

        else:
            if len(current) != 0:
                if isCloseToChar:
                    number_list.append(int(''.join(current)))
                    isCloseToChar = False
                current = []
        i = i + 1        
    return number_list


text = 'day3_input.txt'
with open(text,'r') as file:
    lines = file.readlines()

lines = lines[:-1]
sum_of_part_numbers = 0

for i in range(len(lines)):
    indices = []
    line = lines[i]

    j = 0
    for char in line:
        if char not in (digits + ('.',) + ('\n',)):
            indices.append(j)
        j = j + 1

    if i < (len(lines) - 1):
        j = 0
        next_line = lines[i + 1]
        for char in next_line:
            if char not in (digits + ('.',) + ('\n',)):
                indices.append(j)
            j = j + 1

    if i > 0:
        j = 0
        previous_line = lines[i - 1]
        for char in previous_line:
            if char not in (digits + ('.',) + ('\n',)):
                indices.append(j)
            j = j + 1
       
    unique_indices = sorted(list(set(indices)))

    validNumbers = findNumbers(line,unique_indices)
    sum_of_part_numbers += sum(validNumbers)

print(sum_of_part_numbers)
        


