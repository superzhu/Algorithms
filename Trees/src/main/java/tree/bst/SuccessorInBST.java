package tree.bst;

import tree.binary.BinaryTreeWithParentPrototype.BinaryTree;
/**
 * The successor of a node n in a BST is the node s that appears
 * immediately after n in an inorder walk. When all keys are distinct,
 * s holds the smallest key larger than the key at n (The last node
 * in the inorder walk has no successor)
 */
public class SuccessorInBST {
    // @include
    public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
        BinaryTree<Integer> iter = node;
        if (iter.getRight() != null) {
            // Find the leftmost element in node's right subtree.
            iter = iter.getRight();
            while (iter.getLeft() != null) {
                iter = iter.getLeft();
            }
            return iter;
        }

        // Find the closest ancestor whose left subtree contains node.
        while (iter.getParent() != null && iter.getParent().getRight() == iter) {
            iter = iter.getParent();
        }
        // A return value of null means node does not have successor, i.e., it is
        // the rightmost node in the tree.
        return iter.getParent();
    }
    // @exclude

    public static void main(String[] args) {
        //    3
        //  2   5
        // 1   4 6
        BinaryTree<Integer> root = new BinaryTree<>(3, null, null);
        assert(findSuccessor(root) == null);
        root.setLeft(new BinaryTree<>(2, null, null));
        root.getLeft().setParent(root);
        assert(findSuccessor(root.getLeft()).getData() == 3);

        root.getLeft().setLeft(new BinaryTree<>(1, null, null));
        root.getLeft().getLeft().setParent(root.getLeft());
        assert(findSuccessor(root.getLeft()).getData() == 3);
        assert(findSuccessor(root.getLeft().getLeft()).getData() == 2);

        root.setRight(new BinaryTree<>(5, null, null));
        root.getRight().setParent(root);
        root.getRight().setLeft(new BinaryTree<>(4, null, null));
        root.getRight().getLeft().setParent(root.getRight());
        root.getRight().setRight(new BinaryTree<>(6, null, null));
        root.getRight().getRight().setParent(root.getRight());
        // should output 6
        BinaryTree<Integer> node = findSuccessor(root.getRight());
        assert(node.getData().equals(6));
        System.out.println(node.getData());
        // should output "null"
        node = findSuccessor(root.getRight().getRight());
        assert(node == null);
        if (node != null) {
            System.out.println(node.getData());
        } else {
            System.out.println("null");
        }
    }
}
