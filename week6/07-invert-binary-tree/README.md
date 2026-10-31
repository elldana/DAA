# Invert Binary Tree

The task is to mirror a binary tree by swapping the left and right child of every node. I solved it recursively. If the current node is null, I return null. Otherwise, I recursively invert the right subtree and assign it as the new left subtree. Then I invert the original left subtree and assign it as the new right subtree. After these operations, the current node is returned. Every node is visited once, so the time complexity is O(n). The recursion stack uses O(h) additional space, where h is the height of the tree.
