package heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Please find the smallest k numbers (in value) out of n numbers. For example, if given an array
 * with eight numbers {4, 5, 1, 6, 2, 7, 3, 8}, please return the least four numbers 1, 2, 3, and 4.
 *
 * In Java, the default implementation of type PriorityQueue is for minimum heaps.
 */
public class KLeastNumbers {
    public class ReversedComparator implements Comparator<Integer> {
        public int compare(Integer int1, Integer int2){
            int num1 = int1.intValue();
            int num2 = int2.intValue();

            if(num1 < num2)
                return 1;
            else if (num1 == num2)
                return 0;
            else
                return -1;
        }
    }

    public static void getLeastNumbers_1(int[] input, int[] output) {
        KLeastNumbers leastNumbers = new KLeastNumbers();
        ReversedComparator comparator = leastNumbers.new ReversedComparator();
        PriorityQueue<Integer> maxQueue = null;
        maxQueue = new PriorityQueue<Integer>(1, comparator);

        getLeastNumbers(input, maxQueue, output.length);

        Iterator<Integer> iter = maxQueue.iterator();
        for(int i = 0; i < output.length; ++i) {
            output[i] = iter.next();
        }
    }

    private static void getLeastNumbers(int[] input, PriorityQueue<Integer> output, int k) {
        output.clear();

        for(int i = 0; i < input.length; ++i) {
            if(output.size() < k)
                output.add(new Integer(input[i]));
            else {
                Integer max = output.peek();
                Integer number = new Integer(input[i]);
                if(output.comparator().compare(number, max) > 0) {
                    output.poll();
                    output.add(number);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbers = {4, 5, 1, 6, 2, 7, 3, 8};
        int[] output1 = new int[4];
        getLeastNumbers_1(numbers, output1);
    }
}
