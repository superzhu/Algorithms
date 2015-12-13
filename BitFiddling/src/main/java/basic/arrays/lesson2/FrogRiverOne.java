package basic.arrays.lesson2;

import java.util.BitSet;

/**
 * Created by zhzhzhu on 2015/11/4.
 */
public class FrogRiverOne {
    public int solution(int X, int[] A) {
        int n = A.length;
        BitSet hash = new BitSet(X + 1);
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] >= 1 && A[i] <= X && !hash.get(A[i])) {
                hash.set(A[i]);
                count++;
                if (count >= X) return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int ans = new FrogRiverOne().solution(5, new int[]{1, 2, 1, 4, 3, 3, 5, 4});
        System.out.println(ans);
    }
}
