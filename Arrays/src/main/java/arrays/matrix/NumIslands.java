package arrays.matrix;

/**
 * Given a 2-d grid map of '1's (land) and '0's (water), count the number of islands. An island is
 * surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may
 * assume all four edges of the grid are all surrounded by water.
 * http://www.programcreek.com/2014/04/leetcode-number-of-islands-java/
 */
public class NumIslands {
    /**
     * The idea is to count all top-leftmost corners of given matrix. We can check
     * if a ‘X’ is top left or not by checking following conditions.
     *   1) A ‘X’ is top of rectangle if the cell just above it is a ‘O’
     *   2) A ‘X’ is leftmost of rectangle if the cell just left of it is a ‘O’
     */
    static int countIslands(char[][] mat) {
        if(mat == null)
            return 0;

        int rows = mat.length;
        int cols = mat[0].length;

        int count = 0; // Initialize result

        // Traverse the input matrix
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                // If current cell is 'X', then check
                // whether this is top-leftmost of a
                // rectangle. If yes, then increment count
                if (mat[i][j] == 'X') {
                    if ((i == 0 || mat[i-1][j] == 'O') &&
                            (j == 0 || mat[i][j-1] == 'O'))
                        count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        char[][] mat =  {{'O', 'O', 'O'},
                         {'X', 'X', 'O'},
                         {'X', 'X', 'O'},
                         {'O', 'O', 'X'},
                         {'O', 'O', 'X'},
                         {'X', 'X', 'O'} };//expected: 3
        System.out.println("Island numbers = " + NumIslands.countIslands(mat));

        char[][] mat2 =  {{'X', 'O', 'O', 'O', 'O', 'O'},
                          {'X', 'O', 'X', 'X', 'X', 'X'},
                          {'O', 'O', 'O', 'O', 'O', 'O'},
                          {'X', 'X', 'X', 'O', 'X', 'X'},
                          {'X', 'X', 'X', 'O', 'X', 'X'},
                          {'O', 'O', 'O', 'O', 'X', 'X'} };//expected: 4
        System.out.println("Island numbers = " + NumIslands.countIslands(mat2));

        char[][] mat3 = {
                {'X','X','X','X','O'},
                {'X','X','O','X','O'},
                {'X','X','O','O','O'},
                {'O','O','O','O','O'}};//expected: 1
        System.out.println("Island numbers = " + NumIslands.countIslands(mat3));
    }
}
