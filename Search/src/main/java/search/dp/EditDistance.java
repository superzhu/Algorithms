package search.dp;

/**
 * http://www.javacodegeeks.com/2014/03/easy-to-understand-dynamic-programming-edit-distance.html
 */
public class EditDistance {
    public int editDistance(String word1, String word2) {
        //contains bug
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();

        int word1Length = word1.length();
        int word2Length = word2.length();

        //minCosts[i][j] represents the edit distance of the substrings
        //word1.substring(i) and word2.substring(j)
        int[][] minCosts = new int[word1Length][word2Length];

        //This is the edit distance of the last char of word1 and the last char of word2
        //It can be 0 or 1 depending on whether the two are different or equal
        minCosts[word1Length - 1][word2Length - 1] = Util.replaceCost(word1, word2, word1Length - 1, word2Length - 1);

        for (int j = word2Length - 2; j >= 0; j--) {
            minCosts[word1Length - 1][j] = 1 + minCosts[word1Length - 1][j + 1];
        }

        for (int i = word1Length - 2; i >= 0; i--) {
            minCosts[i][word2Length - 1] = 1 + minCosts[i + 1][word2Length - 1];
        }

        for (int i = word1Length - 2; i >= 0; i--) {
            for (int j = word2Length - 2; j >= 0; j--) {
                int replace = Util.replaceCost(word1, word2, i, j) + minCosts[i + 1][j + 1];
                int delete = 1 + minCosts[i + 1][j];
                int insert = 1 + minCosts[i][j + 1];
                minCosts[i][j] = Util.min(replace, delete, insert);
            }
        }

        return minCosts[0][0];
    }

    public int editDistance2(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        int[][] distances = new int[len1+1][len2+1];

        for (int i = 0; i < len1 + 1; ++i)
            distances[i][0] = i;
        for (int j = 0; j < len2 + 1; ++j)
            distances[0][j] = j;

        //http://www.programcreek.com/2013/12/edit-distance-in-java/
        for (int i = 1; i < len1 + 1; ++i) {
            for (int j = 1; j < len2 + 1; ++j) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    distances[i][j] = distances[i - 1][j - 1];
                else {
                    int deletion = distances[i - 1][j] + 1;
                    int insertion = distances[i][j - 1] + 1;
                    int substitution = distances[i - 1][j - 1] + 1;
                    distances[i][j] = Util.min(deletion, insertion, substitution);
                }
            }
        }

        return distances[len1][len2];
    }

    public static void main(String[] args) {
        EditDistance edit = new EditDistance();

        System.out.println(edit.editDistance2("kitten", "kitten"));//0
        System.out.println(edit.editDistance2("kitten", "sitting"));//3

        System.out.println(edit.editDistance("Saturday", "Sunday"));//3
    }
}
