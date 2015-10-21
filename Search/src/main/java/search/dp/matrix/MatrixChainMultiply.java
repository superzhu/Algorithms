package search.dp.matrix;

/**
 * https://sites.google.com/site/indy256/algo/matrix_multiply
 * Introduction to Algorithm 3rd,section 15.2
 */
public class MatrixChainMultiply {
    public static int solveIterative(int[] s) {
        int n = s.length - 1;
        int[][] p = new int[n][n];
        int[][] m = new int[n][n];
        for(int i = 0; i<n;i++) {
            m[i][i] = 0;
        }

        for (int len = 2; len <= n; len++) {
            for (int a = 0; a + len <= n; a++) {
                int b = a + len - 1;
                m[a][b] = Integer.MAX_VALUE;

                for (int c = a; c < b; c++) {
                    int v = m[a][c] + m[c + 1][b] + s[a] * s[c + 1] * s[b + 1];
                    if (m[a][b] > v) {
                        m[a][b] = v;
                        p[a][b] = c;
                    }
                }
            }
        }
        return m[0][n - 1];
    }

    public static void main(String[] args) {
        int[] s = {30,35,15,5,10,20,25};
        MatrixChainMultiply chain = new MatrixChainMultiply();

        System.out.println(chain.solveIterative(s));
    }
}
