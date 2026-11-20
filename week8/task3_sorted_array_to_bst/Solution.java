package task3_sorted_array_to_bst;

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

    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int left, int right) {

        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = build(nums, left, mid - 1);
        root.right = build(nums, mid + 1, right);

        return root;
    }

    public static List<Integer> treeToLevelOrder(TreeNode root) {
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
            } else {
                result.add(null);
            }
        }
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = { -10, -3, 0, 5, 9 };
        TreeNode bst1 = sol.sortedArrayToBST(nums1);
        System.out.println("Test 1: " + treeToLevelOrder(bst1)); 

        int[] nums2 = { 1, 3 };
        TreeNode bst2 = sol.sortedArrayToBST(nums2);
        System.out.println("Test 2: " + treeToLevelOrder(bst2)); 
    }
}