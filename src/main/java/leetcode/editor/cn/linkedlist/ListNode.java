package leetcode.editor.cn.linkedlist;

/**
 * @author LiangxiongLyu
 * @version 1.0
 * @date 7/29/2020
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
