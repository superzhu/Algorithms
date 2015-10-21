package search.binary;

/**
 * http://algs4.cs.princeton.edu/11model/BinarySearch.java.html
 */
public class BinarySearch {
    private BinarySearch() {
    }

    /**
     * Searches for the integer key in the sorted array a[].
     *
     * @param key the search key
     * @param a   the array of integers, must be sorted in ascending order
     * @return index of key in array a[] if present; -1 if not present
     */
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * When some elements at the beginning of an array are moved to the end, it becomes a rotation
     * of the original array. Please implement a function to get the minimum number in a rotation of an increasingly
     * sorted array. For example, the array {3, 4, 5, 1, 2} is a rotation of array {1, 2, 3, 4, 5},
     * of which the minimum is 1.
     */
    public static int rotatedArraySearch(int numbers[]) {
        int low = 0;
        int high = numbers.length - 1;
        int middle = low;

        while (numbers[low] >= numbers[high]) {
            if (high - low == 1) {
                middle = high;
                break;
            }

            middle = (low + high) / 2;

            // if numbers with indexes index1, index2, indexMid
            // are equal, search sequentially
            if (numbers[low] == numbers[high]
                    && numbers[middle] == numbers[low])
                return getMinSequentially(numbers, low, high);

            if (numbers[middle] >= numbers[low])
                low = middle;
            else if (numbers[middle] <= numbers[high])
                high = middle;

        }

        return numbers[middle];
    }

    private static int getMinSequentially(int numbers[], int index1, int index2) {
        int result = numbers[index1];
        for(int i = index1 + 1; i <= index2; ++i) {
            if(result > numbers[i])
                result = numbers[i];
        }

        return result;
    }

    /**
     * A turning number is the maximum number in a unimodal array that increases and then
     * decreases. Please write a function (or a method) that finds the index of the turning number in a unimodal array.
     * For example, the turning number in the array {1, 2, 3, 4, 5, 10, 9, 8, 7, 6} is 10, so its index 5 is
     * the expected output
     */
    public static int getTurningIndex(int numbers[]) {
        if(numbers.length <= 2)
            return -1;

        int left = 0;
        int right = numbers.length - 1;
        while(right > left + 1) {
            int middle = (left + right) / 2;
            if(middle == 0 || middle == numbers.length - 1)
                return -1;

            if(numbers[middle] > numbers[middle - 1]
                    && numbers[middle] > numbers[middle + 1])
                return middle;
            else if(numbers[middle] > numbers[middle - 1]
                    && numbers[middle] < numbers[middle + 1])
                left = middle;
            else
                right = middle;
        }

        return -1;
    }

    private static boolean checkMajorityExistence(int[] numbers, int number){
        int times = 0;
        for(int i = 0; i < numbers.length; ++i){
            if(numbers[i] == number)
                times++;
        }

        return (times * 2 > numbers.length);
    }

    /**
     * How do you find the majority element in an array when it exists? The majority is an element
     * that occurs for more than half of the size of the array.
     * For example, the number 2 in the array {1, 2, 3, 2, 2, 2, 5, 4, 2} is the majority element because
     * it appears five times and the size of the array is 9.
     */
    public static int getMajority_2(int[] numbers){
        int result = numbers[0];
        int times = 1;
        for(int i = 1; i < numbers.length; ++i){
            if(times == 0){
                result = numbers[i];
                times = 1;
            } else if(numbers[i] == result)
                times++;
            else
                times--;
        }

        if(!checkMajorityExistence(numbers, result))
            throw new IllegalArgumentException("No majority exisits.");

        return result;
    }

}
