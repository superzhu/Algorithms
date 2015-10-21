package search.dp;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-34-assembly-line-scheduling/
 * http://www.cnblogs.com/mengwang024/p/4295389.html
 *
 * http://mytestpjt.googlecode.com/svn/trunk/ASAssembly/src/com/mhhe/clrs2e/  --代码不少
 * https://sites.google.com/site/indy256/algo/lcs
 *
 * Implementation of assembly-line scheduling for two lines, as
 * described in Section 15.1 of <i>Introduction to Algorithms</i>,
 * Second edition.  Because this is Java code, numbering starts from
 * 0, so that the lines are 0 and 1 and station numbers are 0, 1, ...,
 * <i>n</i>-1.
 */
public class AssemblyLine {
    /** The number of stations on each line. */
    private int n;

    /** The value of an optimal solution to a subproblem.
     * <code>f[i][j]</code> is the fastest possible time to get from
     * the starting point through station
     * <i>S</i><sub><i>i</i>,<i>j</i></sub>, for <code>i</code> = 0, 1
     * and <code>j</code> = 0, 1, ..., <code>n</code>-1. */
    private double[][] f;

    /** The fastest way to get all the way through the factory. */
    private double fStar;

    /** The station used in an optimal solution to a subproblem.
     * <code>l[i][j]</code> is the line number whose station precedes
     * <i>S</i><sub><i>i</i>,<i>j</i></sub> on a fastest way from the
     * starting point through station
     * <i>S</i><sub><i>i</i>,<i>j</i></sub>, for <code>i</code> = 0, 1
     * and <code>j</code> = 0, 1, ..., <code>n</code>-1. */
    private int[][] l;

    /** The line whose station <i>n</i> is used in a fastest way
     * through the factory. */
    private int lStar;

    /**
     * Computes the fastest way through the factory, allocating the
     * instance variables and storing the result in them.
     *
     * @param a <code>a[i][j]</code> is the assembly time at station
     * <i>S</i><sub><i>i</i>,<i>j</i></sub>.
     * @param t <code>t[i][j]</code> is the time to transfer from one
     * assembly line to the other after going through station
     * <i>S</i><sub><i>i</i>,<i>j</i></sub>.
     * @param e <code>e[i]</code> is the entry time for line
     * <code>i</code>, for <code>i</code> = 0, 1.
     * @param x <code>e[i]</code> is the exit time for line
     * <code>i</code>, for <code>i</code> = 0, 1.
     * @param n The number of stations on each line.
     */
    public AssemblyLine(double[][] a,
                        double[][] t,
                        double[] e,
                        double[] x,
                        int n) {
        this.n = n;

        // Allocate arrays to hold the computation.
        f = new double[2][n];
        l = new int[2][n];

        //  Compute the fastest way.
        fastestWay(a, t, e, x, n);
    }

    /**
     * Computes the fastest way through the factory, storing the
     * result in the instance variables.  The instance variables are
     * assumed to be already allocated.  Implements the Fastest-Way
     * procedure on page 329.
     *
     * @param a <code>a[i][j]</code> is the assembly time at station
     * <i>S</i><sub><i>i</i>,<i>j</i></sub>.
     * @param t <code>t[i][j]</code> is the time to transfer from one
     * assembly line to the other after going through station
     * <i>S</i><sub><i>i</i>,<i>j</i></sub>.
     * @param e <code>e[i]</code> is the entry time for line
     * <code>i</code>, for <code>i</code> = 0, 1.
     * @param x <code>e[i]</code> is the exit time for line
     * <code>i</code>, for <code>i</code> = 0, 1.
     * @param n The number of stations on each line.
     */
    private void fastestWay(double[][] a,
                            double[][] t,
                            double[] e,
                            double[] x,
                            int n) {
        f[0][0] = e[0] + a[0][0];
        f[1][0] = e[1] + a[1][0];

        for (int j = 1; j < n; j++) {
            if (f[0][j-1] + a[0][j] <= f[1][j-1] + t[1][j-1] + a[0][j]) {
                f[0][j] = f[0][j-1] + a[0][j];
                l[0][j] = 0;
            } else {
                f[0][j] = f[1][j-1] + t[1][j-1] + a[0][j];
                l[0][j] = 1;
            }

            if (f[1][j-1] + a[1][j] <= f[0][j-1] + t[0][j-1] + a[1][j]) {
                f[1][j] = f[1][j-1] + a[1][j];
                l[1][j] = 1;
            } else {
                f[1][j] = f[0][j-1] + t[0][j-1] + a[1][j];
                l[1][j] = 0;
            }
        }

        if (f[0][n-1] + x[0] <= f[1][n-1] + x[1]) {
            fStar = f[0][n-1] + x[0];
            lStar = 0;
        } else {
            fStar = f[1][n-1] + x[1];
            lStar = 1;
        }
    }

    /** Returns the time taken by the fastest way to get all the way
     * through the factory. */
    public double getFastestTime() {
        return fStar;
    }

    /**
     * Returns the line numbers used in a fastest way through the
     * factory.  Based on the Print-Stations procedure on page 330.
     *
     * @return An array, say <code>r</code>, such that station
     * <i>S</i><sub><code>r[j]</code>,<code>j</code></sub> is used in
     * a fastest way through the factory.
     */
    public int[] getFastestRoute() {
        int[] r = new int[n];	// the array to return
        int i = lStar;

        r[n-1] = i;

        for (int j = n-1; j >= 1; j--) {
            i = l[i][j];
            r[j-1] = i;
        }

        return r;
    }

    /** Returns the <code>String</code> representation of a fastest
     * way through the factory. */
    public String toString() {
        int[] route = getFastestRoute();
        String way = "";

        for (int i = 0; i < route.length; i++)
            way += "Line " + (route[i] + 1) + ", Station " + (i + 1) + "\n";

        return way;
    }

    public static void main(String[] args) {
        double[][] stations = {
                {4, 5, 3, 2},
                {2, 10, 1, 4} };
        double[][] times = {
                {7, 4, 5},
                {9, 2, 8} };
        double[] entry = {10,12};
        double[] exit  = {18, 7};

        AssemblyLine alSchedule = new AssemblyLine(stations,times,entry,exit,4);
        int[] ss = alSchedule.getFastestRoute();
        System.out.println("Fast time="+alSchedule.getFastestTime());
    }
}
