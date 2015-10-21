package string.design.cache;

/**
 * https://github.com/gaylemcd/ctci/blob/master/java/Chapter%2010/Question10_7/Node.java
 */
public class Node {
    public Node prev;
    public Node next;
    public String[] results;
    public String query;

    public Node(String q, String[] res) {
        results = res;
        query = q;
    }
}
