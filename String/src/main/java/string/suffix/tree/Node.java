package string.suffix.tree;

/**
 * http://www.geeksforgeeks.org/pattern-searching-set-8-suffix-tree-introduction/
 * http://illya-keeplearning.blogspot.jp/2009/04/suffix-trees-java-ukkonens-algorithm.html
 */
public class Node {
    public int suffix_node;

    public Node() {
        this.suffix_node = -1;
    }

    public static int Count = 1;
}
