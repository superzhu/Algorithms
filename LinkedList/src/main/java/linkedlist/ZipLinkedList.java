package linkedlist;

import linkedlist.cycle.SimpleLinkedList.LinkedNode;

/**
 * EPI: Given a singly linked list L: L0->L1->...->Ln-1->Ln,
 * reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->...
 *
 * https://github.com/leetcoders/LeetCode/blob/master/ReorderList.h
 * https://gist.github.com/ywei89/9226140
 */
public class ZipLinkedList<E> {
    //Head is the first element
    public void reorderList(LinkedNode head) {
        if(head == null || head.next == null)
            return;

        LinkedNode slow = head;
        LinkedNode fast = head.next.next;

        while (fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //Go to the mid point of the linked list
        if(fast!=null)
            slow = slow.next;

        LinkedNode mid = slow, cur = slow.next;
        while (cur.next != null) {
            LinkedNode mov = cur.next;
            cur.next = mov.next;
            mov.next = mid.next;
            mid.next = mov;
        }

        cur = head;
        while (cur != mid && mid.next!=null) {
            LinkedNode mov = mid.next;
            mid.next = mov.next;
            mov.next = cur.next;
            cur.next = mov;
            cur = cur.next.next;
        }
    }
}
