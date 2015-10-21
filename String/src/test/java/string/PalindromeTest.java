package string;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PalindromeTest {
    @Test
    public void isPalindromeV1() {
        boolean flag = Palindrome.isPalindromeV1("this is not a palindrome.");
        assertThat(flag, is(Boolean.FALSE));

        flag = Palindrome.isPalindromeV1("madam");
        assertThat(flag, is(Boolean.TRUE));

        flag = Palindrome.isPalindromeV1("我爱我");
        assertThat(flag, is(Boolean.TRUE));
    }
}
