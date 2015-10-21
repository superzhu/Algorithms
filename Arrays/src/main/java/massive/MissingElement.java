package massive;

import java.io.*;
import java.util.*;

/**
 * Problem 12.4, pg. 67: Suppose you were given a file containing roughly one billion Internet
 * Protocol (IP) addresses, each of which is a 32-bit unsigned integer. How would you
 * programmatically find an IP address that is not in the file? Assume you have unlimited drive
 * space but only two megabytes of RAM at your disposal.
 *
 * Solution 12.4: In the first step, we build an array of 2^16 32-bit unsigned integers that
 * is initialized to 0 and for every IP address in the file, we take its 16 MSBs to index
 * into this array and increment the count of that number. Since the file contains fewer
 * than 2^32 numbers, there must be one entry in the array that is less than 2^16. This tells
 * us that there is at least one IP address which has those upper bits and is not in the
 * file. In the second pass, we can focus only on the addresses that match this criterion
 * and use a bit array of size 2^16 to identify one of the missing numbers.
 */
public class MissingElement {
    // @include
    public static int findMissingElement(InputStream ifs) throws IOException {
        int[] counter = new int[1 << 16];
        ifs.mark(Integer.MAX_VALUE);
        Scanner s = new Scanner(ifs);

        while (s.hasNextInt()) {
            ++counter[s.nextInt() >> 16];
        }

        for (int i = 0; i < counter.length; ++i) {
            //Look for a bucket that contains less than (1 << 16) elements.
            if (counter[i] < (1 << 16)) {
                BitSet bitVec = new BitSet(1 << 16);
                ifs.reset();
                s = new Scanner(ifs);
                while (s.hasNext()) {
                    int x = s.nextInt();
                    if (i == (x >> 16)) {
                        bitVec.set(((1 << 16) - 1) & x); // Gets the lower 16 bits of x.
                    }
                }
                ifs.close();

                for (int j = 0; j < (1 << 16); ++j) {
                    if (!bitVec.get(j)) {
                        return (i << 16) | j;
                    }
                }
            }
        }

        // @exclude
        throw new IllegalArgumentException("no missing element");
        // @include
    }
    // @exclude

    public static void main(String[] args) {
        int n = 990000;
        Random r = new Random();
        if (args.length == 1) {
            n = Integer.parseInt(args[0]);
        }
        File missingFile = new File("missing.txt");
        Set<Integer> hash = new HashSet<>();
        FileOutputStream ofs = null;
        try {
            try {
                ofs = new FileOutputStream(missingFile);
                OutputStreamWriter osw = new OutputStreamWriter(ofs);
                for (int i = 0; i < n; ++i) {
                    int x;
                    do {
                        x = r.nextInt(1000000);
                    } while (!hash.add(x));
                    osw.write(x + "\n");
                }
            } finally {
                if (ofs != null) {
                    ofs.close();
                }
            }

            FileInputStream ifs = null;
            try {
                ifs = new FileInputStream(missingFile);
                BufferedInputStream bis = new BufferedInputStream(ifs);
                int missing = findMissingElement(bis);
                assert(!hash.contains(missing));
                System.out.println(missing);
            } finally {
                if (ifs != null) {
                    ifs.close();
                }
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            // Remove file after the execution.
            missingFile.delete();
        }
    }

}
