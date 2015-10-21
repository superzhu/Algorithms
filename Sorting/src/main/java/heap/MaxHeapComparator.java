package heap;

import java.util.Comparator;

public class MaxHeapComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        // Comparator that sorts integers from highest to lowest
        if (o1 < o2) return 1;
        else if (o1 == o2) return 0;
        else return -1;
    }
}
