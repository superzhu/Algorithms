package linkedlist;

import java.util.Random;

/**
 * EPI 8.4 Copy a postings list
 * Implement a function which takes as input a pointer to the head of a
 * postings list L, and returns a copy of the postings list. Your function should take O(n)
 * time, where n is the length of the postings list and should use O(1) storage beyond
 * that required for the n nodes in the copy. You can modify the original list, but must
 * restore it to its initial state before returning.
 */
public class CopyingPostingsList {
    // @include
    public static PNode<Integer> copyPostingsList(PNode<Integer> L) {
        if (L == null) {
            return null;
        }

        // Stage 1: Makes a copy of the original list without assigning the jump
        //          field, and creates the mapping for each node in the original
        //          list to the copied list.
        PNode<Integer> iter = L;
        while (iter != null) {
            PNode<Integer> newNode = new PNode<>(iter.data, iter.next, null);
            iter.next = newNode;
            iter = newNode.next;
        }

        // Stage 2: Assigns the jump field in the copied list.
        iter = L;
        while (iter != null) {
            if (iter.jump != null) {
                iter.next.jump = iter.jump.next;
            }
            iter = iter.next.next;
        }

        // Stage 3: Reverts the original list, and assigns the next field of
        //          the copied list.
        iter = L;
        PNode<Integer> newListHead = iter.next;
        while (iter.next != null) {
            PNode<Integer> temp = iter.next;
            iter.next = temp.next;
            iter = temp;
        }
        return newListHead;
    }
    // @exclude

    public static <T> void checkPostingsListEqual(PNode<T> a, PNode<T> b) {
        while (a != null && b != null) {
            System.out.print(a.data + " ");
            assert(a.data == b.data);
            assert(a.jump == null && b.jump == null ||
                    (a.jump != null && b.jump != null && a.jump.data == b.jump.data));
            if (a.jump != null) {
                System.out.print(a.jump.data);
            }
            System.out.println("");
            a = a.next;
            b = b.next;
        }

        assert(a == null && b == null);
    }

    public static void main(String[] args) {
        Random gen = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = gen.nextInt(1000) + 1;
            }

            PNode<Integer> L = null;
            PNode<Integer> curr = L;
            for (int i = 0; i < n; ++i) {
                PNode<Integer> temp = new PNode<>(i, null, null);
                if (L != null) {
                    curr.next = temp;
                    curr = temp;
                } else {
                    curr = L = temp;
                }

                // Randomly assigned a jump node.
                int jumpNum = (i > 0) ? gen.nextInt(i) : 0;
                PNode<Integer> jump = L;
                while (jumpNum-- != 0) {
                    jump = jump.next;
                }
                temp.jump = jump;
            }

            PNode<Integer> copied = copyPostingsList(L);
            checkPostingsListEqual(L, copied);
        }
    }
}
