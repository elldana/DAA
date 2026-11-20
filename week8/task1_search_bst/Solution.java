package task1_search_bst;

import java.util.*;

public class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode current = root;

        while (current != null) {
            if (current.val == val) {
                return current;
            } else if (val < current.val) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();
            if (i < values.length && values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.offer(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }

    public static List<Integer> treeToList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        TreeNode root1 = buildTree(new Integer[] { 4, 2, 7, 1, 3 });
        TreeNode found1 = sol.searchBST(root1, 2);
        System.out.println("Test 1 (val=2): " + treeToList(found1)); 

        TreeNode found2 = sol.searchBST(root1, 5);
        System.out.println("Test 2 (val=5): " + treeToList(found2)); 
    }
}