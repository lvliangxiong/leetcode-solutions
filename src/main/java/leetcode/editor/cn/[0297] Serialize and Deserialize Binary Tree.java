package leetcode.editor.cn;

import leetcode.editor.cn.tree.binarytree.TreeNode;

import java.util.*;

/**
 * <pre>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 *
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need
 * to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * </pre>
 */
class SerializeAndDeserializeBinaryTree {

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * <pre>
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     * </pre>
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            Integer[] arr = toArray(root);
            return arr.toString();
        }

        private Integer[] toArray(TreeNode root) {
            // BFS traversal
            Deque<TreeNode> queue = new ArrayDeque<>();
            TreeNode cur = root;
            queue.offer(cur);
            List<Integer> ans = new ArrayList<>();
            while (!queue.isEmpty()) {
                cur = queue.poll();
                if (cur == null) {

                } else {
                    queue.offer()
                }
            }
            ans.set();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Integer[] arr = toArray(data);
            return toTree(arr);
        }

        private TreeNode toTree(Integer[] array) {
            if (array == null || array.length == 0) return null;
            TreeNode root = new TreeNode(array[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int index = 1;
            while (!queue.isEmpty() && index < array.length) {
                TreeNode node = queue.poll();
                TreeNode left = array[index] == null ? null : new TreeNode(array[index]);
                index++;
                TreeNode right = array[index] == null ? null : new TreeNode(array[index]);
                index++;
                node.left = left;
                node.right = right;
                if (left != null) queue.add(left);
                if (right != null) queue.add(right);
            }
            return root;
        }

        private Integer[] toArray(String data) {
            String[] nums = data.substring(1, data.length() - 1).split(",");
            int n = nums.length;
            Integer[] ans = new Integer[n];
            for (int i = 0; i < n; i++) {
                ans[i] = nums[i].equals("null") ? null : Integer.parseInt(nums[i]);
            }
            return ans;
        }
    }

    // Your Codec object will be instantiated and called as such:
    // Codec codec = new Codec();
    // codec.deserialize(codec.serialize(root));
    //leetcode submit region end(Prohibit modification and deletion)

}