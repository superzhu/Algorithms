package tree.search;

import java.util.Comparator;

/**
 * https://github.com/gaylemcd/ctci/tree/master/java/Chapter%2011/Question11_8
 */
public class IntComparable implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
