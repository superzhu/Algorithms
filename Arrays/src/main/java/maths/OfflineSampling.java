package maths;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * EPI: Problem 6.4 : Let A be an array of n distinct elements. Design an algorithm that
 * returns a subset of k elements of A. All subsets should be equally likely. Use as few
 * calls to the random number generator as possible and use O(1) additional storage
 * You can return the result in the same array as input.
 */
public class OfflineSampling {
    //Eventually, the random subset occupies the slots A[n-k : n-1]
    public static void randomSampling(int k, List<Integer> A) {
        Random gen = new Random();
        for (int i = 0; i < k; ++i) {
            // Generate a random int in [i, A.size() - 1].
            Collections.swap(A, i, i + gen.nextInt(A.size() - i));
        }
    }

    public static void main(String[] args) {
        String temp = "122222222222222222222222222266666666666666666666666000000009";

        System.out.println("String len=" + temp.length());
    }
}
