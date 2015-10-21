package arrays.sub;

/**
 * Given an integer array containing positive and negative numbers, how do you get the maximum
 * sum of its sub-arrays? Continuous numbers form a sub-array of an array.
 * For example, if the input array is {1, -2, 3, 10, -4, 7, 2, -5},
 * the sub-array with the maximum sum is {3, 10, -4, 7,2} whose sum 18.
 */
public class GreatestSumOfSubarrays {
    // Implementation of Kadane's algorithm for 1D array.
    public static int getGreatestSumOfSubArray(int[] numbers) {
        int curSum = 0;
        int greatestSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        int maxStart = 0;
        int maxEnd = 0;

        for(int i = 0; i < numbers.length; ++i) {
            if(curSum <= 0) {
                curSum = numbers[i];
                start = i;
                end = i;
            } else {
                curSum += numbers[i];
                end = i;
            }

            if(curSum > greatestSum) {
                greatestSum = curSum;
                maxStart = start;
                maxEnd = end;
            }
        }

        System.out.println("Start="+maxStart+",end="+maxEnd);

        return greatestSum;
    }

    //================= Test Code =================

    private static void test(String testName, int[] numbers, int expected) {
        System.out.print(testName + " begins: ");

        if(getGreatestSumOfSubArray(numbers) == expected)
            System.out.print("passed.\n");
        else
            System.out.print("FAILED.\n");
    }

    private static void test1() {
        int[] numbers = {1, -2, 3, 10, -4, 7, 2, -5};
        int expected = 18;
        test("Test1", numbers, expected);
    }

    // all numbers are negative
    private static void test2() {
        int[] numbers = {-2, -8, -1, -5, -9};
        int expected = -1;
        test("Test2", numbers, expected);
    }

    // all numbers are positive
    private static void test3() {
        int[] numbers = {2, 8, 1, 5, 9};
        int expected = 25;
        test("Test3", numbers, expected);
    }

    // only one number
    private static void test4() {
        int[] numbers = {2};
        int expected = 2;
        test("Test4", numbers, expected);
    }

    public static void main(String[] argv) {
        test1();
        test2();
        test3();
        test4();
    }
}
