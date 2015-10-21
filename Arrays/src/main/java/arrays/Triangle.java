package arrays;

import java.util.ArrayList;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to
 * adjacent numbers on the row below.
 */
public class Triangle {
    public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int[] lines = new int[triangle.size()];
        int bottom = triangle.size() - 1;

        for (int i = 0; i < triangle.get(bottom).size(); i++) {
            lines[i] = triangle.get(bottom).get(i);
        }

        // iterate from last second row
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i + 1).size() - 1; j++) {
                lines[j] = triangle.get(i).get(j) + Math.min(lines[j], lines[j + 1]);
            }
        }

        return lines[0];
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> lines = new ArrayList<Integer>();
        lines.add(2);
        triangle.add(lines);

        lines = new ArrayList<Integer>();
        lines.add(3);
        lines.add(4);
        triangle.add(lines);

        lines = new ArrayList<Integer>();
        lines.add(6);
        lines.add(5);
        lines.add(7);
        triangle.add(lines);

        lines = new ArrayList<Integer>();
        lines.add(4);
        lines.add(1);
        lines.add(8);
        lines.add(3);
        triangle.add(lines);

        System.out.println("Minimum path sum = " + minimumTotal(triangle));
    }
}
