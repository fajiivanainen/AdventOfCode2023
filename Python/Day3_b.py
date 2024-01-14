from collections import Counter

digits = ('0','1','2','3','4','5','6','7','8','9')

def findNumbers(text,index_list):
    char_list = list(text)
    current = []
    number_list = []
    i_list = []
    isCloseToChar = False
    i = 0
    possible_index = 0
    for char in char_list:
        
        if char in digits:
            current.append(char)            
            for index in index_list:
                if(index in [i-1,i,i+1]):
                    isCloseToChar = True
                    possible_index = index                

        else:
            if len(current) != 0:
                if isCloseToChar:
                    number_list.append(int(''.join(current)))
                    i_list.append(possible_index)
                    possible_index = 0
                    isCloseToChar = False
                current = []
        i = i + 1        
    return list(zip(number_list,i_list))

text = 'day3_input.txt'
with open(text,'r') as file:
    lines = file.readlines()


gear_ratio_sum = 0
for i in range(len(lines)):
    line = lines[i]
    star_indices = []
    for j in range(len(line)):
        if line[j] == '*':
            star_indices.append(j)
    if (i != 0) and (i != len(lines) - 1):
        numbers_and_indices = []
        numbers_and_indices.extend(findNumbers(lines[i - 1],star_indices))
        numbers_and_indices.extend(findNumbers(lines[i + 1],star_indices))
        numbers_and_indices.extend(findNumbers(line,star_indices))

        if(len(numbers_and_indices) != 0):
            unzipped = zip(*numbers_and_indices)
            numbers, indices = map(list,unzipped)

            counter = Counter(indices)
            double_values = [key for key, count in counter.items() if count == 2]

            for value in double_values:
                product = 1
                for tuple in numbers_and_indices:
                    if tuple[1] == value:
                        product *= tuple[0]
                gear_ratio_sum += product
            
     
print(gear_ratio_sum)