package concurrency.barber;

import java.util.Random;

public class Customer extends Thread {
    private String name; 			// The name/id of this customer
    private BarberShop shop; 		// The BarberShop instance associated with this customer
    private Random r;				// A random number generator
    private boolean wants_haircut;	// indicates if the customer wants a haircut

    /**
     * Default constructor.
     * @param shop BarberShop instance to use
     * @param name an unique name for this customer
     */
    public Customer(BarberShop shop, String name) {
        this.name = name;
        this.shop = shop;
        wants_haircut = true;
        r = new Random();
    }


    public void run() {
        wasteTime();
        shop.customerReady(this);
    }

    /**
     * Check if the customer still wants a haircut.
     *
     * @return true if the customer wants a haircut, fals otherwise
     */
    public boolean wantsHaircut() {
        return wants_haircut;
    }

    /**
     * Indicate that the customer wants to leave. This will cause
     * wantsHaircut() to return false from now on.
     */
    public void wantsToLeave() {
        wants_haircut = false;
    }

    /**
     * The customer will idle for a random time period. This ensures that
     * the customers arrive at unpredictable times, or can arbitrarily wait
     * at different times.
     */
    public void wasteTime() {
        try {
            this.sleep(Math.abs(r.nextInt(100000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return name;
    }
}
