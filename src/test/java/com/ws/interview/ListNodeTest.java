package com.ws.interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yunhua
 * @date 2020-11-25
 * @see
 * @since 1.0.0
 */
public class ListNodeTest {

    class ListNode {
        private ListNode next;
        private int val;

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    /**
     * 请判断一个链表是否为回文链表。
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.getVal());
            temp = temp.getNext();
        }
        int start = 0;
        int end = list.size() - 1;
        while (start <= end) {
            if (!list.get(start).equals(list.get(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    @Test
    public void testIsPalindrome() {
        ListNode head = new ListNode();
        head.setVal(1);
        ListNode node2 = new ListNode();
        node2.setVal(3);
        head.setNext(node2);
        ListNode node3 = new ListNode();
        node3.setVal(3);
        node2.setNext(node3);
        ListNode node4 = new ListNode();
        node4.setVal(1);
        node3.setNext(node4);
        /**
         * 1 3 3 1
         */
        System.out.println("是否回文:" + isPalindrome(head));
    }

}
