package tree.binary.bfs;

import tree.binary.bfs.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.LinkedList;

/**
 * Printing a Binary Tree in Zig Zag Level-Order
 * Given a binary tree, print out the tree in zig zag level order (ie, from left to right, then right
 * to left for the next level and alternate between). Output a newline after the end of each level
 *        3
 *    9       20
 *         15    7
 *   For example, the zig zag level order output of the tree above is:
 *      3
 *      20 9
 *      15 7
 */
public class BinaryTreeZigZagLevelOrder {
    static void printLevelOrderZigZag(BinaryTreeNode<Integer> root) {
        //use LinkedList to simulate Stack
        LinkedList<BinaryTreeNode<Integer>> currentLevel = new LinkedList<>(), nextLevel=new LinkedList<>();
        boolean leftToRight = true;

        currentLevel.push(root);
        while (!currentLevel.isEmpty()) {
            BinaryTreeNode<Integer> currentNode = currentLevel.pop();
            if(currentNode != null) {
                System.out.print(currentNode.getData() + "  ");
                if(leftToRight) {
                    nextLevel.push(currentNode.getLeft());
                    nextLevel.push(currentNode.getRight());
                } else {
                    nextLevel.push(currentNode.getRight());
                    nextLevel.push(currentNode.getLeft());
                }
            }

            if(currentLevel.isEmpty()) {
                leftToRight = !leftToRight;
                System.out.println();

                LinkedList<BinaryTreeNode<Integer>> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(3);
        tree.setLeft(new BinaryTreeNode<>(9));
        tree.setRight(new BinaryTreeNode<>(20));
        tree.getRight().setLeft(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(15));
        tree.getRight().setRight(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(7));

        printLevelOrderZigZag(tree);
    }
}
