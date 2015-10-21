package concurrency.reader;

import java.math.BigInteger;
import java.util.Random;

class Task {
    static Random r = new Random();
    static void doSomeThingElse() {
        BigInteger b = BigInteger.probablePrime(512, r);
        System.out.println(" identified a big prime: " + (b.mod(BigInteger.TEN)));
        try {
            Thread.sleep(r.nextInt(1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
