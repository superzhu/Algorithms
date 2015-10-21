package search.dp;

/**
 * 最长递增子序列（Longest Increasing Subsequence）是指找到一个给定序列的最长子序列的长度，
 * 使得子序列中的所有元素单调递增。例如：{ 3，5，7，1，2，8 } 的 LIS 是 { 3，5，7，8 }，长度为 4。
 *
 * L(i) = { 1 + Max ( L(j) ) } where j < i and arr[j] < arr[i]
 *   and if there is no such j then L(i) = 1
 */
public class LIS {
    /**
     * http://blog.csdn.net/lisonglisonglisong/article/details/45241965
     * Time Complexity: O(n^2)
     */
    public static int getLISLength(int[] arr) {
        if(arr == null)
            return 0;

        int size = arr.length;

        int[] lis = new int[size];
        /* 初始化为1 */
        for (int i = 0; i < size; ++i)
            lis[i] = 1;


        /* 计算每个i对应的lis最大值，即打表的过程 */
        for (int i = 1; i < size; ++i)
            for (int j = 0; j < i; ++j)     // 0到i-1
                if ( arr[i] > arr[j] && lis[i] < lis[j]+1)
                    lis[i] = lis[j] + 1;  // 更新

        /* 数组中最大的那个，就是最长递增子序列的长度 */
        int maxlis = 0;
        for (int i = 0; i < size; ++i)
            if ( maxlis < lis[i] )
                maxlis = lis[i];

        return maxlis;
    }

    public static void main(String[] args) {
        int[] arr = {1,7,2,8,3,4,9};

        System.out.println("Longest Increasing Subsequence: " + getLISLength(arr));
    }
}
