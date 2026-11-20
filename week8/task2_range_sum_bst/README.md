# Range Sum of BST

## 1. Problem
Given the `root` of a Binary Search Tree and two integers `low` and `high`, we need to calculate and return the sum of values of all nodes whose values lie within the inclusive range `[low, high]`.

## 2. Approach

### How the Algorithm Works
I solved this problem in Java using **Recursive Depth-First Search (DFS) with Branch Pruning**.

A naive traversal would visit all `N` nodes and check if `low <= node.val && node.val <= high`. However, leveraging the BST ordering rules allows us to prune (skip) entire subtrees that cannot possibly contain valid numbers:
1. **Base Case:** If `root == null`, return `0`.
2. **Prune Left Branch:** If `root.val < low`, then `root` and every single node in `root.left` are strictly smaller than `low`. We discard `root.left` and only recurse on `root.right`.
3. **Prune Right Branch:** If `root.val > high`, then `root` and every single node in `root.right` are strictly greater than `high`. We discard `root.right` and only recurse on `root.left`.
4. **Within Range:** If `low <= root.val && root.val <= high`, we add `root.val` to the recursive sums of both `root.left` and `root.right`.

### Written Tracing with Examples

**Example:** `root = [10, 5, 15, 3, 7, null, 18]`, `low = 7`, `high = 15`

       10
      /  \
     5    15
    / \     \
   3   7     18

- **Step 1 (`root = 10`):** `10` is inside `[7, 15]`. Include `10` and recurse on left (`5`) and right (`15`).
- **Step 2 (`root = 5`):** `5 < 7` (below `low`). **Prune** left subtree (`3`) completely and only recurse on right child (`7`).
- **Step 3 (`root = 7`):** `7` is inside `[7, 15]`. Both children are `null` (return `0`). Returns `7`.
- **Step 4 (`root = 15`):** `15` is inside `[7, 15]`. Include `15` and recurse on left (`null` -> `0`) and right (`18`).
- **Step 5 (`root = 18`):** `18 > 15` (above `high`). **Prune** right subtree and recurse on left (`null` -> `0`). Returns `0`.
- **Total Sum:** `10 + 7 + 15 = 32`.

### Challenges Faced & Approaches Tested
- **Unpruned Inorder Traversal (Discarded):** My initial draft performed a standard DFS visiting every node in the tree and adding values matching the range condition. While accurate, it wasted time inspecting nodes like `3` and `18` that are guaranteed to be out of range.
- **Early Termination Bug:** When first implementing pruning, I mistakenly returned `0` as soon as `root.val < low`. Testing showed this failed because even when `root.val` (`5`) is below `low` (`7`), its right subtree can still contain valid values (`7`). I fixed this by returning `rangeSumBST(root.right, low, high)` instead of `0`.

## 3. Complexity Analysis

### Time Complexity: `O(N)` (where `N` is the number of nodes in the tree)
- **Explanation:** While branch pruning skips many nodes in practice when `[low, high]` is narrow, in the worst-case scenario (e.g., `low = Integer.MIN_VALUE` and `high = Integer.MAX_VALUE`), every node in the tree falls inside `[low, high]`. No branches can be pruned, forcing the algorithm to visit all `N` nodes once.

### Space Complexity: `O(H)` (where `H` is the height of the tree)
- **Best / Balanced Case:** **`O(log N)`**
- **Worst Case (Skewed Tree):** **`O(N)`**
- **Explanation:** Auxiliary memory is consumed by the JVM recursion call stack. The maximum number of active stack frames at any point equals the height `H` of the tree (`log₂(N)` for balanced trees, `N` for completely skewed trees).

## 4. Reflection / Improvement
- **Is there a more efficient approach?** Asymptotically, `O(N)` worst-case time complexity cannot be improved because all `N` nodes could legitimately belong to the target range and must be summed.
- **What would you need to change?** To prevent potential `StackOverflowError` exceptions in Java when processing deeply skewed trees (up to `2 * 10^4` nodes), we can convert the recursive DFS into an **iterative DFS using an explicit `Deque<TreeNode>` stack** while keeping the exact same pruning rules.
- **What complexity could the improved solution achieve?** An iterative stack-based solution achieves the same **`O(N)`** worst-case time and **`O(H)`** auxiliary space, while safely avoiding JVM call stack limits.