package leetcode.editor.cn.tree.binarytree;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 7/31/2020
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}