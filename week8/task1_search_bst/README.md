# Search in a Binary Search Tree

## 1. Problem
Given the `root` of a Binary Search Tree (BST) and an integer target value `val`, we need to locate the node whose value equals `val` and return the subtree rooted at that node. If no node with the target value exists in the BST, we must return `null`.

## 2. Approach

### How the Algorithm Works
To solve this problem in Java, I implemented an **iterative search algorithm** that relies directly on the core invariant of a Binary Search Tree:
- All values in a node's **left subtree** are strictly smaller than the node's value.
- All values in a node's **right subtree** are strictly greater than the node's value.

Instead of blindly searching every node in the tree, I initialize a pointer `current = root` and advance downward one level per iteration using a `while (current != null)` loop:
1. If `current.val == val`, the target is found and `current` is immediately returned.
2. If `val < current.val`, the target cannot be in the right subtree, so I move left (`current = current.left`).
3. If `val > current.val`, the target cannot be in the left subtree, so I move right (`current = current.right`).
4. If `current` reaches `null`, the value does not exist in the tree and the method returns `null`.

### Written Tracing with Examples

**Example 1:** `root = [4, 2, 7, 1, 3]`, `val = 2`

      4
     / \
    2   7
   / \
  1   3

- **Iteration 1:** `current` points to node `4`. Compare `val (2)` with `4`. Since `2 < 4`, move left (`current = current.left`, node `2`).
- **Iteration 2:** `current` points to node `2`. Compare `val (2)` with `2`. They match (`2 == 2`).
- **Result:** Return node `2` (which represents the subtree `[2, 1, 3]`).

**Example 2:** `root = [4, 2, 7, 1, 3]`, `val = 5`
- **Iteration 1:** `current` is at `4`. Since `5 > 4`, move right to node `7`.
- **Iteration 2:** `current` is at `7`. Since `5 < 7`, move left (`current = current.left`, which is `null`).
- **Termination:** `current == null`, loop exits and returns `null`.

### Challenges Faced & Approaches Tested
- **Brute-Force Traversal (Discarded):** At first, I considered writing a standard recursive Depth-First Search (DFS) that inspects both `left` and `right` children regardless of their values. I discarded this because it ignores the BST property and performs unnecessary `O(N)` work on branches that cannot contain `val`.
- **Recursive vs. Iterative BST Search:** I also tested a recursive BST search (`return searchBST(root.left, val)`). While functionally identical, recursion consumes call stack memory proportional to the tree's depth. Switching to an iterative `while` loop eliminated JVM call stack overhead.

## 3. Complexity Analysis

### Time Complexity: `O(H)` (where `H` is the height of the tree)
- **Best / Balanced Case:** **`O(log N)`**
- **Worst Case (Skewed Tree):** **`O(N)`**
- **Explanation:** In every iteration of the `while` loop, the algorithm moves strictly down one level of the tree and eliminates the opposite subtree from consideration. Thus, the maximum number of comparisons is equal to the height `H` of the tree. In a height-balanced BST, `H = log₂(N)`, resulting in `O(log N)` time. In a completely skewed tree (where every node has only one child), `H = N`, resulting in `O(N)` time.

### Space Complexity: `O(1)`
- **Explanation:** The iterative implementation uses only a single reference pointer (`TreeNode current`) regardless of the number of nodes `N` in the tree. No recursion call stack or auxiliary data structures are allocated, giving constant `O(1)` auxiliary space.

## 4. Reflection / Improvement
- **Is there a more efficient approach?** Asymptotically, `O(H)` time and `O(1)` space is already optimal for searching in a standard Binary Search Tree because we must traverse from the root down to the target node's depth to verify its presence.
- **What would you need to change?** The code itself cannot be improved asymptotically. To guarantee `O(log N)` worst-case time complexity on all inputs, the underlying data structure would need to be a self-balancing BST (such as an AVL Tree or Red-Black Tree, like Java's `TreeMap`), ensuring `H` never degrades to `N`.
- **What complexity could the improved solution achieve?** With a self-balancing BST structure, worst-case time complexity improves from `O(N)` to **`O(log N)`**, while auxiliary space remains **`O(1)`**.