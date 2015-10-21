package basic.operations;

import java.util.Random;

import static java.lang.Math.floor;
import static java.lang.Math.sqrt;

/**
 * 5.5 EPI --- The open doors problem
 *Five hundred closed doors along a corridor are numbered from 1 to 500. A person
 * walks through the corridor and opens each door. Another person walks through the
 * corridor and closes every alternate door. Continuing in this manner, the i-th person
 * comes and toggles the position of every i-th door starting from door i.
 */
public class Doors {
    /**
     * Solution: If the number of times a door’s state changes is odd, it will be open; otherwise it
     * is closed. Therefore, the number of times door k’s state changes equals the number
     * of divisors of k.
     */
    static boolean isDoorOpen(int i) {
        double sqrtI = sqrt(i);
        int floorSqrtI = (int)floor(sqrtI);
        return floorSqrtI * floorSqrtI == i;
    }

    static void checkAnswer(int n) {
        boolean[] doors = new boolean[n + 1]; // false means closed door.
        for (int i = 1; i <= n; ++i) {
            int start = 0;
            while (start + i <= n) {
                start += i;
                doors[start] = !doors[start];
            }
        }

        for (int i = 1; i <= n; ++i) {
            assert isDoorOpen(i) == doors[i];
        }
    }

    public static void main(String[] args) {
        Random gen = new Random();
        int n;
        if (args.length == 1) {
            n = Integer.valueOf(args[0]);
        } else {
            n = gen.nextInt(1000) + 1;
        }
        checkAnswer(n);
    }
}
