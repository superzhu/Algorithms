package basic.operations;

/**
 * Common and basic bit manipulation operations.
 *
 * Java does bitwise operators on integers, so be aware!
 * http://fahdshariff.blogspot.jp/2010/09/java-bit-twiddling.html
 */
public class BitTwiddling {
    /**
     * Checking if an integer is even or odd.
     * If an integer is odd, its rightmost bit will always
     *   be 1 and if it is even, it will always be 0
     */
    public static boolean isEven(int a){
        return (a & 1) == 0;
    }

    /**
     *  Is an integer positive or negative?
     */
    public static boolean isNegative(int a){
        return (a >> 31) != 0;
    }

    /**
     * Negating an integer
     * The basic idea is you invert all the bits and then add 1.
     */
    public static int negate(int a){
        return (~a) + 1;
    }

    /**
     * Check if the nth bit is set
     */
    public boolean isBitSet(int a, int n){
        return (a & (1 << n) ) != 0;

        //Setting the nth bit -- a | (1<<n)
        //Unsetting the nth bit -- a & ~(1<<n)
        //Toggling the nth bit -- a ^ (1<<n)
        //Unsetting the rightmost 1-bit -- a & (a-1)
        //Checking if number is a power of 2 --  (a & (a-1)) == 0

        //Turn off the rightmost 1-bit -- y = x & (x-1)
        //Isolate the rightmost 1-bit -- y = x & (-x)
        //Isolate the rightmost 0-bit -- y = ~x & (x+1)
        //Turn on the rightmost 0-bit -- y = x | (x+1)
    }

    public int swapBits(int n, int i, int j) {
        int a = (n >> i) & 1;
        int b = (n >> j) & 1;

        if ((a ^ b) != 0) {
            return n ^= (1 << i) | (1 << j);
        }

        return n;
    }

    /**
     * To get the number of 1s in the binary representation of an integer
     */
    public static int numberOf1s(int val) {
        //Left-Shift Operation on 1
        int count = 0;
        int flag =1;
        while( flag != 0) {
            if( (val & flag) != 0)
                count++;

            flag = flag << 1;
        }

        return count;
    }

    public static int numberOf1s2(int val) {
        int count = 0;

        while(val != 0) {
            count++;
            val = (val & (val-1));//turn off rightmost 1
        }
        return count;
    }

    /**
     * Return the number of bits that will need to
     * be changed in order to convert an integer, X,
     * into another integer, Y and vice versa
     */
    public static int findNumberOfBits(int x, int y) {
        int z = x ^ y;  //XOR x and y
        int bitCount = 0;

        bitCount = numberOf1s2(z);
        return bitCount;
    }

    /**
     * To clear all bits from the most significant bit through i(inclusive)
     */
    public static int clearBitsMSBthroughI(int num, int i) {
        int mask = (1<<i) -1;
        return num & mask;
    }

    /**
     * To clear all bits from i through 0(inclusive)
     */
    public static int clearBitsIthough0(int num, int i) {
        int mask = ~((1 << (i+1)) -1);
        return num & mask;
    }

    public static void main(String[] args) {
        System.out.println("Positive Number =" + BitTwiddling.findNumberOfBits(12,16));
        System.out.println("Negative Number =" + BitTwiddling.numberOf1s2(-2));
    }
}
