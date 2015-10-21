package tree.bst;

import tree.binary.BinarySearchTreePrototypeTemplate.BSTNode;

/**
 * Find the first occurrence of a key in a BST
 */
public class SearchBSTForFirstOccurrenceIterative {
    // @include
    public static BSTNode<Integer> findFirstEqualK(BSTNode<Integer> tree, Integer k) {
        BSTNode<Integer> firstSoFar = null, curr = tree;
        while (curr != null) {
            if (Integer.compare(curr.getData(), k) < 0) {
                curr = curr.getRight();
            } else if (Integer.compare(curr.getData(), k) > 0) {
                curr = curr.getLeft();
            } else { // curr.getData() is equal to k
                // Record this node, and search for the first node in the left subtree.
                firstSoFar = curr;
                curr = curr.getLeft();
            }
        }
        return firstSoFar;
    }
    // @exclude

    // @include
    public static BSTNode<Integer> findFirstEqualK(BSTNode<Integer> tree, int k) {
        if (tree == null) {
            return null; // No match.
        } else if (tree.getData() == k) {
            // Recursively search the left subtree for first node containing k.
            BSTNode<Integer> node = findFirstEqualK(tree.getLeft(), k);
            return node != null ? node : tree;
        }
        // Search the left or right subtree based on relative values of tree.getData() and k.
        return findFirstEqualK(tree.getData() < k ? tree.getRight() : tree.getLeft(),
                k);
    }
    // @exclude

    public static void main(String[] args) {
        // 3
        // 2 5
        // 1 4 6
        BSTNode<Integer> tree = new BSTNode<>(3);
        tree.setLeft(new BSTNode<>(2));
        tree.getLeft().setLeft(new BSTNode<>(1));
        tree.setRight(new BSTNode<>(5));
        tree.getRight().setLeft(new BSTNode<>(4));
        tree.getRight().setRight(new BSTNode<>(6));
        assert(findFirstEqualK(tree, 7) == null);
        assert(findFirstEqualK(tree, 6).getData().equals(6));

        tree = new BSTNode<>(3);
        tree.setLeft(new BSTNode<>(3));
        tree.getLeft().setLeft(new BSTNode<>(1));
        tree.setRight(new BSTNode<>(5));
        tree.getRight().setLeft(new BSTNode<>(5));
        tree.getRight().setRight(new BSTNode<>(5));
        assert(findFirstEqualK(tree, 3) == tree.getLeft());
        assert(findFirstEqualK(tree, 5).equals(tree.getRight().getLeft()));
        assert(findFirstEqualK(tree, 5).getData().equals(5));
    }
}
