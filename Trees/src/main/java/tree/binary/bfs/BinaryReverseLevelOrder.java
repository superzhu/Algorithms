package tree.binary.bfs;

import tree.binary.bfs.BinaryTreePrototypeTemplate.BinaryTreeNode;

import java.util.LinkedList;

/**
 * Write a function which takes as input a binary tree where each node
 * is labeled with an integer and prints all the node keys in a bottom up, left-to-right order.
 *                     1
 *             2                3
 *        4                 5        6
 *   The output should be:
 *       456
 *        23
 *        1
 */
public class BinaryReverseLevelOrder {
    static void reverseLevelOrder(BinaryTreeNode root) {
        if(root == null)
            return;

        LinkedList<BinaryTreeNode<Integer>> queue = new LinkedList<>();
        LinkedList<BinaryTreeNode<Integer>> stack = new LinkedList<>();
        LinkedList<Integer> counts =  new LinkedList<>();
        int currentLevelNodeNum = 1;//used to record num of nodes in current level
        int nextLevelNodeNum = 0;//used to record num of nodes in next level
        queue.offer(root);
        counts.push(currentLevelNodeNum);

        while(!queue.isEmpty()) {
            /* Dequeue node and make it root */
            root = queue.peekFirst();
            queue.pop();
            currentLevelNodeNum--;
            stack.push(root);

            // NOTE: RIGHT CHILD IS ENQUEUED BEFORE LEFT
            if (root.getRight() != null) {
                nextLevelNodeNum++;
                queue.offer(root.getRight());
            }

            if(root.getLeft() != null) {
                nextLevelNodeNum++;
                queue.offer(root.getLeft());
            }

            if(currentLevelNodeNum == 0 && nextLevelNodeNum!=0) {//if we have traversed current level, turn to next level
                currentLevelNodeNum = nextLevelNodeNum;//assign next level node num to current
                counts.push(currentLevelNodeNum);
                nextLevelNodeNum = 0;//set next level num to 0
            }
        }

        currentLevelNodeNum = counts.pop();
        while(!stack.isEmpty()) {
            root = stack.peekFirst();
            stack.pop();
            currentLevelNodeNum--;
            System.out.print(root.getData() + " ");

            if(currentLevelNodeNum == 0 && !counts.isEmpty()){
                System.out.println();
                currentLevelNodeNum = counts.pop();
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(1);
        tree.setLeft(new BinaryTreeNode<>(2));
        tree.setRight(new BinaryTreeNode<>(3));

        tree.getLeft().setLeft(new BinaryTreeNode<>(4));
        tree.getRight().setLeft(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(5));
        tree.getRight().setRight(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(6));

        reverseLevelOrder(tree);
    }
}
