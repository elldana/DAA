# Path Sum

The goal is to check whether the tree contains a root-to-leaf path whose node values add up to the target sum. I used DFS with recursion. At each node, I subtract its value from the remaining target. When I reach a leaf, I check whether the remaining value is zero. If the current node is not a leaf, I search its left and right subtrees. The method returns true if at least one of these paths matches the target. Every node is visited at most once, so the time complexity is O(n). The recursive call stack requires O(h) space, where h is the height of the tree.
