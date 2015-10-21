package quicksort;

import java.util.Collections;
import java.util.List;

/**
 *  The <tt>Quick3way</tt> class provides static methods for sorting an
 *  array using quicksort with 3-way partitioning.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  One straightforward idea is to partition the array into three parts, one each for items with
 *  keys smaller than, equal to, and larger than the partitioning item's key. Accomplishing this
 *  partitioning was a classical programming exercise popularized by E. W. Dijkstra as the Dutch
 *  National Flag problem, because it is like sorting an array with three possible key values,
 *  which might correspond to the three colors on the flag.
 */
public class Quick3way {

    // This class should not be instantiated.
    private Quick3way() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        //StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray a[lo .. hi] using 3-way partitioning
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
        assert isSorted(a, lo, hi);
    }



    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // does v == w ?
    private static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void dutchFlagPartition(int pivotIndex, List<Integer> A) {
        int pivot = A.get(pivotIndex);

        /**
         * Keep the following invariants during partitioning:
         * bottom group: A.subList(0 : smaller).
         * middle group: A.subList(smaller : equal).
         * unclassified group: A.subList(equal : larger + 1).
         * top group: A.subList(larger + 1, A.size()).
         */
        int smaller = 0, equal = 0, larger = A.size() - 1;
        // Keep iterating as long as there is an unclassified element.
        while (equal <= larger) {
            // A.get(equal) is the incoming unclassified element.
            if (A.get(equal) < pivot) {
                Collections.swap(A, smaller++, equal++);
            } else if (A.get(equal) == pivot) {
                ++equal;
            } else { // A.get(equal) > pivot.
                Collections.swap(A, equal, larger--);
            }
        }
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }



    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; 3-way
     * quicksorts them; and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        //String[] a = StdIn.readAllStrings();
        Integer[] a = {8,9,10,6,18,3,20,6};
        Quick3way.sort(a);
        show(a);
    }

}
