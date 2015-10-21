package tree.bst;

/**
 *
 * This class creates nodes for a doubly linked list data structure. Each node
 * contains a string data, as well as references to a prev and a next node
 * of the same class.  This allows the user to create a dynamic data structure.
 *
 * @author Kevin Kong
 * @version May 30, 2011
 *
 */
class DoublyListNode<E> {
    //creates instance variables
    private E data; //data to be held
    public DoublyListNode next; //reference to the next node
    public DoublyListNode prev; //reference to the prev node

    /**
     * This is a constructor for the class, which takes in a String parameter.
     * This constructor will call the overloaded constructor with all the
     * references and data of the node to create a DoublyListNode.
     *
     * @param nodeValue The String data to be held by the node.
     */
    public DoublyListNode (E nodeValue) {
        //calls the overloaded constructor to create the node
        this( nodeValue,null, null);
    }

    /**
     * This is the overloaded constructor which takes in two references values as well
     * as in a String parameter.  This constructor will then set the instance variables to
     * the values as specified by the parameters.
     *
     * @param nodeBefore A reference to the prev node.
     * @param nodeValue The String data to be held by the node.
     * @param nodeNext A reference to the next node.
     */
    public DoublyListNode ( E nodeValue,DoublyListNode nodeBefore, DoublyListNode nodeNext) {
        data = nodeValue;
        next = nodeNext;
        prev = nodeBefore;
    }

    /**
     * This is the accessor method for the instance variable next. This method takes no
     * parameters and returns the reference held by the current node object to the next.
     *
     * @return The reference to the next node.
     */
    public DoublyListNode getNext () {
        return next;
    }

    /**
     * This is the accessor method for the instance variable prev. This method takes no
     * parameters and returns the reference held by the current node object to the prev.
     *
     * @return The reference to the next node.
     */
    public DoublyListNode getPrev() {
        return prev;
    }

    /**
     * This is the accessor method for the instance variable data. This method takes no
     * parameters and returns the String data of the current node object.
     *
     * @return The String object held by the class.
     */
    public E getData() {
        return data;
    }

    /**
     * This is the mutator method for the instance variable next.  This method takes in a
     * DoublyListNode parameter and assigns the reference to the instance variable next.
     *
     * @param newNext The new reference to be assigned to the instance variable next.
     */
    public void setNext( DoublyListNode newNext ) {
        next = newNext;
    }

    /**
     * This is the mutator method for the instance variable prev.  This method takes in a
     * DoublyListNode parameter and assigns the reference to the instance variable prev.
     *
     * @param newPrevious The new reference to be assigned to the instance variable next.
     */
    public void setPrev(DoublyListNode newPrevious) {
        prev = newPrevious;
    }


    /**
     * This is the mutator method for the instance variable data.  This method takes in a
     * String parameter and assigns the data of that parameter to the instance variable data.
     *
     * @param newValue The new data to be assigned to the instance variable data.
     */
    public void setData(E newValue) {
        data = newValue;
    }
}