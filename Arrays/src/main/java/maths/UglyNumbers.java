package maths;

/**
 * 我们把只包含因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包
 * 含因子7。习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第1500个丑数
 */
public class UglyNumbers {
    public static int GetUglyNumber_Solution2(int index) {
        if (index <= 0)
            return 0;

        int[] uglyNums = new int[index];
        uglyNums[0] = 1;
        int nextUglyIndex = 1;

        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        while (nextUglyIndex < index) {
            int min = Math.min(uglyNums[index2] * 2, uglyNums[index3] * 3);
            min = Math.min(min, uglyNums[index5] * 5);

            uglyNums[nextUglyIndex] = min;

            while (uglyNums[index2] * 2 <= uglyNums[nextUglyIndex])
                ++index2;
            while (uglyNums[index3] * 3 <= uglyNums[nextUglyIndex])
                ++index3;
            while (uglyNums[index5] * 5 <= uglyNums[nextUglyIndex])
                ++index5;

            ++nextUglyIndex;
        }

        int ugly = uglyNums[nextUglyIndex - 1];
        return ugly;
    }

    public static void main(String[] args) {
        UglyNumbers.GetUglyNumber_Solution2(100);
    }
}
