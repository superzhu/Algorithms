package util;

/**
 * http://fushan.blog.51cto.com/9723472/1673081
 */
public class Equi3 {
    public int solution(int[] A) {
        int sum = 0, leftSum = 0, midSum = 0, rightSum = 0;
        int n = A.length;
        int[] totals = new int[n];
        for (int i=0; i<n; i++) {
            sum += A[i];
            totals[i] = sum;
        }

        int left = 1, right = n - 2;
        while (left+1 < right) {
            leftSum = totals[left-1];
            midSum = totals[right-1] - totals[left];
            rightSum = totals[n-1] - totals[right];
            if ((leftSum == midSum) && (midSum == rightSum)) {
                return 1;
            } else if (leftSum > rightSum) {
                right--;
            } else if (leftSum < rightSum){
                left++;
            } else {
                left++;
                right--;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] in = {4,5,1,1,1,1,4,3,1};

        Equi3 instance = new Equi3();
        System.out.println(instance.solution(in));
    }
}
