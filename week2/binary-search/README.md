# 704. Binary Search

## 1. Problem
We have a sorted array of numbers and a target value. We need to find the target in the array and return its index. If the number isn't there, we just return -1

## 2. Approach
I solved this using binary search with left and right sides
1. Set `left` to 0 (the start of the array) and `right` to `nums.length - 1` (the end)
2. Use a `while (left <= right)` loop to keep searching while the search range is valid
3. Calculate the middle index with `mid = left + (right - left) / 2`
4. Compare `nums[mid]` with `target`
    if they are equal we found it, so return `mid`.
    if `nums[mid] < target`, the target is bigger, so it must be on the right side. i change it to `left = mid + 1`
    if `nums[mid] > target`, the target is smaller, so it's on the left side. i change it to `right = mid - 1`
5. if the loop ends and we didn't find the target just return -1

## 3. Time Complexity
Time Complexity: O(log n)

Every step cuts the work in half. Each time we check the middle item, we throw away half of the remaining list. Because the search area become smaller so quickly, we only need a few steps to find what we want

## 4. Space Complexity
Space Complexity: O(1)

The algorithm works in-place and doesn't create any other arrays or data structures. It only stores a few simple integer variables (`left`, `right`, and `mid`), so the extra memory stays constant regardless of how big the array is

## 5. Reflection / Improvement
- Is there a more efficient approach?
i think it's the most optimal solution
  
