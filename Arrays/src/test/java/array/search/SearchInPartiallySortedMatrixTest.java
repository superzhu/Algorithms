package array.search;

import arrays.search.SearchInPartiallySortedMatrix;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SearchInPartiallySortedMatrixTest {

    @Test
    public void searchOnDiagonal() {
        System.out.println("running searchOnDiagonal...");
        int[][] multi = new int[][]{
                { 1,2,8,9 },
                { 2,4,9,12 },
                { 4,7,10,13 },
                { 6,8,11,15 }
        };

        boolean flag = SearchInPartiallySortedMatrix.searchOnDiagonal(multi,7);

        assertThat(flag, is(Boolean.TRUE));
    }
}
