package string;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ManacherTest {

    @Test
    public void longestSubPalindrom() {
        Manacher manacher = new Manacher("babcbabcbaccba");
        assertEquals("abcbabcba",manacher.longestPalindromicSubstring());

        Manacher manacher2 = new Manacher("abaaba");
        assertEquals("abaaba",manacher2.longestPalindromicSubstring());

        Manacher manacher3 = new Manacher("abababa");
        assertEquals("abababa",manacher3.longestPalindromicSubstring());

        Manacher manacher4 = new Manacher("forgeeksskeegfor");
        assertEquals("geeksskeeg",manacher4.longestPalindromicSubstring());
    }
}
