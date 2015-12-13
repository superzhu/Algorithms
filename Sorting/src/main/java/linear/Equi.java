package linear;

/**
 * Created by zhzhzhu on 2015/11/4.
 */
public class Equi {
    public int solution(int[] A) {
        // write your code in Java SE 8
        /*if(A == null){
            throw new NullPointerException("Input array is null");
        }*/

       /* if(A.length == 0) {
            throw new IllegalArgumentException("Length of Input array is zero");
        }*/

        long rightSum = 0;
        long leftSum = 0;
        for(int i =0;i<A.length;i++) {
            rightSum += A[i];
        }

        for(int i=0;i<A.length;i++) {
            if(leftSum == rightSum - A[i]) {
                return i;
            }

            leftSum += A[i];
            rightSum -= A[i];
        }

        return -1;
    }

    public static void main(String[] args) {
        //int[] input = {-1,3,-4,5,1,-6,2,1};

       // int[] input = {-1,5,0,4};

        int[] input = {};

        Equi instance = new Equi();
        System.out.println(instance.solution(null));
    }
}
