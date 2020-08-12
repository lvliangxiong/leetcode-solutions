package com.joey.learning.oj.util.tree;

import leetcode.editor.cn.tree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 8/2/2020
 */
class TreeUtilsTest {
    @Test
    void toTree() {
        Integer[] arr = new Integer[]{10, 5, 15, 0, 8, 13, 20, 2, -5, 6, 9, 12, 14, 18, 25};
        TreeNode tree = TreeUtils.toTree(arr);
    }
}