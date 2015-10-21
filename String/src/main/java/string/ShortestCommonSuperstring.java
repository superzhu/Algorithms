package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://abdulcode.wordpress.com/2013/05/18/shortest_common_superstring/
 *
 * The shortest common superstring of 2 strings S1 and S2 is a string S
 * with the minimum number of characters which contains both S1 and S2 as a
 * sequence of consecutive characters.
 * For instance, the shortest common superstring of “alba” and “bacau” is “albacau”.
 */
public class ShortestCommonSuperstring {
    private void createSuperString(Set<String> subStrings) {
        int totalStrings = subStrings.size();
        String[] match = new String[totalStrings];
        int i = 0;

        for(String superString : subStrings) {
            Set<String> temp = new HashSet<>(subStrings);
            String maxSuperString = superString;
            while(temp.size() > 1) {

                String subString = "";
                String nextMaxSuperString = maxSuperString;

                for(String nextString : temp) {

                    if(!nextString.equals(nextMaxSuperString)) {
                        String superTemp = getSuperString(maxSuperString, nextString);
                        if (nextMaxSuperString.equals(maxSuperString) || nextMaxSuperString.length() > superTemp.length()) {
                            nextMaxSuperString = superTemp;
                            subString = nextString;
                        }
                    }
                }

                temp.remove(maxSuperString);
                temp.remove(subString);
                maxSuperString = nextMaxSuperString;
                temp.add(maxSuperString);
            }

            match[i] = maxSuperString;
            System.out.println(match[i]);
            i++;
        }

        String bestAns = match[0];

        for(i = 1; i < match.length; i++) {
            if(bestAns.length() > match[i].length()) {
                bestAns = match[i];
            }
        }

        System.out.println("Shortest Common Super String => " + bestAns);
        System.out.println("With a Length of             => " + bestAns.length());

    }

    private String getSuperString(String superString, String someString) {
        String result = superString;

        int endIndex = someString.length() - 1;

        while(endIndex > 0 && !superString.endsWith(someString.substring(0, endIndex)))	{
            endIndex--;
        }


        if(endIndex > 0) {
            result += someString.substring(endIndex);
        } else {
            result += someString;
        }

        return result;
    }

    public static void main(String arg[]) {
        Set<String> fragments = new HashSet<String>();
        ShortestCommonSuperstring superStringCreator = new ShortestCommonSuperstring();

        fragments.add("abdul");
        fragments.add("apple");
        fragments.add("mango");
        fragments.add("orange");
        fragments.add("banana");

        /* find the shortest super string */
        superStringCreator.createSuperString(fragments);
    }
}
