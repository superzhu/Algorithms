package string.suffix.simpletree;

import java.util.ArrayList;

/**
 * 18.8*Cracking--Given a string s and an array of smaller strings T, design a method to search s for
 * each small string in T.
 */
public class SimpleSuffixTree {
    SuffixTreeNode root = new SuffixTreeNode();

    public SimpleSuffixTree(String s) {
        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(i);
            root.insertString(suffix, i);
        }
    }

    public ArrayList<Integer> search(String s) {
        return root.search(s);
    }

    public static void main(String[] args) {
        String testString = "mississippi";
        String[] stringList = {"is", "sip", "hi", "sis"};
        SimpleSuffixTree tree = new SimpleSuffixTree(testString);
        for (String s : stringList) {
            ArrayList<Integer> list = tree.search(s);
            if (list != null) {
                System.out.println(s + ": " + list.toString());
            }
        }
    }
}
