package search.dp.arrays;

/**
 * 最大连续乘积子串
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *   For example, given the array [2,3,-2,4],
 *   the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaxProductSubArray {
    public double maxProduct(double[] A) {
        double curMax = A[0];
        double curMin = A[0];
        double max = A[0];
        double temp;

        int start = 0;
        int end  = 0;

        for(int i = 1; i < A.length; i++) {
            // 注意这里先存起来，下面要在更新之后再用一次
            temp = curMax * A[i];
            curMax = Math.max(A[i], Math.max(temp, curMin * A[i]));
            curMin = Math.min(A[i], Math.min(temp, curMin * A[i]));

            //max = Math.max(curMax, max);
            if(curMax > max) {
                max = curMax;
                end = i;
            }
        }

        double mmm = max;
        start = end;

        if(Math.abs((A[start] - 0.0)) > 0.00001) {
            while ( (Math.abs((mmm - 1.0)) > 0.00001) && (Math.abs((A[start] - 0.0)) > 0.00001) ) {
                mmm = mmm / A[start];
                start--;
            }
            start++;
        }

        System.out.println("Max sub array product start index="+start+", end index="+end);
        return max;
    }

    public static void main(String[] args) {
        double[] val1 = {2,3,-2,4};//Max Product is 6.0
        double[] val2 = {-2.5,4,0,3,0.5,8,-1};//Max Product is 12.0
        double[] val3 = {6, -3, -10, 0, 2};//Max Product is 180.0
        double[] val4 = {-1, -3, -10, 0, 60};//Max Product is 60.0
        double[] val5 = {-2, -3, 0, -2, -40};//Max Product is 80.0
        double[] val6 = {-3, 0, -2};//Max Product is 0.0
        double[] val7 = {-3};//Max Product is -2.0
        MaxProductSubArray product = new MaxProductSubArray();

        System.out.println(product.maxProduct(val1));
        System.out.println(product.maxProduct(val2));
        System.out.println(product.maxProduct(val3));
        System.out.println(product.maxProduct(val4));
        System.out.println(product.maxProduct(val5));
        System.out.println(product.maxProduct(val6));
        System.out.println(product.maxProduct(val7));
    }
}
