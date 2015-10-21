package string;

/**
 * 字符串包含
 * 链接：https://github.com/julycoding/The-Art-Of-Programming-By-July/blob/master/ebook/zh/01.02.md
 */
public class StringContain {
    public static boolean hashCheck(char[] s1, char[] s2) {
        int mask = 0;
        for (char c: s1) {
            mask = mask | (1 << (c - 'A'));
        }
        for (char c: s2) {
            if ((mask & (1 << (c - 'A'))) == 0) {
                return false;
            }
        }
        return true;
    }
}
