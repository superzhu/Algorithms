package set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generating all subsets (the power set) of a given set of
 * characters (or numbers) is very similar to generating combinations.
 *
 * http://exceptional-code.blogspot.jp/2012/09/generating-all-permutations.html
 */
public class PowerSet {
    public static <T> Set<Set<T>> generateAllSubsets(Set<T> original) {
        Set<Set<T>> allSubsets = new HashSet<Set<T>>();

        allSubsets.add(new HashSet<T>()); //Add empty set.

        for (T element : original) {
            // Copy subsets so we can iterate over them without ConcurrentModificationException
            Set<Set<T>> tempClone = new HashSet<Set<T>>(allSubsets);

            // All element to all subsets of the current power set.
            for (Set<T> subset : tempClone) {
                Set<T> extended = new HashSet<T>(subset);
                extended.add(element);
                allSubsets.add(extended);
            }
        }

        return allSubsets;
    }

    /**
     * print all permutations of a given string
     */
    static void  generatePermutations(char[] arr, int size, char[] branch, int level, boolean[] visited) {
        if (level >= size-1) {
            System.out.println(branch);
            return;
        }

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                branch[++level] = arr[i];
                visited[i] = true;
                generatePermutations(arr, size, branch, level, visited);
                visited[i] = false;
                level--;
            }
        }
    }

    //Generating combinations of k elements
    static void combine(char[] arr, int k, int startId, char[] branch, int numElem) {
        if (numElem == k) {
            System.out.println(Arrays.toString(branch));
            return;
        }

        for (int i = startId; i < arr.length; ++i) {
            branch[numElem] = arr[i];
            combine(arr, k, ++startId, branch, ++numElem);
            --numElem;
        }
    }

    static void powerSet(char[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            char[] branch = new char[i];
            combine(arr, i, 0, branch, 0);
        }
    }

    public static void  main(String[] args) {
        Set<String> original = Stream.of("a", "b").collect(Collectors.toSet());

        Set<Set<String>> allSets = generateAllSubsets(original);

        System.out.println("All set size = " + allSets.size());

        String str = "ABCD";
        int n = str.length();
        char[] arr = str.toCharArray();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++)
            visited[i] = false;
        char[] branch = new char[n];
        generatePermutations(arr, n, branch, -1, visited);

        /*int k = 2;
        char[] input = "ABCD".toCharArray();
        char[] branch = new char[k];
        combine(input, k, 0, branch, 0);

        System.out.println("All powerset");
        powerSet(input);*/
    }
}
