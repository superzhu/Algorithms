package basic.arrays;

/**
 * Given an array where every element occurs three times, except one element which occurs only once. Find the
 * element that occurs once. Expected time complexity is O(n) and O(1) extra space.
 *
 * Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3}
 * Output: 2
 */
public class GetSingle {
    private final int INT_SIZE = 32;

    /**
     * sum the bits in same positions for all the numbers and take modulo with 3.
     * The bits for which sum is not multiple of 3, are the bits of number with single occurrence.
     */
    int getSingle(int arr[], int n) {
        // Initialize result
        int result = 0;

        int x, sum;

        // Iterate through every bit
        for (int i = 0; i < INT_SIZE; i++) {
            // Find sum of set bits at ith position in all
            // array elements
            sum = 0;
            x = (1 << i);
            for (int j=0; j< n; j++ ) {
                if ((arr[j] & x) != 0)
                    sum++;
            }

            // The bits with sum not multiple of 3, are the
            // bits of element with single occurrence.
            if ((sum % 3) != 0)
                result |= x;
        }

        return result;
    }

    public static void main(String args[]) {
        int[] arr = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3};
        GetSingle instance = new GetSingle();
        System.out.println(instance.getSingle(arr,arr.length));
    }
}
