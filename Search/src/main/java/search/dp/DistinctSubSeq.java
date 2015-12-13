package search.dp;

/**
 * http://www.lifeincode.net/programming/leetcode-distinct-subsequences-java/
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 *
 * A subsequence of a string is a new string which is formed from the original string
 * by deleting some (can be none) of the characters without disturbing the relative
 * positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 *
 * Analysis
 *
 * This is a DP problem. We can use num[i][j] to save the number of distinct subsequences of T(0, j)
 * in S(0, i). We know that for any number i, num[i][0] = 1 because we need to delete all characters
 * from S(0, i) to a empty string.
 *
 * If the character at position i in S is equal to the character at position j in T, there are two options.
 *
 * Delete the character at position i in S. Then the number of distinct subsequences should be the number
 * of distinct subsequences of T(0, j) in S(0, i – 1).
 * Remains the character at position i in S. Then the number is the number of distinct subsequences of
 * T(0, j – 1) in S(0, i – 1). So num[i][j] = num[i – 1][j] + num[i – 1][j – 1].
 *
 * If the character at position i in S is not equal to the character at position j in T, then we can
 * only delete this character. So num[i][j] = num[i – 1][j].
 */
public class DistinctSubSeq {
    public int numDistinct(String S, String T) {
        int M = S.length();
        int N = T.length();
        int[][] num = new int[M + 1][N + 1];
        for (int i = 0; i <= N; i++)
            num[0][i] = 0;
        for (int i = 0; i <= M; i++)
            num[i][0] = 1;
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (S.charAt(i - 1) != T.charAt(j - 1)) {
                    num[i][j] = num[i - 1][j];
                } else {
                    num[i][j] = num[i - 1][j] + num[i - 1][j - 1];
                }
            }
        }
        return num[M][N];
    }
}
