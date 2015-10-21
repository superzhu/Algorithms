package arrays.sub;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/maximum-difference-between-two-elements/
 *
 * Maximum difference between two elements such
 * that larger element appears after the smaller number
 */
public class MaxDiff {
    //allowed to buy and sell stock only once
    public static int maxDiff(int[] vals) {
        if(vals==null || vals.length<2)
            return 0;

        int min = vals[0];
        int maxDiff = vals[1] - min;

        for(int i = 2; i < vals.length; ++i) {
            if(vals[i - 1] < min)
                min = vals[i - 1];
            int currentDiff = vals[i] - min;
            if(currentDiff > maxDiff)
                maxDiff = currentDiff;
        }
        return maxDiff;
    }

    //allowed to buy and sell many times
    static int maxProfit(int a[],int len){
        int profit = 0;
        int start = 0;

        while(start<len){
            int end=start+1;
            //Find Local Maxima
            while(end<len && a[end]>a[end-1])
                ++end;
            end--;

            if(start!=end)
                profit += a[end]-a[start];
            start=end+1;
        }
        return profit;
    }

    //Buy and sell a stock twice
    public static double buyAndSellStockTwice(List<Double> prices) {
        double maxTotalProfit = 0.0;
        List<Double> firstBuySellProfits = new ArrayList<>();
        double minPriceSoFar = Double.MAX_VALUE;

        // Forward phase. For each day, we record maximum profit if we
        // sell on that day.
        for (int i = 0; i < prices.size(); ++i) {
            minPriceSoFar = Math.min(minPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, prices.get(i) - minPriceSoFar);
            firstBuySellProfits.add(maxTotalProfit);
        }

        // Backward phase. For each day, find the maximum profit if we make
        // the second buy on that day.
        double maxPriceSoFar = Double.MIN_VALUE;
        for (int i = prices.size() - 1; i > 0; --i) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, maxPriceSoFar - prices.get(i) +
                            firstBuySellProfits.get(i - 1));
        }
        return maxTotalProfit;
    }

    //Buy and sell a stock k times
    public static double maxKPairsProfits(List<Double> A, int k) {
        List<Double> kSum = new ArrayList<>();
        for (int i = 0; i < k * 2; ++i) {
            kSum.add(Double.NEGATIVE_INFINITY);
        }

        for (int i = 0; i < A.size(); ++i) {
            List<Double> preKSum = new ArrayList<>(kSum);
            for (int j = 0, sign = -1; j < kSum.size() && j <= i; ++j, sign *= -1) {
                double diff = sign * A.get(i) + (j == 0 ? 0 : preKSum.get(j - 1));
                kSum.set(j, Math.max(diff, preKSum.get(j)));
            }
        }

        // Returns the last selling profits as the answer.
        return kSum.get(kSum.size() - 1);
    }

    public static void main(String[] args) {
        int[] val1 = {13, 11, 5, 7, 16, 1, 4, 2};
        System.out.println("Max Stock profit=" + MaxDiff.maxDiff(val1));
        System.out.println("Max Stock profit=" + MaxDiff.maxProfit(val1,val1.length));

        int[] val2 = {100, 180, 260, 310, 40, 535, 695};
        System.out.println("Max Stock profit=" + MaxDiff.maxProfit(val2,val2.length));

        int[] val3 = {7,1,3,2,9,2,5,4,2,100};
        System.out.println("Max Stock profit of val3=" + MaxDiff.maxProfit(val3,val3.length));

        int[] val4 = {1,1,9,9};
        System.out.println("Max Stock profit of val4=" + MaxDiff.maxProfit(val4,val4.length));
    }
}
