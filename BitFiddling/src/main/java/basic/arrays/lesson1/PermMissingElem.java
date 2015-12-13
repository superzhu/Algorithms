package basic.arrays.lesson1;

/**
 * https://github.com/acprimer/Codility/blob/master/src/Lesson1/PermMissingElem.java
 */
public class PermMissingElem {
    public int solution(int[] A) {
        int n = A.length;

        int remain = 0;
        for(int i=0; i<n;i++)
            remain = remain ^ A[i] ^ (i+1);

        remain  = remain ^ (n+1);

        return remain;
    }

    public static void main(String[] args) {
        int ans = new PermMissingElem().solution(new int[]{5, 2, 3, 1});
        System.out.println(ans);
    }
}
