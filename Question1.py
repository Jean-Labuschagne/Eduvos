def merge_sort(arr):
    if len(arr) > 1:
        mid = len(arr) //2
        left = merge_sort( arr[:mid])
        right = merge_sort( arr[mid:])

        i = j = k = 0
        while i < len(left) and j < len(right):
            if left[i] < right[j]:
                arr[k] = left[i]
                i += 1
            else:
                arr[k] = right[j]
                j += 1
            k += 1
        while i < len(left):
            arr[k] = left[i]
            i+=1
            k+=1

        while j < len(right):
            arr[k] = right[j]
            j+=1
            k+=1
    return arr

numbers = [13,5,34,49,42,2,73,54,40,18]
print("Unsorted list: \n", numbers)
print("Sorted list: \n", merge_sort(numbers.copy()))