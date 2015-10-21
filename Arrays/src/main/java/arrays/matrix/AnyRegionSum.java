package arrays.matrix;

/**
 * http://www.ardendertat.com/2011/09/20/programming-interview-questions-2-matrix-region-sum/
 * Given a matrix of integers and coordinates of a rectangular region
 * within the matrix, find the sum of numbers falling inside the rectangle. Our program will
 * be called multiple times with different rectangular regions from the same matrix
 */
public class AnyRegionSum {
    private int[][] sum;

    private  void preComputeSum(int[][] matrix){
        int row = matrix.length;
        int col = matrix[0].length;

        sum = new int[row][col];

        sum[0][0] = matrix[0][0];
        //for first row
        for(int j = 1; j < col; j++)
           sum[0][j] = sum[0][j-1] + matrix[0][j];
       //for first column
        for(int i=1; i<row;i++)
           sum[i][0] = sum[i-1][0] + matrix[i][0];

        //for all other rows and columns
        for(int i=1;i<row;i++)
            for(int j=1;j<col;j++)
                sum[i][j] = matrix[i][j]-sum[i-1][j-1]+sum[i-1][j]+sum[i][j-1];
    }
}
