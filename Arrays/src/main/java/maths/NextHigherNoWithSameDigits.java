package maths;

import java.util.Arrays;

/**
 *  Given a number, write an algorithm to find the next higher number with the same digits.
 *  For example : If the input is 45156, then the next higher number with the same digit is 45165.
 *
 *  http://www.geeksforgeeks.org/find-next-greater-number-set-digits/
 */
public class NextHigherNoWithSameDigits {
    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void findNext(int nums[]) {
        if(nums == null)
            return;

        int len = nums.length;
        int i,j;

        //I) Start from the right most digit and find the first digit that is
        //  smaller than the digit next to it.
        for (i = len-1; i > 0; i--)
            if (nums[i] > nums[i-1])
                break;

        if(i ==0) {
            //1) If all digits sorted in descending order, then output is always “Not Possible”. For example, 4321.
            System.out.println("Next number is not possible");
            return;
        }

        //II) Find the smallest digit on right side of (i-1)'th digit that is
        //greater than number[i-1]
        int x = nums[i-1], smallest = i;
        for (j = i+1; j < len; j++)
            if (nums[j] > x && nums[j] < nums[smallest])
                smallest = j;

        // III) Swap the above found smallest digit with number[i-1]
        swap(nums,i-1,smallest);

        // IV) Sort the digits after (i-1) in ascending order
        Arrays.sort(nums, i, len);

        System.out.println("Next number with same set of digits is " );
        for(int kk=0;kk<len;kk++) {
            System.out.print(nums[kk]);
        }

        return;
    }

    public static void main(String[] args) {
        int[] nums= {5,3,4,9,7,6};//expected: 536479
        NextHigherNoWithSameDigits.findNext(nums);

        int[] num1= {4,3,2,1};//expected: impossible
        NextHigherNoWithSameDigits.findNext(num1);

        int[] num2= {1,2,3,4};//expected: 1243
        NextHigherNoWithSameDigits.findNext(num2);

        int[] num3= {4,5,1,5,6};//expected: 45165
        NextHigherNoWithSameDigits.findNext(num3);
    }
}
