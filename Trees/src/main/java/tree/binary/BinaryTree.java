package tree.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * http://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/
 */
public class BinaryTree<E> {

    public BinaryTreeNode<E> createBinaryTreeNode(E value) {
        BinaryTreeNode<E> pNode = new BinaryTreeNode<E>();
        pNode.value = value;
        pNode.left = null;
        pNode.right = null;

        return pNode;
    }

    void connectTreeNodes(BinaryTreeNode<E> pParent, BinaryTreeNode<E> pLeft, BinaryTreeNode<E> pRight) {
        if(pParent != null) {
            pParent.left = pLeft;
            pParent.right = pRight;
        }
    }

    //Binary Tree Inorder Traversal --- Recursive
    public List<E> inorderRecursiveTraversal(BinaryTreeNode<E> root) {
        List<E> result = new ArrayList<E>();

        if(root !=null){
            helper(root,result);
        }

        return result;
    }

    public void helper(BinaryTreeNode<E> p,List<E> result){
        if(p.left!=null)
            helper(p.left,result);

        result.add(p.value);

        if(p.right!=null)
            helper(p.right,result);
    }

    public List<E> inorderIterativeTraversal(BinaryTreeNode<E> root) {
        List<E> result = new ArrayList<E>();

        if(root == null)
            return result;

        Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
        //define a pointer to track nodes
        BinaryTreeNode<E> p = root;

        while(!stack.empty() || p != null){
            // if it is not null, push to stack
            //and go down the tree to left
            if(p != null){
                stack.push(p);
                p = p.left;
            }else{
                // if no left child pop stack, process the node
                // then let p point to the right
                BinaryTreeNode<E> t = stack.pop();
                result.add(t.value);
                p = t.right;
            }
        }

        return result;
    }

    public ArrayList<E> preorderTraversal(BinaryTreeNode<E> root) {
        ArrayList<E> result = new ArrayList<E>();

        if(root == null)
            return result;

        Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
        stack.push(root);

        while(!stack.empty()){
            BinaryTreeNode<E> n = stack.pop();
            result.add(n.value);

            if(n.right != null){
                stack.push(n.right);
            }
            if(n.left != null){
                stack.push(n.left);
            }

        }
        return result;
    }

    public List<E> postorderTraversal(BinaryTreeNode<E> root) {
        ArrayList<E> lst = new ArrayList<E>();

        if(root == null)
            return lst;

        Stack<BinaryTreeNode<E>> stack = new Stack<BinaryTreeNode<E>>();
        stack.push(root);

        BinaryTreeNode<E> prev = null;
        while(!stack.empty()){
            BinaryTreeNode<E> curr = stack.peek();

            // go down the tree.
            //check if current node is leaf, if so, process it and pop stack,
            //otherwise, keep going down
            if(prev == null || prev.left == curr || prev.right == curr){
                //prev == null is the situation for the root node
                if(curr.left != null){
                    stack.push(curr.left);
                }else if(curr.right != null){
                    stack.push(curr.right);
                }else{
                    stack.pop();
                    lst.add(curr.value);
                }

                //go up the tree from left node
                //need to check if there is a right child
                //if yes, push it to stack
                //otherwise, process parent and pop stack
            }else if(curr.left == prev){
                if(curr.right != null){
                    stack.push(curr.right);
                }else{
                    stack.pop();
                    lst.add(curr.value);
                }

                //go up the tree from right node
                //after coming back from right node, process parent node and pop stack.
            }else if(curr.right == prev){
                stack.pop();
                lst.add(curr.value);
            }

            prev = curr;
        }

        return lst;
    }

    //http://www.programcreek.com/2014/04/leetcode-binary-tree-level-order-traversal-java/

    /**
     * 6) Flatten Binary Tree to Linked List in place
     *  http://rangerway.com/way/algorithm-binary-tree-to-linked-list/
     */
    BinaryTreeNode<E> flattenInorder(BinaryTreeNode<E> root) {
        BinaryTreeNode<E> head = null;
        BinaryTreeNode<E> last = null;

        while (root != null) {
            if (root.left != null) {
                BinaryTreeNode<E> pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                pre.right = root;
                pre = pre.right;
                root = root.left;
                pre.left = null;
                if (last != null) {
                    last.right = root;
                }
            } else {
                if (head == null) head = root;
                last = root;
                root = root.right;
            }
        }

        return head;
    }

    public int treeDepth(BinaryTreeNode<E> root) {
        if(root == null)
            return 0;

        int left = treeDepth(root.left);
        int right = treeDepth(root.right);

        return (left > right) ? (left + 1) : (right + 1);
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        BinaryTreeNode<Integer> root = tree.createBinaryTreeNode(1);
        BinaryTreeNode<Integer> left = tree.createBinaryTreeNode(2);
        BinaryTreeNode<Integer> right = tree.createBinaryTreeNode(3);
        tree.connectTreeNodes(root,left,right);

        BinaryTreeNode<Integer> leftChild = tree.createBinaryTreeNode(4);
        right = tree.createBinaryTreeNode(5);
        tree.connectTreeNodes(left,leftChild,right);

        //List<Integer> result = tree.inorderRecursiveTraversal(root);
        List<Integer> result = tree.inorderIterativeTraversal(root);

        for(Integer item : result) {
            System.out.println(item);
        }
    }
}
