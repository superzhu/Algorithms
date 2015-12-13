package util;

/**
 * http://fushan.blog.51cto.com/9723472/1673080
 * http://fushan.blog.51cto.com/9723472/
 * A 2D Cartesian plane is given. A lattice point in this plane is a point whose coordinates are integers.
 * All lattice points of the plane are arraged into a spiral as follows:
 *    point (0, 0) is point number 0;
 *    point (0, 1) is point number 1;
 *    point (1, 1) is point number 2;
 *    thereafter the points are (1, 0), (1, -1), (0, -1),  (-1, -1),
 *    (-1, 0), (-1, 1), (-1, 2), (0, 2), (1, 2), (2, 2), (2, 1) etc.
 *
 *    return the spiral coordinate of the lattice point (X, Y).
 *    Get spiral index from location
 */
public class SpiralIndex {
    //http://stackoverflow.com/questions/9970134/get-spiral-index-from-location
    //http://stackoverflow.com/questions/3706219/algorithm-for-iterating-over-an-outward-spiral-on-a-discrete-2d-grid-from-the-or
    public int solution2(int X, int Y) {
        int index = 0;
        /**
         * Description: Left-upper semi-diagonal (0-4-16-36-64) contains squared layer number (4 * layer^2).
         * External if-statement defines layer and finds (pre-)result for position in corresponding row or
         * column of left-upper semi-plane, and internal if-statement corrects result for mirror position.
         */
        if(X*X >= Y*Y) {
            index = 4 * X * X - X - Y;
            if(X < Y)
                index = index - 2 * (X - Y);
        } else {
            index = 4 *Y*Y -X - Y;
            if(X < Y)
                index = index + 2 * (X - Y);
        }

        return index;
    }

    public int solution(int X, int Y) {
        int latticePointIdx = 0;
        int currentX = 0, currentY = 0;
        int[] next;

        while (currentX != X || currentY != Y) {
            next = moveToNext(currentX, currentY);
            currentX = next[0];
            currentY = next[1];
            if (Math.abs(currentX) > 20000 || Math.abs(currentY) > 20000)
                return -1;

            latticePointIdx++;
        }

        return latticePointIdx;
    }

    private int[] moveToNext(int X, int Y) {
        int[] nextPoint = new int[2];

        if (X <= 0 && Y >= 0) {
            if (Math.abs(X) < Math.abs(Y)) {
                nextPoint[0] = X + 1;
                nextPoint[1] = Y;
            } else {
                nextPoint[0] = X;
                nextPoint[1] = Y + 1;
            }
        } else if (X >= 0 && Y >= 0) {
            if (Math.abs(X) < Math.abs(Y)) {
                nextPoint[0] = X + 1;
                nextPoint[1] = Y;
            } else {
                nextPoint[0] = X;
                nextPoint[1] = Y - 1;
            }
        } else if (X >= 0 && Y <= 0) {
            if (Math.abs(X) <= Math.abs(Y)) {
                nextPoint[0] = X - 1;
                nextPoint[1] = Y;
            } else {
                nextPoint[0] = X;
                nextPoint[1] = Y - 1;
            }
        } else if (X <= 0 && Y <= 0) {
            if (Math.abs(X) < Math.abs(Y)) {
                nextPoint[0] = X - 1;
                nextPoint[1] = Y;
            } else {
                nextPoint[0] = X;
                nextPoint[1] = Y + 1;
            }
        }

        return nextPoint;
    }

    //http://stackoverflow.com/questions/398299/looping-in-a-spiral
    public static void Spiral(int X, int Y) {
        int x=0, y=0, dx = 0, dy = -1;
        int t = Math.max(X,Y);
        int maxI = t*t;

        for (int i=0; i < maxI; i++){
            if ((-X/2 <= x) && (x <= X/2) && (-Y/2 <= y) && (y <= Y/2)) {
                System.out.println(x+","+y);
                //DO STUFF
            }

            if( (x == y) || ((x < 0) && (x == -y)) || ((x > 0) && (x == 1-y))) {
                t=dx; dx=-dy; dy=t;
            }
            x+=dx; y+=dy;
        }
    }

    public static void main(String[] args) {
        SpiralIndex instance = new SpiralIndex();
        System.out.println(instance.solution(1,1));

        Spiral(3,3);
    }
}
