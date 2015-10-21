package tree.binary;

/**
 * How do you verify whether a binary tree is balanced? If the depth difference between a left
 * subtree and right subtree of any node in a binary tree is not greater than 1, it is balanced.
 */
public class BalancedTree<E> {
    public static class Depth{
        int depth;
    }

    boolean IsBalanced(BinaryTreeNode<E> root, Depth pDepth) {
        if(root == null) {
            pDepth.depth = 0;
            return true;
        }

        Depth left = new Depth(), right = new Depth();
        if(IsBalanced(root.left, left) && IsBalanced(root.right, right)) {
            int diff = left.depth - right.depth;
            if(diff <= 1 && diff >= -1) {
                pDepth.depth = 1 + (left.depth > right.depth ? left.depth : right.depth);
                return true;
            }
        }

        return false;
    }

    /**
     * Visiting Every Node Only Once
     * If a binary tree is scanned with the post-order algorithm, its left and right subtrees are traversed before
     * the root node. If we record the depth of the currently visited node (the depth of a node is the maximum
     * length of paths from the node to its leaf nodes), we can verify whether the subtree rooted at the currently
     * visited node is balanced. If any subtree is unbalanced, the whole tree is unbalanced.
     *
     */
    boolean IsBalanced_Solution2(BinaryTreeNode<E> root) {
        Depth depth = new Depth();
        return IsBalanced(root, depth);
    }
}
