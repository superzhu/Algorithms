package tree.binary;

/**
 * How do you convert a binary search tree into a sorted double-linked list without creating any
 * new nodes? It is only allowed that you can reconnect links between existing nodes.
 *
 * https://chesterli0130.wordpress.com/2012/10/08/convert-binary-search-tree-bst-to-sorted-doubly-linked-list/
 */
public class ConvertBinarySearchTree<E> {
    private static class Context<E> {
        public BinaryTreeNode<E> head;
        public BinaryTreeNode<E> prev;
    }

    void convertNode(BinaryTreeNode<E> root,Context<E> context) {
        if(root == null)
            return;

        convertNode(root.left, context);

        if (context.prev == null) {
            context.head = root;
        } else {
            root.left = context.prev;
            context.prev.right = root;
        }

        context.prev = root;
        convertNode(root.right, context);
    }

    /**
     * LeetCode – Binary Tree Maximum Path Sum (Java)
     * Given a binary tree, find the maximum path sum.
     * The path may start and end at any node in the tree.
     *
     */
    private int getMax(BinaryTreeNode<Integer>  x){
        if(x == null){
            return 0;
        }
        int left = getMax(x.left);
        int right = getMax(x.right);
        return x.value + (left > right ? left:right);
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        BinaryTreeNode<Integer> root = tree.createBinaryTreeNode(8);
        BinaryTreeNode<Integer> left = tree.createBinaryTreeNode(6);
        BinaryTreeNode<Integer> right = tree.createBinaryTreeNode(10);
        tree.connectTreeNodes(root, left, right);

        BinaryTreeNode<Integer> leftChild = tree.createBinaryTreeNode(5);
        BinaryTreeNode<Integer> rightChild = tree.createBinaryTreeNode(7);
        tree.connectTreeNodes(left, leftChild, rightChild);

        leftChild = tree.createBinaryTreeNode(9);
        rightChild = tree.createBinaryTreeNode(11);
        tree.connectTreeNodes(right, leftChild, rightChild);

        ConvertBinarySearchTree<Integer> converter = new ConvertBinarySearchTree<>();
        Context<Integer> context = new Context<>();
        converter.convertNode(root,context);

        System.out.println("Head of converted doubled-linked list: " + context.head.value);
    }
}
