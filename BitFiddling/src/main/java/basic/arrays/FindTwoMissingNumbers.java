package basic.arrays;

/**
 * Given an array of size n. It contains numbers in the range 1 to n.
 * Each number is present at least once except for 2 numbers. Find the missing numbers.
 *数组有N-2个数字，数字的范围为1 ... N，没有重复的元素，要求打印缺少的2个数字, 空间复杂度O（1）。
 */
public class FindTwoMissingNumbers {
    public class NumbersOccurringOnce {
        public int num1;
        public int num2;
    }

    public static void findMissing_solution2(int numbers[], NumbersOccurringOnce missing){
        int originalLength = numbers.length;
        int extendedLength = originalLength * 2 + 2;
        int extention[] = new int[extendedLength];

        for(int i = 0; i < originalLength; ++i)
            extention[i] = numbers[i];
        for(int i = originalLength; i < extendedLength; ++i)
            extention[i] = i - originalLength + 1;

        getOnce(extention, missing);
    }

    private static void getOnce(int numbers[], NumbersOccurringOnce once){
        if (numbers.length < 2)
            return;

        int resultExclusiveOR = 0;
        for (int i = 0; i < numbers.length; ++ i)
            resultExclusiveOR ^= numbers[i];

        int indexOf1 = NumbersOccuringOnce.findFirstBitIs1(resultExclusiveOR);

        once.num1 = once.num2 = 0;
        for (int j = 0; j < numbers.length; ++ j) {
            if(NumbersOccuringOnce.isBit1(numbers[j], indexOf1))
                once.num1 ^= numbers[j];
            else
                once.num2 ^= numbers[j];
        }
    }
}
