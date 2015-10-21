package string.suffix.tree;

/**
 * This is a Java-port of Mark Nelson's C++ implementation of Ukkonen's algorithm.
 * http://illya-keeplearning.blogspot.com/2009/04/suffix-trees-java-ukkonens-algorithm.html
 */
import java.io.PrintStream;

public class SuffixTree {
    public static final int MAX_LENGTH = 1000;

    //a prime roughly 10% larger
    public static int HASH_TABLE_SIZE = 2179;

    // This is the hash table where all the currently defined EDGES are stored.
    // You can dump out all the currently defined EDGES by iterating through the table
    // and finding EDGES whose start_node is not -1.
    public static Edge[] EDGES = new Edge[HASH_TABLE_SIZE];

    // The array of defined NODES. The count is 1 at the start
    // because the initial tree has the root node defined, with no children.
    public static Node[] NODES = new Node[MAX_LENGTH * 2];

    static {
        for (int i = 0; i < EDGES.length; i++) {
            EDGES[i] = new Edge();
        }
        for (int i = 0; i < NODES.length; i++) {
            NODES[i] = new Node();
        }
    }

    // The input buffer and character count. Please note that N is the length of the input string -1,
    // which means it denotes the maximum index in the input buffer.
    public static char[] T;
    public static int N;

    /**
     * This routine constitutes the heart of the algorithm. It is called repetitively, once for each of the prefixes
     * of the input string.  The prefix in question is denoted by the index of its last character.
     * <p/>
     * At each prefix, we start at the active point, and add a new edge denoting the new last character,
     * until we reach a point where the new edge is not needed due to the presence of an existing edge
     * starting with the new last character.  This point is the end point.
     * <p/>
     * Luckily for use, the end point just happens to be the active point for the next pass through the tree.
     * All we have to do is update it's last_char_index to indicate that it has grown by a single character,
     * and then this routine can do all its work one more time.
     *
     * @param active
     * @param last_char_index
     */
    public static void addPrefix(Suffix active, int last_char_index) {
        int parent_node;
        int last_parent_node = -1;

        for (; ;) {
            Edge edge;
            parent_node = active.origin_node;

            // Step 1 is to try and find a matching edge for the given node.
            // If a matching edge exists, we are done adding edges, so we break
            // out of this big loop.
            if (active.explicit()) {
                edge = Edge.find(active.origin_node, T[last_char_index]);
                if (edge.start_node != -1)
                    break;
            } else {
                //implicit node, a little more complicated
                edge = Edge.find(active.origin_node, T[active.first_char_index]);
                int span = active.last_char_index - active.first_char_index;
                if (T[edge.first_char_index + span + 1] == T[last_char_index])
                    break;
                parent_node = edge.splitEdge(active);
            }

            // We didn't find a matching edge, so we create a new one, add
            // it to the tree at the parent node position, and insert it
            // into the hash table.  When we create a new node, it also
            // means we need to create a suffix link to the new node from
            // the last node we visited.
            Edge new_edge = new Edge(last_char_index, N, parent_node);
            new_edge.insert();
            if (last_parent_node > 0)
                NODES[last_parent_node].suffix_node = parent_node;
            last_parent_node = parent_node;

            // This final step is where we move to the next smaller suffix
            if (active.origin_node == 0)
                active.first_char_index++;
            else
                active.origin_node = NODES[active.origin_node].suffix_node;
            active.canonize();
        }
        if (last_parent_node > 0)
            NODES[last_parent_node].suffix_node = parent_node;
        active.last_char_index++;  //Now the endpoint is the next active point
        active.canonize();
    }

    // This routine prints out the contents of the suffix tree at the end of the program by walking through
    // the hash table and printing out all used edges. It would be really great if I had some code that
    // will print out the tree in a graphical fashion, but I don't!
    public static void dump_edges(int current_n, PrintStream out) {
        out.println("\tStart \tEnd \tSuf \tFirst \tLast \tString");
        for (int j = 0; j < HASH_TABLE_SIZE; j++) {
            Edge s = EDGES[j];
            if (s.start_node == -1)
                continue;

            out.print("\t" + s.start_node + " " +
                    "\t\t" + s.end_node + " " +
                    "\t\t" + NODES[s.end_node].suffix_node + " " +
                    "\t\t" + s.first_char_index + " " +
                    "\t\t" + s.last_char_index + " " +
                    "\t\t");
            int top;
            if (current_n > s.last_char_index)
                top = s.last_char_index;
            else
                top = current_n;
            for (int l = s.first_char_index; l <= top; l++) {
                out.print(T[l]);
            }
            out.println();
            out.flush();
        }
    }
}
