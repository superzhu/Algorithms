package search.dp.ibm;

import java.util.ArrayList;
import java.util.List;

/**
 * http://flexaired.blogspot.jp/2013/03/bad-neighbours.html
 * http://www.krishnabharadwaj.info/dynamic-programming/
 * Question: find the max subsequence sum but the elements
 *      can not be close to each other, the first and the
 *      end are not allowed either.
 * Solution: This is another classic example of using dynamic programming.
 */
public class BadNeighbors {
    public int maxDonations(int[] donations) {
        List<Integer> l1 = new ArrayList<Integer>();
        List<Integer> l2 = new ArrayList<Integer>();
        int n = donations.length;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                l1.add(donations[i]);
            } else if (i== n-1) {
                l2.add(donations[i]);
            } else {
                l1.add(donations[i]);
                l2.add(donations[i]);
            }
        }

        return Math.max(findMax(l1), findMax(l2));
    }

    private int findMax(List<Integer> l1) {
        if (l1.size() == 1)
            return l1.get(0);
        if (l1.size() == 2)
            return Math.max(l1.get(0), l1.get(1));
        if (l1.size() == 3)
            return Math.max(l1.get(0) + l1.get(2), l1.get(1));

        int[] dp = new int[l1.size()];

        dp[0] = l1.get(0);
        dp[1] = Math.max(l1.get(0), l1.get(1));
        dp[2] = Math.max(l1.get(0) + l1.get(2), l1.get(1));

        for (int i=3; i<l1.size(); i++) {
            dp[i] = Math.max(l1.get(i)+dp[i-2], l1.get(i-1)+dp[i-3]);
        }

        return dp[l1.size()-1];
    }

    public static void main(String[] args) {
        int[] s = {7, 7, 7, 7, 7, 6, 7};
        BadNeighbors chain = new BadNeighbors();

        System.out.println(chain.maxDonations(s));

        String property = "java.io.tmpdir";
        String tempDir = System.getProperty(property);
        System.out.println("system temp folder:" + tempDir);
    }
}
