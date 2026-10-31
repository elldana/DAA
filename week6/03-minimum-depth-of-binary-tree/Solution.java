import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


import java.util.*;

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> nodes = new ArrayDeque<>();
        Queue<Integer> depths = new ArrayDeque<>();
        nodes.offer(root);
        depths.offer(1);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            int depth = depths.poll();
            if (node.left == null && node.right == null) return depth;
            if (node.left != null) {
                nodes.offer(node.left);
                depths.offer(depth + 1);
            }
            if (node.right != null) {
                nodes.offer(node.right);
                depths.offer(depth + 1);
            }
        }
        return 0;
    }

    // Intentionally incorrect version, kept to demonstrate the common min-depth mistake.
    public int minDepthWrong(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.min(minDepthWrong(root.left), minDepthWrong(root.right));
    }

    public int minDepthDfs(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return 1 + minDepthDfs(root.right);
        if (root.right == null) return 1 + minDepthDfs(root.left);
        return 1 + Math.min(minDepthDfs(root.left), minDepthDfs(root.right));
    }
}
