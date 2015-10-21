package concurrency.barber;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Sleeping Barber Problem
 * Consider a barber shop with a single barber B, one barber chair,
 * and n chairs for customers who are waiting for their turn for a
 * haircut. The barber sleeps in his chair when customers are not present.
 * On entering, a customer either awakens the barber or if the
 * barber is cutting someone else's hair, he sits down in one of
 * the chairs for waiting customers. If all of the waiting chairs are taken,
 * the newly arrived customer simply leaves
 *
 * https://vyatkins.wordpress.com/2013/12/21/sleeping-barber-problem/
 */
public class BlockingQueueSleepingBarber extends Thread {
    public static final int CHAIRS = 5;

    public static final long BARBER_TIME = 5000;

    private static final long CUSTOMER_TIME = 2000;

    public static final long OFFICE_CLOSE = BARBER_TIME * 2;

    public static BlockingQueue queue = new ArrayBlockingQueue(CHAIRS);

    class Customer extends Thread {
        int iD;
        boolean notCut = true;
        BlockingQueue queue = null;

        public Customer(int i, BlockingQueue queue) {
            iD = i;
            this.queue = queue;
        }

        public void run() {
            while (true) { // as long as the customer is not cut he is in the queue or if not enough sits he is out
                try {
                    this.queue.add(this.iD);

                    this.getHaircut(); // take a sit
                } catch (IllegalStateException e) {
                    System.out.println("There are no free seats. Customer "
                            + this.iD + " has left the barbershop.");
                }
                break;
            }
        }

        // take a seat
        public void getHaircut() {
            System.out.println("Customer " + this.iD + " took a chair");
        }

    }

    class Barber extends Thread {
        BlockingQueue queue = null;
        public Barber(BlockingQueue queue) {
            this.queue = queue;
        }

        public void run() {
            while (true) { // runs in an infinite loop

                try {
                    Integer i = (Integer) this.queue.poll(OFFICE_CLOSE, TimeUnit.MILLISECONDS);
                    if (i==null) break; // barber slept for long time (OFFICE_CLOSE) no more clients in the queue - close office
                    this.cutHair(i); // cutting...

                } catch (InterruptedException e) {

                }
            }
        }

        // simulate cutting hair
        public void cutHair(Integer i) {
            System.out.println("The barber is cutting hair for customer #" + i);
            try {
                sleep(BARBER_TIME);
            } catch (InterruptedException ex) {
            }
        }
    }

    public static void main(String args[]) {

        BlockingQueueSleepingBarber barberShop = new BlockingQueueSleepingBarber();
        barberShop.start(); // Let the simulation begin
    }

    public void run() {
        Barber giovanni = new Barber(BlockingQueueSleepingBarber.queue);
        giovanni.start();

        //  create new customers
        for (int i = 1; i < 16; i++) {
            Customer aCustomer = new Customer(i, BlockingQueueSleepingBarber.queue);
            aCustomer.start();
            try {
                sleep(CUSTOMER_TIME);
            } catch (InterruptedException ex) {};
        }
    }
}