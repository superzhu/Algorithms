package maths;

import java.util.Arrays;

/**
 * http://yuanhsh.iteye.com/blog/2215696
 *
 * Google:
 * Compute the h-index of a list of papers, given their citation count. Can you do it in linear time?
 * How about a distributed algorithm for the task?
 *
 *Facebook:
 * Given: for every paper authored, there is a citation count vector. The h-index is a measure of researcher importance.
 * h-index: The largest number i such that there are i papers each with at least i citations.
 * 1. Suppose that the citation-vector is sorted, how to efficiently compute the h-index?
 * 2. Suppose that the citation-vector is not sorted, how to efficiently compute the h-index? time complexity? an algorithm with time complexity n?
 *
 *Princeton algorithm:
 *Given an array of N positive integers, its h-index is the largest integer h
 * such that there are at least h entries in the array greater than or equal to h.
 * Design an algorithm to compute the h-index of an array.
 *Hint: median or quicksort-like partitioning and divide-and-conquer.
 */
public class Hindex {
    // assume sorted in descending order, O(lgN)
    public static int getHIndexFromSorted(int[] citation) {
        int low = 0; int high = citation.length - 1;
        while(low <= high) {
            int idx = (low+high)/2;
            if(citation[idx] >= idx + 1) {
                low = idx + 1;
            } else {
                high = idx - 1;
            }
        }
        return low;
    }

    // sort the array, O(NlgN)
    public static int computeHIndexBySorting(int[] A) {
        Arrays.sort(A);
        int h = 0;
        for (int i = A.length-1; i >= 0; i--) {
            if(A[i] > h) {
                h++;
            } else {
                return h;
            }
        }
        return -1;
    }

    // no need to sort array, O(N)
    public static int computeHIndex(int[] A) {
        int n = A.length;
        int[] s = new int[n+1];
        for(int num : A) {
            num = Math.min(n ,num);
            s[num]++;
        }
        int sum = 0;
        for (int i = s.length-1; i >= 0; i--) {
            sum += s[i];
            if(sum >= i) {
                return i;
            }
        }
        return -1;
    }
}
