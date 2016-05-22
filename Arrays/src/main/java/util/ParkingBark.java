package util;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Maximum gap issue
 *
 */
public class ParkingBark {
    public int solution(int[] A) {
        // write your code in Java SE 8
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int i=0;i<A.length;i++)
            minHeap.add(A[i]);

        int ans = Integer.MIN_VALUE;
        int previous = minHeap.remove();

        while(!minHeap.isEmpty()) {
            int current = minHeap.remove();
            ans = Math.max(ans,current - previous);

            previous = current;
        }

        return ans/2;
    }

    public int solution2(int[] A) {
        Arrays.sort(A);
        int ans = Integer.MIN_VALUE;
        if (A.length == 2) return (A[1] - A[0]) / 2;
        for (int i=0; i < A.length - 1; i++) {
            //如果两点之间有空位置
            if (A[i+1] - A[i] > 1) {
                ans = Math.max(ans, A[i+1] - A[i]);
            }
        }

        return ans / 2;
    }

    public static void main(String[] args) {
        //int[] in = {10,0,8,2,-1,12,11,3};
        int[] in = {5};

        ParkingBark instance = new ParkingBark();
        System.out.println(instance.solution(in));
        System.out.println(instance.solution2(in));
    }
}
