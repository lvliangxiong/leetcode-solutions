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
        TreeNode NULL = new TreeNode();

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            Integer[] arr = toArray(root);
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (Integer num : arr) {
                sb.append(num).append(',');
            }
            if (arr.length > 0) sb.setLength(sb.length() - 1);
            sb.append(']');
            return sb.toString();
        }

        private Integer[] toArray(TreeNode root) {
            if (root == null) return new Integer[0];
            // BFS traversal
            Deque<TreeNode> current = new ArrayDeque<>();
            Deque<TreeNode> next = new ArrayDeque<>();
            current.offer(root);
            List<Integer> ans = new ArrayList<>(); // ArrayList allows null, but ArrayDeque doesn't
            int size = 0;
            while (!current.isEmpty()) {
                boolean end = true;
                while (!current.isEmpty()) {
                    TreeNode cur = current.poll();
                    if (cur != NULL) {
                        ans.add(cur.val);
                        size = ans.size();
                        next.add(cur.left == null ? NULL : cur.left);
                        if (end && next.peekLast() != NULL) end = false;
                        next.add(cur.right == null ? NULL : cur.right);
                        if (end && next.peekLast() != NULL) end = false;
                    } else ans.add(null);
                }
                if (end) break;
                Deque<TreeNode> tmp = current;
                current = next;
                next = tmp;
            }
            return ans.toArray(new Integer[size]);
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
            if (data.equals("[]")) return new Integer[0];
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


    /**
     * T -> (T) num (T) | X
     */
    public class BNFCodec {
        int index = 0;

        public String serialize(TreeNode root) {
            // String concatenate by + operator is really expensive, use StringBuilder or StringBuffer instead.
            StringBuilder sb = new StringBuilder();
            serializeHelper(root, sb);
            return sb.toString();
        }

        private void serializeHelper(TreeNode root, StringBuilder sb) {
            if (root == null) sb.append('X');
            else {
                sb.append('(');
                serializeHelper(root.left, sb);
                sb.append(')');
                sb.append(root.val);
                sb.append('(');
                serializeHelper(root.right, sb);
                sb.append(')');
            }
        }

        public TreeNode deserialize(String data) {
            return parse(data);
        }

        public TreeNode parse(String data) {
            // X 代表空树
            if (data.charAt(index) == 'X') {
                ++index;
                return null;
            }
            // 处理 (left subtree) num (right subtree) 形式的字符串
            TreeNode cur = new TreeNode(0); // 初始化根节点
            cur.left = parseSubtree(data);
            cur.val = parseInt(data); // 为根节点重新赋值
            cur.right = parseSubtree(data);
            return cur;
        }

        public TreeNode parseSubtree(String data) {
            ++index; // 跳过左括号
            TreeNode subtree = parse(data);
            ++index; // 跳过右括号
            return subtree;
        }

        public int parseInt(String data) {
            int x = 0, sign = 1;
            // 当前字符为符号位 -
            if (!Character.isDigit(data.charAt(index))) {
                sign = -1;
                ++index;
            }
            // 处理连续的数字字符
            while (Character.isDigit(data.charAt(index))) {
                x = x * 10 + data.charAt(index++) - '0';
            }
            return x * sign;
        }
    }
}