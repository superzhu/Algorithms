package heap;

import java.io.InputStream;
import java.util.*;

/**
 * EPI Problem 11.3 : Design an algorithm for computing the running median of a sequence.
 */
public class OnlineMedian {
    private static List<Double> globalResult = new ArrayList<>();

    // @include
    public static void onlineMedian(InputStream sequence) {
        // minHeap stores the larger half seen so far.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // maxHeap stores the smaller half seen so far.
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(11, Collections.reverseOrder());

        Scanner s = new Scanner(sequence);
        while (s.hasNextInt()) {
            int x = s.nextInt();
            if (minHeap.isEmpty()) {
                // This is the very first element.
                minHeap.add(x);
            } else {
                if (x >= minHeap.peek()) {
                    minHeap.add(x);
                } else {
                    maxHeap.add(x);
                }
            }
            // Ensure minHeap and maxHeap have equal number of elements if
            // an even number of elements is read; otherwise, minHeap must have
            // one more element than maxHeap.
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.add(minHeap.remove());
            } else if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.remove());
            }

            // @exclude
           /* globalResult.add((minHeap.size() == maxHeap.size()
                    ? 0.5 * (minHeap.peek() + maxHeap.peek())
                    : minHeap.peek()));
            // @include
            System.out.println(minHeap.size() == maxHeap.size()
                    ? 0.5 * (minHeap.peek() + maxHeap.peek())
                    : minHeap.peek());*/
        }
    }
    // @exclude

}
