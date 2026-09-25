# 278. First Bad Version

## 1. Problem
We have `n` versions of product numbered from 1 to `n`. At some point one version failed and after that all other versions became bad too. We need to find this first bad point

## 2. Approach
I solved this using binary search with two points `left` and `right`
1. Set `left` to 1 (the first version) and `right` to `n` (the latest version)
2. Use a `while (left < right)` loop
3. Calculate the middle version using `mid = left + (right - left) / 2` 
4. Check the version with `isBadVersion(mid)`:
   - if it is `true`, it means this version is bad. The first bad version could be this `mid` or somewhere to the left, so I set `right = mid` (keeping `mid` in range)
   - if it is `false`, it means this version is still good, so the bad one must be to the right. I set `left = mid + 1`
5. When the loop ends `left` and `right` converge on the same number, which is our first bad version. So I just return `left` (i can return `right` too^ it doesn't matter)

## 3. Time Complexity
Time Complexity: O(log n)

Its just standard binary search, every check cuts the remaining versions in half. Instead of checking all versions from 1 to n one by one, we only need around log2(n) calls to the API

## 4. Space Complexity
Space Complexity: O(1)

The algorithm uses constant extra space. It only tracks a few integer variables (`left`, `right`, and `mid`) without adding any arrays or additional memory

## 5. Reflection / Improvement
- **Is there a more efficient approach?**
  I think this is the most optimal solution. Since we cannot know whether a version is bad without calling the API, and the array of boolean outcomes `[false, ..., false, true, ..., true]` is monotonic, binary search provides minimum number of API calls
- **What could be improved?**
  I think the code is already very clean