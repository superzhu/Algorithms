package search.dp;

/**
 * Created by zhzhzhu on 2015/8/13.
 */
public class Util {
    private Util(){}

    public static int replaceCost(String w1, String w2, int w1Index, int w2Index) {
        return (w1.charAt(w1Index) == w2.charAt(w2Index)) ? 0 : 1;
    }

    public static int min(int... numbers) {
        int result = Integer.MAX_VALUE;

        for (int each : numbers) {
            result = Math.min(result, each);
        }

        return result;
    }
}
