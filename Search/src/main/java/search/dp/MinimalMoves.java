package search.dp;

/**
 * You are required to sort n cards numbered from 1 to n. You can choose one card and move it to
 * any place you want (insert to any place, not swap). Given a sequence, please implement a function to return the
 * minimum move count to sort these cards.
 *
 *   For example, given a sequence {1, 2, 5, 3, 4, 7, 6}, you can move 5 and insert it between 4 and 7, and the
 * sequence becomes {1, 2, 3, 4, 5, 7, 6}. This is one move. If 7 is moved behind 6, the whole sequence gets sorted.
 * Therefore, it needs two steps at least to sort cards in the sequence of {1, 2, 5, 3, 4, 7, 6}.
 *
 *   In order to sort a sequence with minimum number of moves, we first find the longest increasing
 * subsequence, and then it is only necessary to insert numbers out of the longest increasing subsequence
 * into the appropriate places
 */
public class MinimalMoves {
    //Based on Binary Search Costing O(nlogn) Time
    private static int longestIncreasingLength_solution2(int[] seq){
        if(seq == null)
            return 0;

        int len = seq.length;
        int[] lookup = new int[len];

        lookup[0] = seq[0];
        int longestLength = 1;
        for(int i = 1; i < len; ++i) {
            if(seq[i] > lookup[longestLength - 1]) {
                longestLength++;
                lookup[longestLength - 1] = seq[i];
            } else {
                int low = 0;
                int high = longestLength - 1;
                while(low != high) {
                    int mid = (low + high) / 2;
                    if(lookup[mid] < seq[i]) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }

                lookup[low] = seq[i];
            }
        }

        return longestLength;
    }

    public static int minMoveCount_solution2(int[] seq) {
        return seq.length - longestIncreasingLength_solution2(seq);
    }
}
