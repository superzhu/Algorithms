package basic.arrays.lesson2;

import java.util.BitSet;

/**
 * https://github.com/acprimer/Codility/blob/master/src/Lesson2/PermCheck.java
 */
public class PermCheck {
    public int solution(int[] A) {
        int n = A.length;
        BitSet bit = new BitSet(n);
        for (int i = 0; i < A.length; i++) {
            if (A[i] < 1 || A[i] > n || bit.get(A[i] - 1)) return 0;
            bit.set(A[i] - 1);
        }
        return 1;
    }
}
