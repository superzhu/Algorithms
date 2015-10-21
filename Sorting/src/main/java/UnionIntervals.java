/**
 * Compute the union of intervals
 *  Write a function which takes as input an array A of disjoint closed
 *  intervals with integer endpoints, sorted by increasing order of left endpoint, and an interval
 *  I, and returns the union of I with the intervals in A, expressed as a union of disjoint intervals.
 *
 *  Solution 14.3: Let I = [x; y]. There are two possibilities—A has an interval that has
 *  a nonempty intersection with I, or it does not. If it does not contain an interval
 *  intersecting I, we simply add I in the appropriate place.
 *
 *    If A does contain an interval with a nonempty intersection with I, we iterate
 *  through A until we encounter the first such interval, call it I0. As a general fact, given
 *  any two intervals [a; b] and [a0; b0] that intersect, their union is [min(a; a0); max(b; b0)].
 *  If I0 [ I = I0, there there is nothing to do, we can return A. Otherwise, we compute
 *  w = I0 [ I. Now we keep testing subsequent intervals for intersection with w. If
 *  an interval J overlaps with w we update w to the union of J with w. As soon as an
 *  interval is disjoint from w, we add w and the remaining intervals to the result and return.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UnionIntervals {
    // @include
    public static List<Interval> unionOfIntervals(Interval[] intervals) {
        // Empty input.
        if (intervals.length == 0) {
            return new ArrayList<>();
        }

        // Sort intervals according to left endpoints of intervals.
        Arrays.sort(intervals);
        Interval curr = new Interval();
        curr = intervals[0];
        List<Interval> result = new ArrayList<>();

        for (int i = 1; i < intervals.length; ++i) {
            if (intervals[i].left.val < curr.right.val || (intervals[i].left.val == curr.right.val &&
                            (intervals[i].left.isClosed || curr.right.isClosed) ) ) {

                if (intervals[i].right.val > curr.right.val || (intervals[i].right.val == curr.right.val &&
                                intervals[i].right.isClosed) ) {
                    curr.right = intervals[i].right;
                }

            } else {
                result.add(curr);
                curr = intervals[i];
            }
        }

        result.add(curr);
        return result;
    }
    // @exclude

    private static void checkIntervals(List<Interval> A) {
        // only check the Intervals do not overlap with each other.
        for (int i = 1; i < A.size(); ++i) {
            assert(A.get(i - 1).right.val < A.get(i).left.val ||
                    (A.get(i - 1).right.val == A.get(i).left.val &&
                            !A.get(i - 1).right.isClosed && !A.get(i).left.isClosed));
        }
    }

    public static void main(String[] args) {
        Random gen = new Random();
        for (int times = 0; times < 1000; ++times) {
            int n;
            if (args.length == 1) {
                n = Integer.parseInt(args[0]);
            } else {
                n = gen.nextInt(1000) + 1;
            }
            Interval[] A = new Interval[n];
            for (int i = 0; i < n; ++i) {
                Interval temp = new Interval();
                temp.left.isClosed = gen.nextBoolean();
                temp.left.val = gen.nextInt(9999);
                temp.right.isClosed = gen.nextBoolean();
                temp.right.val = gen.nextInt(temp.left.val + 100) + temp.left.val + 1;
                A[i] = temp;
            }
            List<Interval> ret = unionOfIntervals(A);
            if (!ret.isEmpty()) {
                checkIntervals(ret);
            }
        }
    }

   static class Interval implements Comparable<Interval> {
        class Endpoint {
            public boolean isClosed;
            public int val;
        }

        public int compareTo(Interval i) {
            if (Integer.compare(left.val, i.left.val) != 0) {
                return left.val - i.left.val;
            }
            // Left endpoints are equal, so now see if one is closed and the
            // other open - closed intervals should appear first.
            if (left.isClosed && !i.left.isClosed) {
                return -1;
            }
            if (!left.isClosed && i.left.isClosed) {
                return 1;
            }
            return 0;
        }

        public Endpoint left = new Endpoint();
        public Endpoint right = new Endpoint();
    }
}
