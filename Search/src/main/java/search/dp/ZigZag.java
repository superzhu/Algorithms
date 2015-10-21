package search.dp;

/**
 *A sequence of numbers is called a zig-zag sequence if the differences between successive numbers
 * strictly alternate between positive and negative. The first difference (if one exists) may be
 * either positive or negative. A sequence with fewer than two elements is trivially a zig-zag sequence.
 *
 *  For example, 1,7,4,9,2,5 is a zig-zag sequence because the differences (6,-3,5,-7,3) are alternately
 *  positive and negative. In contrast, 1,4,7,2,5 and 1,7,4,5,5 are not zig-zag sequences, the first
 *  because its first two differences are positive and the second because its last difference is zero.
 *
 *  Given a sequence of integers, sequence, return the length of the longest subsequence of sequence
 *  that is a zig-zag sequence. A subsequence is obtained by deleting some number of elements
 *  (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 *
 *  traceback: 回朔;错误消息与追溯；可回溯
 */
public class ZigZag {
    //http://www.krishnabharadwaj.info/dynamic-programming/
    public static int zigzag(int[] values) {
        int size = values.length;

        int[] signs = new int[size];
        int[] dp = new int[size];
        for(int i=0; i< size; i++) {
            signs[i] = 0;
            dp[i] = 1;
        }

        // we have to find the longest zig zag sequence till i
        for(int i=1;i<size;i++){
            /**
             * For each i, we should figure out the previous element to jump from.
             * it can be the one right before i or it could be element at index 0.
             * so we have to go all the way from i-1 down to 0.
             */
            for(int j = i-1; j>=0; j--){
                /**
                 * What's most important is.. we cannot jump from any element..
                 *  we are looking for an element which when subtracted from this
                 *  has the desired sign. variable sign indicates whether the previous
                 *  was a positive jump or a negative jump.
                 */
                int sign = signs[j];

                if( (sign == 0) || ( (sign < 0) && (values[i] - values[j] > 0) ) ||
                        ( (sign > 0) && (values[i] - values[j] < 0) ) ){
                    /**
                     * The different cases.
                     * 1. sign == 0 : We are just beginning.. we can pick either
                     *     positive difference or negative difference.
                     *
                     * 2. (sign < 0) && (a[i] - a[j] > 0) : Previous value resulted in a negative
                     *     difference, so we need to pick a positive difference value
                     *
                     * 3. (sign > 0) && (a[i] - a[j] < 0) : Previous value resulted in a positive
                     *    difference, pick negative difference this time.
                     *
                     * Once we have found an element, store the difference in signs, so that it
                     * helps in subsequent iterations.
                     */
                    dp[i] = dp[j] + 1;

                    signs[i] = values[i] - values[j];
                    break;
                }
            }
        }

        return dp[size-1];
    }//zigzag


    public static void main(String[] args) {
        //http://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493
        //int[] values = { 1, 7, 4, 9, 2, 5};//expected: 6

        int[] values = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};//expected: 7

        //int[] values = {66};//expected: 1

        //int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };//expected: 2

        //int[] values = {70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };//expected: 8

       /* int[] values = { 374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
                600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
                67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
                477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
                249, 22, 176, 279, 23, 22, 617, 462, 459, 244};*///expected: 36

        System.out.println(zigzag(values));
    }
}
