digits = ('0','1','2','3','4','5','6','7','8','9')
text = """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4"""

lines = text.splitlines()
map_seq = []
latest_map = []
i = 0
for line in lines:
    if len(line) != 0 and i != 0:
        if(line[0] in digits):
            numbers_str = line.split(' ')
            numbers = []
            for number in numbers_str:
                numbers.append(int(number))
            latest_map.append(numbers)

        if (i >= len(lines) - 1) or (len(lines[i+1]) <= 0):           
            map_seq.append(latest_map)
            latest_map = []

    i = i + 1

#print(map_seq)

def squish_map(map_seq):
    if len(map_seq) <= 1:
        return map_seq[0]
    else:
        previous_map = squish_map(map_seq[:-1])
        new_map = combine_maps(previous_map,map_seq[-1])
        return new_map

def combine_maps(map1, map2):
    return_map = []
    overlaps1 = []
    overlaps2 = []
    j = 0
    for d1, s1,sp1 in map1:
        new_maps = []
        i = 0
        for d2,s2,sp2 in map2:
            if s2 + sp2 > d1 and s2 < d1 + sp1:
                sp_temp = s2 + sp2 - d1
                new_maps.append([d2 + sp2 - sp_temp,s1,sp_temp])
                new_maps.append([d1 + sp_temp,s1 + sp_temp,sp1 - sp_temp])
                new_maps.append([d2,s2,sp2 - sp_temp])
                overlaps1.append(j)
                overlaps2.append(i)
                for new_map in new_maps:
                    if new_map[2] > 0 and new_map[1] >= 0 and new_map[0] >= 0:
                        return_map.append(new_map)
                new_maps = []
            i = i + 1
        j = j + 1
    for k in range(len(map2)):
        if k not in overlaps2:
            return_map.append(map2[k])
    
    for k in range(len(map1)):
        if k not in overlaps1:
            return_map.append(map1[k])

    return return_map

# map1 = [[50, 98, 2], [52, 50, 48]]
# map2 = [[0, 15, 37], [37, 52, 2], [39, 0, 15]]
# print(combine_maps(map1,map2))

seeds_str = lines[0].split(':')[1].split(' ')
seeds = []
for seed in seeds_str:
    if seed != '':
        seeds.append(int(seed))

final_map = squish_map(map_seq)
sorted_final_map = sorted(final_map,key=lambda x: x[0])
print(sorted_final_map)
# for map_n in final_map:






