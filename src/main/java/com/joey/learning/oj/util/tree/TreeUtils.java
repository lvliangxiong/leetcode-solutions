package com.joey.learning.oj.util.tree;

import leetcode.editor.cn.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/2/2020
 */
public class TreeUtils {
    public static TreeNode toTree(Integer[] array) {
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
}
