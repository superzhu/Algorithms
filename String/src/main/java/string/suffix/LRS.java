package string.suffix;

import string.pattern.common.StdOut;

/**
 * Longest repeated substring
 */
public class LRS {

    public static void main(String[] args) {
        String text = "";//StdIn.readAll().replaceAll("\\s+", " ");
        SuffixArray sa = new SuffixArray(text);

        int N = sa.length();

        String lrs = "";
        for (int i = 1; i < N; i++) {
            int length = sa.lcp(i);
            if (length > lrs.length()) {
                // lrs = sa.select(i).substring(0, length);
                lrs = text.substring(sa.index(i), sa.index(i) + length);
            }
        }

        StdOut.println("'" + lrs + "'");
    }
}

