/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// NOTE: Remember kids, copyOfRange is inclusive on the lower bound, but exclusive on the upper
// e.g. the range [lowerBound, uppperBound)
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        // first, find the half point
        int halfIndex = nums.length/2;
        // then make a node based on that
        TreeNode root = new TreeNode(nums[halfIndex]);
        // if there's content to the left
        if (halfIndex-1>=0) {
            // perform the same operation, similar to a binary search
            // stick the result into our left
            root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, halfIndex));
        }
        // if there's content to the right
        if (halfIndex+1<nums.length) {
            // perform the same operation, similar to a binary search
            // stick the result into our right
            root.right = sortedArrayToBST(Arrays.copyOfRange(nums, halfIndex+1, nums.length));
        }
        // return results
        return root;
    }
}
