package search.dp;

/**
 * Given a list of 'N' coins, their values being in an array A[],
 * return the minimum number of coins required to sum to 'S'
 *
 *
 */
public class CoinChange {
    /**
     * d(i)=min{ d(i-vj)+1 }，其中i-vj >=0，vj表示第j个硬币的面值;
     */
    public static int minimumNumberDP(int[] values, int sum) {
        int[] table = new int[sum+1];

        for(int i = 0; i < table.length; ++i) {
            table[i] = Integer.MAX_VALUE;
        }

        table[0] = 0;

        for(int i = 1; i <= sum; ++i) {
            for(int j = 0; j < values.length; ++j) {
                if(values[j] <= i && (table[i - values[j]] + 1 < table[i])) {
                    table[i] = table[i - values[j]] + 1;
                }
            }
        }

        return table[sum];
    }

    public static void main(String[] args) {
        int[] values = {1, 3, 9, 10};

        System.out.println(minimumNumberDP(values,15));

        //Impossible to make changes
        int[] coins = { 2, 4, 8, 16 };
        System.out.println(minimumNumberDP(coins,63));
    }
}
