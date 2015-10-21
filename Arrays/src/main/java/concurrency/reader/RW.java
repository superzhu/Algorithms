package concurrency.reader;

import java.util.Date;
import java.util.Random;

/**
 * Reader--Writer Problem
 *   This motivates the first reader-writers problem: protest s with the
 * added constraint that no reader is to be kept waiting if s is currently
 * opened for reading.
 */
public class RW {
    static String data = new Date().toString();
    static Random random = new Random();

    static Object LR = new Object();
    static int readCount = 0;
    static Object LW = new Object();

    public static void main( String [] args ) {
        Thread r0 = new Reader("r0");
        Thread r1 = new Reader("r1");
        Thread w0 = new Writer("w0");
        Thread w1 = new Writer("w1");
        r0.start(); r1.start();
        w0.start(); w1.start();
        try { Thread.sleep(10000); } catch (Exception e) { e.printStackTrace(); }
        System.exit(0);
    }
}
