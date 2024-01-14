import numpy as np
# advent of code day 1

# A few useful functions:

# Finds the first index of each instance of the wanted substring in string text
def find_all_substrings(text,substring):
    occurrences = []
    start_index = 0
    
    while (index := text.find(substring, start_index)) != -1:
        occurrences.append(index)
        start_index = index + 1

    return occurrences

# Replaces the letters at indices with the wanted replacements
def replace_substrings(text,start_indices,replacement):
    char_list = list(text)
    for i in start_indices:
        char_list[i] = replacement

    new_text = ''.join(char_list)
    return new_text

# Define the input text and divide it into lines: 
text = 'day1_input.txt'
with open(text,'r') as file:
    lines = file.readlines()

# Define mapping tables
numbers = ('0','1','2','3','4','5','6','7','8','9')
numbers_char = ('zero','one','two','three','four','five','six','seven','eight','nine')

new_lines = ['' for _ in range(len(lines))]

# Replace the first letter of each letter-number with the right number
i = 0
for line in lines:
    j = 0
    new_line = line
    for number in numbers_char:
        indices = find_all_substrings(line,number)
        new_line = replace_substrings(new_line,indices,numbers[j])
        j = j + 1

    new_lines[i] = new_line
    i = i + 1

# Find the pair of first and last number of each line, convert to int, calculate sum
sum = 0
for line in new_lines:
    pair = ''
    for character in line:
        if character in numbers:
            pair += character
    if pair != '':
        sum += int(pair[0] + pair[-1])


print(sum)
