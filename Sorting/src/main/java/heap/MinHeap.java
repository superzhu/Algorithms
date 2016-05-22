package heap;


public class MinHeap<E extends Comparable<? super E>> {
    private E[] Heap;   // Pointer to the heap array
    private int size;   // Maximum size of the heap
    private int n;      // Number of things in heap

    /** Constructor supporting preloading of heap contents */
    public MinHeap(E[] h, int num, int max) {
        Heap = h;
        n = num;
        size = max;
        buildheap();
    }

    /** @return Current size of the heap */
    public int heapsize() {
        return n;
    }

    /** @return True if pos a leaf position, false otherwise */
    public boolean isLeaf(int pos) {
        return (pos >= n/2) && (pos < n);
    }

    /** @return Position for left child of pos */
    public int leftchild(int pos) {
        assert pos < n/2 : "Position has no left child";
        return 2*pos + 1;
    }

    /** @return Position for right child of pos */
    public int rightchild(int pos) {
        assert pos < (n-1)/2 : "Position has no right child";
        return 2*pos + 2;
    }

    /** @return Position for parent */
    public int parent(int pos) {
        assert pos > 0 : "Position has no parent";
        return (pos-1)/2;
    }

    /** Insert val into heap */
    public void insert(E val) {
        assert n < size : "Heap is full";
        int curr = n++;
        Heap[curr] = val;             // Start at end of heap
        // Now sift up until curr's parent's key > curr's key
        while ((curr != 0)  &&
                (Heap[curr].compareTo(Heap[parent(curr)]) < 0)) {
            DSutil.swap(Heap, curr, parent(curr));
            curr = parent(curr);
        }
    }

    /** Heapify contents of Heap */
    public void buildheap() {
        for (int i=n/2-1; i>=0; i--)
            siftdown(i);
    }

    /** Put element in its correct place */
    private void siftdown(int pos) {
        assert (pos >= 0) && (pos < n) : "Illegal heap position";
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j<(n-1)) && (Heap[j].compareTo(Heap[j+1]) > 0))
                j++; // j is now index of child with lesser value
            if (Heap[pos].compareTo(Heap[j]) <= 0) return;
            DSutil.swap(Heap, pos, j);
            pos = j;  // Move down
        }
    }

    /**
     * Deletes the top item
     */
    public E removemin() {
        assert n > 0 : "Removing from empty heap";
        DSutil.swap(Heap, 0, --n); // Swap maximum with last value
        if (n != 0)      // Not on last element
            siftdown(0);   // Put new heap root val in correct place
        return Heap[n];
    }

    public E getRoot() {
        return Heap[0];
    }
}
