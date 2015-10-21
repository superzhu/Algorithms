package string.TRIE;

import java.util.HashMap;

/**
 * Created by zhzhzhu on 2015/8/24.
 */
public class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean isLeaf;

    public TrieNode() {}

    public TrieNode(char c){
        this.c = c;
    }
}
