package maths;

/**
 * greatest common divisor
 */
public class GCD {
    static int gcd(int a, int b) {
        while(a!=0 && b!=0) {// until either one of them is 0
            int c = b;
            b = a%b;
            a = c;
        }
        return a+b; // either one is 0, so return the non-zero value
    }

    /**
     * Design an ecient algorithm for computing the GCD of two numbers
     * without using multiplication, division or the modulus operators
     */
    public static long elementaryGCD(long x, long y) {
        if (x == 0) {
            return y;
        } else if (y == 0) {
            return x;
        } else if ((x & 1) == 0 && (y & 1) == 0) { // x and y are even.
            return elementaryGCD(x >> 1, y >> 1) << 1;
        } else if ((x & 1) == 0 && (y & 1) != 0) { // x is even, y is odd.
            return elementaryGCD(x >> 1, y);
        } else if ((x & 1) != 0 && (y & 1) == 0) { // x is odd, y is even.
            return elementaryGCD(x, y >> 1);
        } else if (x > y) { // Both x and y are odd, and x > y.
            return elementaryGCD(x - y, y);
        }
        return elementaryGCD(x, y - x); // Both x and y are odd, and x <= y.
    }
}
