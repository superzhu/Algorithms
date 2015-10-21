package heap;

/**
 * Given a stream of unsorted integers, find the median element in sorted order at any given Time.
 * So, we will be receiving a continuous stream of numbers in some random order and we don't know
 * the stream length in advance. Write a function that finds the median of the already received
 * numbers efficiently at any time.
 *   Just to recall: median is the middle element in an odd length sorted array, and in the even
 *     case it's the average of the middle elements
 * - See more at: http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/
 *
 * We also should make sure that the numbers in the max heap are less than the numbers in the min heap.
 * all numbers in the min heap should be greater than numbers in the max heap
 */
public class GetMedian {
    MaxHeap<Integer> leftMax;
    MinHeap<Integer> rightMin;

    public void GetMedian() {
        Integer[] left = new Integer[50];
        leftMax = new MaxHeap(left,0,50);
        Integer[] right = new Integer[50];
        rightMin = new MinHeap(right,0,50);
    }

    Integer getMe() {
        int size = rightMin.heapsize() + leftMax.heapsize();
        assert size == 0 : "No numbers are available";

        Integer median = 0;
        if((size & 1) == 1)
            median = (Integer)rightMin.getRoot();
        else
            median = ((Integer)leftMax.getRoot() + (Integer)rightMin.getRoot()) / 2;

        return median;
    }

    void insert(Integer num) {
        if(((rightMin.heapsize() + leftMax.heapsize()) & 1) == 0) {
            if(leftMax.heapsize() > 0 && num < (Integer)leftMax.getRoot()) {
                leftMax.insert(num);
                num = leftMax.getRoot();

                leftMax.removemax();
            }
            rightMin.insert(num);
        } else {
            //rightMin contains more element
            if(rightMin.heapsize() > 0 && num > (Integer)rightMin.getRoot()) {
                rightMin.insert(num);
                num = rightMin.getRoot();
                rightMin.removemin();
            }

            leftMax.insert(num);
        }
    }
}
