package search.dp.matrix;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
 * Given a 2D array, find the maximum sum subarray in it
 */
public class MaxSubRegion {
    public static class Result {
        public int start;
        public int end;
        public int result;
    };

    public static class Region {
        public int finalLeft;
        public int finalTop;
        public int finalBottom;
        public int finalRight;
        public int maxSum;
    };

    // Implementation of Kadane's algorithm for 1D array.
    public static Result getGreatestSumOfSubArray(int[] numbers) {
        int curSum = 0;
        int greatestSum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        Result re = new Result();
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
                re.start = start;
                re.end = end;
            }
        }

        System.out.println("Start="+re.start+",end="+re.end);
        re.result = greatestSum;

        return re;
    }

    public static Region findMaxSum(int[][] numbers) {
        if(numbers == null)
            return null;

        int ROWS = numbers.length;
        int COLS = numbers[0].length;

        Region region = new Region();
        int maxSum = Integer.MIN_VALUE;
        int left, right, i;
        int sum, start, finish;
        int[] temp = new int[ROWS];

        // Set the left column
        for (left = 0; left < COLS; ++left) {
            // Initialize all elements of temp as 0
            for(i=0;i<ROWS;i++)
                temp[i] = 0;

            // Set the right column for the left column set by outer loop
            for (right = left; right < COLS; ++right) {
                // Calculate sum between current left and right for every row 'i'
                for (i = 0; i < ROWS; ++i)
                    temp[i] += numbers[i][right];

                Result result = MaxSubRegion.getGreatestSumOfSubArray(temp);

                // Compare sum with maximum sum so far. If sum is more, then update
                // maxSum and other output values
                if (result.result > maxSum)
                {
                    maxSum = result.result;
                    region.finalLeft = left;
                    region.finalRight = right;
                    region.finalTop = result.start;
                    region.finalBottom = result.end;
                }
            }
        }

        region.maxSum = maxSum;
        return region;
    }

    public static void main(String[] argv) {
        int[] numbers = {1, -2, 3, 10, -4, 7, 2, -5};

        Result result = MaxSubRegion.getGreatestSumOfSubArray(numbers);
        System.out.println("Max Sub Sum="+ result.result+", start index="+ result.start
                +",end index="+result.end);

        int M[][] = {{1, 2, -1, -4, -20},
                     {-8, -3, 4, 2, 1},
                     {3, 8, 10, 1, 3},
                     {-4, -1, 1, 7, -6}
        };

        Region re = findMaxSum(M);
        System.out.println("Max sum = " + re.maxSum);
    }
}

