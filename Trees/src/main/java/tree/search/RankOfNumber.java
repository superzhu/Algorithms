package tree.search;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Imagine you are reading in a stream of integers. Periodically, you wish to be able to
 * look up the rank of a number x (the number of values less than or equal to x). Implement
 * the data structures and algorithms to support these operations. That is, implement
 * the method track(int x), which is called when each number is generated,
 * and the method getRankOf'Number (int x), which returns the number of values
 * less than or equal to x (not including x itself).
 */
public class RankOfNumber {
    private static RankNode root = null;

    public static void track(int number) {
        if (root == null) {
            root = new RankNode(number);
        } else {
            root.insert(number);
        }
    }

    public static int getRankOfNumber(int number) {
        return root.getRank(number);
    }

    public static void main(String[] args) {
        int size = 100;
        //{20,15,25,10,23,5,13,24}
        int[] list = AssortedMethods.randomArray(size, -100, 100);
        for (int i = 0; i < list.length; i++) {
            track(list[i]);
        }

        int[] tracker = new int[size];
        for (int i = 0; i < list.length; i++) {
            int v = list[i];
            int rank1 = root.getRank(list[i]);
            tracker[rank1] = v;
        }

        for (int i = 0; i < tracker.length - 1; i++) {
            if (tracker[i] != 0 && tracker[i + 1] != 0) {
                if (tracker[i] > tracker[i + 1]) {
                    System.out.println("ERROR at " + i);
                }
            }
        }

        System.out.println("Array: " + AssortedMethods.arrayToString(list));
        System.out.println("Ranks: " + AssortedMethods.arrayToString(tracker));
    }
}
