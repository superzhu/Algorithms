package search.binary;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
/**
 * EPI: Search in two sorted arrays
 * You are given two sorted arrays A and B of lengths m and n, respectively,
 * and a positive integer k belonging to [1;m + n]. Design an algorithm that runs in O(logk)
 * time for computing the k-th smallest element in array formed by merging A and B.
 * Array elements may be duplicated within and between A and B.
 */
public class FindKthElementInTwoSortedArrays {
    // @include
    public static int findKthNTwoSortedArrays(int[] A, int[] B, int k) {
        // Lower bound of elements we will choose in A.
        int lower = Math.max(0, k - B.length);
        // Upper bound of elements we will choose in A.
        int upper = Math.min(A.length, k);

        while (lower < upper) {
            int x = lower + ((upper - lower) / 2);

            int ax1 = (x <= 0 ? Integer.MIN_VALUE : A[x - 1]);
            int ax =  (x >= A.length ? Integer.MAX_VALUE : A[x]);

            int bkx1 = (k - x <= 0 ? Integer.MIN_VALUE : B[k - x - 1]);
            int bkx = (k - x >= B.length ? Integer.MAX_VALUE : B[k - x]);

            if (ax < bkx1) {
                lower = x + 1;
            } else if (ax1 > bkx) {
                upper = x - 1;
            } else {
                // B[k - x - 1] <= A[x] && A[x - 1] < B[k - x].
                return Math.max(ax1, bkx1);
            }
        }

        int ab1 = lower <= 0 ? Integer.MIN_VALUE : A[lower - 1];
        int bkb1 = k - lower - 1 < 0 ? Integer.MIN_VALUE : B[k - lower - 1];

        return Math.max(ab1, bkb1);
    }
    // @exclude

    private static int checkAnswer(int[] A, int[] B, int k) {
        int i = 0, j = 0, count = 0;
        int ret = -1;
        while ((i < A.length || j < B.length) && count < k) {
            if (i < A.length && j < B.length) {
                if (A[i] < B[j]) {
                    ret = A[i];
                    ++i;
                } else {
                    ret = B[j];
                    ++j;
                }
            } else if (i < A.length) {
                ret = A[i];
                ++i;
            } else {
                ret = B[j];
                ++j;
            }
            ++count;
        }
        return ret;
    }

    private static void smallTest() {
        // AA: handwritten test
        int[] a0 = new int[] {0, 1, 2, 3};
        int[] b0 = new int[] {0, 1, 2, 3};
        assert(0 == findKthNTwoSortedArrays(a0, b0, 1));
        assert(0 == findKthNTwoSortedArrays(a0, b0, 2));
        assert(1 == findKthNTwoSortedArrays(a0, b0, 3));
        assert(1 == findKthNTwoSortedArrays(a0, b0, 4));
        assert(2 == findKthNTwoSortedArrays(a0, b0, 5));
    }

    public static void main(String[] args) {
        smallTest();

       /* Random r = new Random();
        // Random test 10000 times.
        for (int times = 0; times < 10000; ++times) {
            int n, m, k;
            if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                m = Integer.parseInt(args[1]);
                k = r.nextInt(n + m) + 1;
            } else if (args.length == 3) {
                n = Integer.parseInt(args[0]);
                m = Integer.parseInt(args[1]);
                k = Integer.parseInt(args[2]);
            } else {
                n = r.nextInt(10000) + 1;
                m = r.nextInt(10000) + 1;
                k = r.nextInt(n + m) + 1;
            }
            int[] A = new int[n];
            int[] B = new int[m];
            for (int i = 0; i < n; ++i) {
                A[i] = r.nextInt(100000);
            }
            for (int i = 0; i < m; ++i) {
                B[i] = r.nextInt(100000);
            }
            Arrays.sort(A);
            Arrays.sort(B);
      *//*
       * System.out.println(A); System.out.println(B);
       *//*
            int ans = findKthNTwoSortedArrays(A, B, k);
            System.out.println(k + "th = " + ans);
            assert(ans == checkAnswer(A, B, k));
        }*/
    }
}
