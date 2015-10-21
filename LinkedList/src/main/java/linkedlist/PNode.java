package linkedlist;

public class PNode<E> {
    public E data;
    public PNode<E> next;
    PNode<E> jump;

    public PNode(E data,PNode<E> next, PNode<E> jump) {
        this.data = data;
        this.next = next;
        this.jump = jump;
    }

    public PNode(E data,PNode<E> next) {
        this.data = data;
        this.next = next;
        this.jump = null;
    }
}
