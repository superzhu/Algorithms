package search.dp.arrays;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * You have a stack of n boxes, with widths w, heights h and depths d. The boxes
 * cannot be rotated and can only be stacked on top of one another if each box in the
 * stack is strictly larger than the box above it in width, height, and depth. Implement
 * a method to build the tallest stack possible, where the height of a stack is the sum of
 * the heights of each box.
 *
 * https://ziliangzhu.wordpress.com/2013/11/16/you-have-a-stack-of-n-boxes-with-widths-w-heights-l-and-depths-dr-the-boxes-cannot-be-rotated-and-can-only-be-stacked-on-top-of-one-another-if-each-box-in-the-stack-is-strictly-larger-than-the-bo/
 */
public class BoxStack {
    static class Box {
        double width;
        double height;
        double depth;

        public Box(double w, double h, double d) {
            width = w;
            height = h;
            depth = d;
        }

        boolean isSmallerThan(Box theBox) {
            if (theBox == null) {
                return true;
            }
            if (width < theBox.width && height < theBox.height
                    && depth < theBox.depth) {
                return true;
            } else {
                return false;
            }
        }

        public String toString(){
            return  "("+width+", "+height+", "+depth+")";
        }
    }

    public ArrayList<Box> getTallestBoxStack(Box[] boxes) {
        HashMap<Box, ArrayList<Box>> cache = new HashMap<Box, ArrayList<Box>>();
        return getTallestBoxStack(cache, boxes, null);
    }

    private ArrayList<Box> getTallestBoxStack(
            HashMap<Box, ArrayList<Box>> myHash, Box[] boxes, Box bottom) {
        // Base and Recursion
        // Check hashmap
        if (bottom != null && myHash.containsKey(bottom)) {
            return myHash.get(bottom);
        } else {
            // Temporary instance
            double previousHighestHeight = 0;

            // To return value
            ArrayList<Box> previousFinal = new ArrayList<Box>();

           // Recursion
            for (int i = 0; i < boxes.length; i++) {
                // If any box is smaller than current box
                if (boxes[i].isSmallerThan(bottom)) {
                    ArrayList<Box> previous = getTallestBoxStack(myHash, boxes, boxes[i]);
                    // Compare height and update the final previous
                    double previousHeight = getStackHeight(previous);
                    if (previousHeight > previousHighestHeight) {
                        // update height record
                        previousHighestHeight = previousHeight;
                        previousFinal = previous;
                    }
                }
            }

            // Ready to return
            if (bottom != null){
                previousFinal.add(0,bottom);
                // update hashmap
                myHash.put(bottom, previousFinal);
            }

             // return a deep copy
            return new ArrayList<Box>(previousFinal);
        }
    }

    private double getStackHeight(ArrayList<Box> boxes) {
        double returnVal = 0;
        for (Box i : boxes) {
            returnVal += i.height;
        }
        return returnVal;
    }

    public static void main(String[]args) {
        Box[] boxes = new Box[]{
                    new Box(1, 7, 4),new Box(2, 6, 9),new Box(4, 9, 6),
                    new Box(10, 12, 8),new Box(6, 2, 5),new Box(3, 8, 5),
                            new Box(5, 7, 7),new Box(2, 10, 16),new Box(12, 15, 9)
        };

        BoxStack instance = new BoxStack();
        ArrayList<Box> result = instance.getTallestBoxStack(boxes);
        System.out.println(result);
    }
}
