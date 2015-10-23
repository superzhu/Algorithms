package stack;

import java.util.LinkedList;

/**
 * Implement Stack using Queues (Java)
 * http://www.programcreek.com/2014/06/leetcode-implement-stack-using-queues-java/
 */
public class StackByQueue {
    LinkedList<Integer> queue1 = new LinkedList<Integer>();
    LinkedList<Integer> queue2 = new LinkedList<Integer>();

    // Push element x onto stack.
    public void push(int x) {
        if(empty()){
            queue1.offer(x);
        }else{
            if(queue1.size()>0){
                queue2.offer(x);
                int size = queue1.size();
                while(size>0){
                    queue2.offer(queue1.poll());
                    size--;
                }
            }else if(queue2.size()>0){
                queue1.offer(x);
                int size = queue2.size();
                while(size>0){
                    queue1.offer(queue2.poll());
                    size--;
                }
            }
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        if(queue1.size()>0){
            queue1.poll();
        }else if(queue2.size()>0){
            queue2.poll();
        }
    }

    // Get the top element.
    public int top() {
        if(queue1.size()>0){
            return queue1.peek();
        }else if(queue2.size()>0){
            return queue2.peek();
        }
        return 0;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue1.isEmpty() & queue2.isEmpty();
    }

    public static void main(String[] args) {
        StackByQueue instance = new StackByQueue();
        instance.push(1);
        instance.push(2);
        instance.push(3);

        System.out.println("Exiting...");
    }
}
