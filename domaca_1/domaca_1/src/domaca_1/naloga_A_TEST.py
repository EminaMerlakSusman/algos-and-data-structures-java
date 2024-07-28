file = open('nalogaA.txt', 'r')
output = open('rezultatiA_test.txt', 'w')
import time
(h, v, p) = map(int, file.readline().split())

#print(slices)
results = []
test_results = []
queries = []

t1 = time.time()
pets = 0
slices = []
prev = 0
prev_house = 0
prev_house_newcomers = 0
newcomers = []

test_slices = [0 for i in range(h)]


def process_query(num1, num2):
    global results
    global test_results
    global slices
    global test_slices
    st = int(num1)
    end = int(num2)
    queries.append((st, end))

    pets_before = slices[st][1]
    pets_until = slices[end][1]
    #test_results.append(sum(test_slices[st:end+1]))

    # if st == 0:  # edge case
    #     results.append(pets_until)
    if slices[end + 1][1] != pets_until and end != h:
        results.append(slices[end + 1][1] - pets_before)
    elif st != end:
        results.append(pets_until - pets_before)
    else:
        results.append(pets_until)

# # Test array
# arr = [2, 3, 4, 10, 40]
# x = 10
#
# # Function call
# result = binary_search(arr, 0, len(arr) - 1, x)
#
# if result != -1:
#     print("Element is present at index", str(result))
# else:
#     print("Element is not present in array")

for i in range(v):
    (letter, num1, num2) = file.readline().split()
    if i == 0:
        slices += [(0, 0)]
    if letter == 'V':
        house = int(num1)
        if house > prev_house:  # new person moving in
            pets = int(num2) + prev
            # filling pet numbers for houses up to this one, then filling this house's pets
            slices += [(k, prev) for k in range(prev_house + 1, house)] + [(house, pets)]
            test_slices[house] = int(num2)
            prev = pets
            prev_house = house

        if i == 0:
            slices += [(house, pets)]

        else: # change in slices list
            pets = int(num2) + prev
            # filling pet numbers for houses up to this one, then filling this house's pets
            
            prev_house_newcomers = house
    elif letter == 'P':
        process_query(num1, num2)


#
# for i in range(70):
#     file.readline()

for i in range(0, p):
    (letter, num1, num2) = file.readline().split()
    process_query(num1, num2)
    if i % 100 == 0:
        print('made 100 queries')

#print(slices)
print(time.time() - t1)
print(test_results==results)
#print(test_results, results)
#print(sum(test_results) - sum(results))
print(len(test_results), len(results))
# for i in range(101):
#     if test_results[i] != results[i]:
#         print('error:', test_results[i], results[i], 'index {}'.format(i), 'query:', queries[i])
#         query = queries[i]
#         val1 = test_slices[query[0]]
#         val2 = test_slices[query[1]]
#         print(val1, val2)

output_file = open("rezultatiA_test.txt", "w")
for i in results:
    output_file.write(str(i) + "\n")

