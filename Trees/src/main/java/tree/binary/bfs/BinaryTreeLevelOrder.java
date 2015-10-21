package tree.binary.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * EPI 9.2: Print a binary tree in order of increasing depth
 * Given the root node r of a binary tree, print all the keys at r and its
 * descendants. The keys should be printed in the order of the corresponding nodes’
 * depths. Specifically, all keys corresponding to nodes of depth d should appear in a
 * single line, and the next line should correspond to keys corresponding to nodes of
 * depth d + 1.
 */
public class BinaryTreeLevelOrder {
    // @include
    public static List<List<Integer>> binaryTreeDepthOrder(
            BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> tree) {
        LinkedList<BinaryTreePrototypeTemplate.BinaryTreeNode<Integer>> processingNodes = new LinkedList<>();
        processingNodes.push(tree);
        int numNodesToProcessAtCurrentLevel = processingNodes.size();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> oneLevel = new ArrayList<>();

        while (!processingNodes.isEmpty()) {
            BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> curr = processingNodes.pollLast();
            --numNodesToProcessAtCurrentLevel;
            if (curr != null) {
                oneLevel.add(curr.getData());

                // Defer the null checks to the null test above.
                processingNodes.push(curr.getLeft());
                processingNodes.push(curr.getRight());
            }
            // Are we done with the nodes at the current depth?
            if (numNodesToProcessAtCurrentLevel == 0) {
                numNodesToProcessAtCurrentLevel = processingNodes.size();
                result.add(new ArrayList(oneLevel));
                oneLevel.clear();
            }
        }
        return result;
    }
    // @exclude

    public static void main(String[] args) {
        //      3
        //    2   5
        //  1    4 6
        // 10
        // 13
        BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> tree = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(3);
        tree.setLeft(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(2));
        tree.getLeft().setLeft(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(1));
        tree.getLeft().getLeft().setLeft(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(10));
        tree.getLeft().getLeft().getLeft().setRight(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(13));
        tree.setRight(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(5));
        tree.getRight().setLeft(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(4));
        tree.getRight().setRight(new BinaryTreePrototypeTemplate.BinaryTreeNode<>(6));
        List<List<Integer>> result = binaryTreeDepthOrder(tree);
        List<List<Integer>> goldenRes = new ArrayList<>();
        goldenRes.add(Arrays.asList(3));
        goldenRes.add(Arrays.asList(2, 5));
        goldenRes.add(Arrays.asList(1, 4, 6));
        goldenRes.add(Arrays.asList(10));
        goldenRes.add(Arrays.asList(13));
        goldenRes.add(new ArrayList());
        assert(goldenRes.equals(result));
    }
}
