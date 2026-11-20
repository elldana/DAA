# Convert Sorted Array to Binary Search Tree

## 1. Problem
Given an integer array `nums` sorted in strictly ascending order, convert it into a **height-balanced** Binary Search Tree (a binary tree in which the depths of the two subtrees of every node never differ by more than `1`).

## 2. Approach

### How the Algorithm Works
To satisfy both the **BST ordering invariant** and the **height-balance constraint**, I implemented a **Divide and Conquer** recursive algorithm in Java:
- Since `nums` is already sorted in ascending order, choosing any element `nums[mid]` as the root automatically places all smaller elements to its left (`nums[left ... mid - 1]`) and all larger elements to its right (`nums[mid + 1 ... right]`).
- To guarantee that the tree remains height-balanced, both subtrees must receive an equal (or nearly equal, differing by at most 1) number of elements. Therefore, at each recursive step, we select the **middle index**: `mid = left + (right - left) / 2`.
- Instead of slicing or copying subarrays, I pass integer boundary pointers `left` and `right` into a helper method `build(int[] nums, int left, int right)`:
  1. **Base Case:** If `left > right`, the current subarray segment is empty, so return `null`.
  2. **Create Root:** Calculate `mid` and construct `TreeNode root = new TreeNode(nums[mid])`.
  3. **Build Subtrees:** Recursively assign `root.left = build(nums, left, mid - 1)` and `root.right = build(nums, mid + 1, right)`.
  4. Return `root`.

### Written Tracing with Examples

**Example:** `nums = [-10, -3, 0, 5, 9]` (indices `0` to `4`)
- **Call 1 (`build(nums, 0, 4)`):**
  - `mid = 0 + (4 - 0) / 2 = 2` -> Root node is `nums[2] = 0`.
  - Left child calls `build(nums, 0, 1)` (subarray `[-10, -3]`).
  - Right child calls `build(nums, 3, 4)` (subarray `[5, 9]`).
- **Call 2 (`build(nums, 0, 1)`):**
  - `mid = 0 + (1 - 0) / 2 = 0` -> Node is `nums[0] = -10`.
  - Left child calls `build(nums, 0, -1)` -> `left > right`, returns `null`.
  - Right child calls `build(nums, 1, 1)` -> `mid = 1`, creates leaf node `-3` (both children return `null`).
- **Call 3 (`build(nums, 3, 4)`):**
  - `mid = 3 + (4 - 3) / 2 = 3` -> Node is `nums[3] = 5`.
  - Left child calls `build(nums, 3, 2)` -> `left > right`, returns `null`.
  - Right child calls `build(nums, 4, 4)` -> `mid = 4`, creates leaf node `9` (both children return `null`).

**Resulting Height-Balanced BST:**

      0
     / \
   -10  5
     \   \
     -3   9

### Challenges Faced & Approaches Tested
- **Sequential BST Insertion (Discarded):** My first idea was to loop through `nums` from index `0` to `N - 1` and insert each element into a BST one by one. I quickly realized this fails completely because inserting sorted values sequentially builds a completely right-skewed degenerate tree of height `N`, violating the height-balance requirement.
- **Array Copying vs. Index Pointers:** In my initial recursive design, I considered passing subarray copies using `Arrays.copyOfRange(nums, 0, mid)`. However, copying arrays at every level allocates `O(N log N)` extra memory and adds copying overhead. Passing `left` and `right` index boundaries eliminated all extra array allocations.
- **Integer Overflow Prevention:** I computed the midpoint as `left + (right - left) / 2` instead of `(left + right) / 2` to prevent integer overflow on very large arrays.

## 3. Complexity Analysis

### Time Complexity: `O(N)` (where `N` is the length of `nums`)
- **Explanation:** Each element in the input array `nums` is visited once to instantiate a single `TreeNode`. At every recursive call, computing `mid` and allocating a node takes `O(1)` time. Across `N` elements, the total runtime is `N * O(1) = O(N)`.

### Space Complexity: `O(log N)` auxiliary space (`O(N)` total including output tree)
- **Explanation:** Because the array is split strictly in half at every step, the constructed BST is guaranteed to be height-balanced with a height of `H = log₂(N)`. Consequently, the maximum depth of the JVM recursion call stack is `O(log N)`. (Note: If the `N` newly allocated `TreeNode` objects returned as the output tree are included, total space is `O(N)`).

## 4. Reflection / Improvement
- **Is there a more efficient approach?** Asymptotically, `O(N)` time complexity is optimal because every element in `nums` must be read at least once to construct `N` tree nodes.
- **What would you need to change?** Passing `(left, right)` indices already optimizes auxiliary space down to `O(log N)` without copying arrays. The only alternative would be an iterative construction using a `Queue` or `Deque` storing index ranges alongside parent node references to avoid recursion syntax.
- **What complexity could the improved solution achieve?** An iterative queue/stack-based implementation would achieve the exact same **`O(N)`** time complexity and **`O(log N)`** auxiliary space complexity.