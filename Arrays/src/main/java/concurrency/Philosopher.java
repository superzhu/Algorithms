package concurrency;

/**
 * In the famous dining philosophers problem, a bunch of philosophers are sitting
 * around a circular table with one chopstick between each of them. A philosopher
 * needs both chopsticks to eat, and always picks up the left chopstick before the right
 * one. A deadlock could potentially occur if all the philosophers reached for the left
 * chopstick at the same time. Using threads and locks, implement a simulation of the
 * dining philosophers problem that prevents deadlocks.
 */
public class Philosopher extends Thread {
    private final int maxPause = 100;
    private int bites = 10;

    private Chopstick left;
    private Chopstick right;
    private int index;
    public Philosopher(int i, Chopstick left, Chopstick right) {
        index = i;
        this.left = left;
        this.right = right;
    }

    public void eat() {
        System.out.println("Philosopher " + index + ": start eating");
        if (pickUp()) {
            chew();
            putDown();
            System.out.println("Philosopher " + index + ": done eating");
        } else {
            System.out.println("Philosopher " + index + ": gave up on eating");
        }
    }

    public boolean pickUp() {
        pause();
        if (!left.pickUp()) {
            return false;
        }
        pause();
        if (!right.pickUp()) {
            left.putDown();
            return false;
        }
        pause();
        return true;
    }

    public void chew() {
        System.out.println("Philosopher " + index + ": eating");
        pause();
    }

    public void pause() {
        try {
            int pause = 15;//AssortedMethods.randomIntInRange(0, maxPause);
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putDown() {
        left.putDown();
        right.putDown();
    }

    public void run() {
        for (int i = 0; i < bites; i++) {
            eat();
        }
    }

    public static int size = 3;

    public static int leftOf(int i) {
        return i;
    }

    public static int rightOf(int i) {
        return (i + 1) % size;
    }

    public static void main(String[] args) {
        Chopstick[] chopsticks = new Chopstick[size + 1];
        for (int i = 0; i < size + 1; i++) {
            chopsticks[i] = new Chopstick();
        }

        Philosopher[] philosophers = new Philosopher[size];
        for (int i = 0; i < size; i++) {
            Chopstick left = chopsticks[leftOf(i)];
            Chopstick right = chopsticks[rightOf(i)];
            philosophers[i] = new Philosopher(i, left, right);
        }

        for (int i = 0; i < size; i++) {
            philosophers[i].start();
        }
    }
}
