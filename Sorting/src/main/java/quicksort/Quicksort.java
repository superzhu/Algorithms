package quicksort;

/**
 * Implements the {@link Sorter} interface via quicksort from
 *  page 146 of <i>Introduction to Algorithms</i>, Second edition.
 */
public class Quicksort implements Sorter {
    protected Partitioner part;

    protected void quicksort(Comparable[] array, int p, int r) {
        if (p < r) {
            int q = part.partition(array, p, r);
            quicksort(array, p, q - 1);
            quicksort(array, q + 1, r);
        }
    }

    public void sort(Comparable[] array) {
        part = new Partitioner(); // use a deterministic partitioner
        quicksort(array, 0, array.length - 1); // sort using it
    }
}
