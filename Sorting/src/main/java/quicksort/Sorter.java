package quicksort;

/**
 * Interface for a sorting algorithm.  Sorts an array of
 * <code>Comparable</code> objects.  No other information about the
 * objects is assumed.
 */
public interface Sorter {
    /**
     * Sorts an array of <code>Comparable</code> objects.
     * @param array The array of <code>Comparable</code> objects to be sorted
     */
    public void sort(Comparable[] array);
}
