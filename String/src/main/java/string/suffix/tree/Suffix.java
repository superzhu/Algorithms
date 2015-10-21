package string.suffix.tree;

/**
 * Defines suffix.
 * <p/>
 * When a new tree is added to the table, we step through all the currently defined suffixes
 * from the active point to the end point.  This structure defines a <b>Suffix</b> by its final character.
 * In the canonical representation, we define that last character by starting at a node in the tree,
 * and following a string of characters, represented by <b>first_char_index</b> and <b>last_char_index</b>.
 * The two indices point into the input string.  Note that if a suffix ends at a node,
 * there are no additional characters needed to characterize its last character position.
 * When this is the case, we say the node is <b>explicit</b>,
 * and set <b>first_char_index > last_char_index<b> to flag that.
 */
public class Suffix {
    public int origin_node;
    public int first_char_index;
    public int last_char_index;

    public Suffix(int node, int start, int stop) {
        this.origin_node = node;
        this.first_char_index = start;
        this.last_char_index = stop;
    }

    public boolean explicit() {
        return this.first_char_index > this.last_char_index;
    }

    public boolean implicit() {
        return last_char_index >= first_char_index;
    }

    public void canonize() {
        if (!explicit()) {
            Edge edge = Edge.find(origin_node, SuffixTree.T[first_char_index]);
            int edge_span = edge.last_char_index - edge.first_char_index;
            while (edge_span <= (last_char_index - first_char_index)) {
                first_char_index = first_char_index + edge_span + 1;
                origin_node = edge.end_node;
                if (first_char_index <= last_char_index) {
                    edge = Edge.find(edge.end_node, SuffixTree.T[first_char_index]);
                    edge_span = edge.last_char_index - edge.first_char_index;
                }
            }
        }
    }
}
