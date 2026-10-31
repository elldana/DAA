# Sum of Left Leaves

The goal is to calculate the sum of all leaf nodes that are left children of their parents. I used a recursive DFS method with an additional boolean parameter that shows whether the current node is a left child. If the node is null, the method returns zero. If it is a leaf, its value is added only when the boolean parameter is true. For other nodes, I recursively calculate the sums of the left and right subtrees and add them together. Each node is visited once, so the time complexity is O(n). The recursion stack requires O(h) additional space.
