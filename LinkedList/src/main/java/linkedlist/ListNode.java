package linkedlist;


public class ListNode<E> {
    public E data;
    public ListNode<E> next;

    public ListNode(E data,ListNode<E> next) {
        this.data = data;
        this.next = next;
    }
}
