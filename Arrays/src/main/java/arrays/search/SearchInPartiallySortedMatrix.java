package arrays.search;

/**
 * In a 2-D matrix, every row is increasingly sorted from left to right, and every column is
 * increasingly sorted from top to bottom. Please implement a function to check whether a number is in such a
 * matrix or not. For example, all rows and columns are increasingly sorted in the following matrix. It returns true if it
 * tries to find number 7, but it returns false if it tries to find number 5.
 *
 * 1 2 8 9
 * 2 4 9 12
 * 4 7 10 13
 * 6 8 11 15
 */
public class SearchInPartiallySortedMatrix {
    public static boolean searchOnDiagonal(int matrix[][], int value) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        return findCore(matrix, value, 0, 0, rows - 1, cols - 1);
    }

    static boolean findCore(int matrix[][], int value, int row1, int col1, int row2, int col2) {
        if (value < matrix[row1][col1] || value > matrix[row2][col2])
            return false;

        if (value == matrix[row1][col1] || value == matrix[row2][col2])
            return true;

        int copyRow1 = row1, copyRow2 = row2;
        int copyCol1 = col1, copyCol2 = col2;

        int midRow = (row1 + row2) / 2;
        int midCol = (col1 + col2) / 2;

        // find the last element less than value on diagonal
        while ((midRow != row1 || midCol != col1)
                && (midRow != row2 || midCol != col2)) {
            if (value == matrix[midRow][midCol])
                return true;

            if (value < matrix[midRow][midCol]) {
                row2 = midRow;
                col2 = midCol;
            } else {
                row1 = midRow;
                col1 = midCol;
            }

            midRow = (row1 + row2) / 2;
            midCol = (col1 + col2) / 2;
        }

        // find value in two sub-matrices
        boolean found = false;
        if(midRow < matrix.length - 1)
            found = findCore(matrix, value, midRow + 1, copyCol1, copyRow2, midCol);
        if(!found && midCol < matrix[0].length - 1)
            found = findCore(matrix, value, copyRow1, midCol + 1, midRow, copyCol2);
        return found;
    }

    public static boolean find_solution2(int matrix[][], int value){
        boolean found = false;
        int row = 0;
        int col = matrix[0].length - 1;

        while(row < matrix.length && col >= 0){
            if(matrix[row][col] == value){
                found = true;
                break;
            }

            if(matrix[row][col] > value)
                --col;
            else
                ++row;
        }

        return found;
    }

}
