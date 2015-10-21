package quicksort;

/**
 * Class for partitioning an array of {@link Comparable} objects.
 * Implements the Partition procedure from page 146 of
 * <i>Introduction to Algorithms</i>, Second edition.
 */
public class Partitioner {
    /**
     * Partitions a subarray around its last element.
     * @param array  The array containing the subarray to be partitioned
     * @param p      Index of the beginning of the subarray.
     * @param r      Index of the end of the subarray.
     * @return
     */
    public int partition(Comparable[] array, int p, int r) {
        if(p == r)
            return p;

        Comparable x = array[r]; // x is the pivot
        int i = p - 1;

        // Maintain the following invariant:
        //   array[p..i] <= x,
        //   array[i+1..j-1] > x, and
        //   array[r] = x.
        for (int j = p; j < r; j++) {
            if (array[j].compareTo(x) <= 0) {
                i++;
                exchange(array, i, j);
            }
        }

        //Put the pivot value in its correct place and return that index.
        exchange(array, i+1, r);
        return i + 1;
    }

    /**
     * Exchanges the objects at two positions within an array.
     * @param array
     * @param i  The index of one object.
     * @param j  The index of the other object.
     */
    public void exchange(Object[] array, int i, int j) {
        Object t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    public static void main(String[] args) {
        Partitioner pa = new Partitioner();
        Integer[] values = {5,16,7,20,3,22,10};

        pa.partition(values,0,values.length-1);
    }
}
