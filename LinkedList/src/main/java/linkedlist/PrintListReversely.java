package linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Please implement a function to print a list from its tail to head.
 */
public class PrintListReversely<E> {
    public void iterativePrint(LinkedList<E> list) {
        if(list == null)
            return;

        Deque<E> stack = new ArrayDeque<E>();

       /* ListIterator<E> itr = list.listIterator();
        while(itr.hasNext()) {
            stack.addFirst(itr.next());
        }*/

        for(E str: list) {
            stack.addFirst(str);
        }

        while(!stack.isEmpty()) {
            E elem = stack.removeFirst();
            System.out.println("Element: " + elem.toString());
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        PrintListReversely demo = new PrintListReversely();

        demo.iterativePrint(list);
    }
}
