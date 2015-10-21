package string.pattern;

/**
 * http://blog.csdn.net/v_july_v/article/details/7041827
 */
public class KMPSearch {
    private static int[] getNextVal(String pattern) {
        int pLen = pattern.length();
        int[] next = new int[pLen];
        next[0] = -1;

        int k = -1;
        int j = 0;
        while (j < pLen - 1) {
            //p[k]表示前缀，p[j]表示后缀
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                ++j;
                ++k;
                //较之前next数组求法，改动在下面4行
                if (pattern.charAt(j) != pattern.charAt(k))
                    next[j] = k;   //之前只有这一行
                else
                    //因为不能出现p[j] = p[ next[j ]]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
                    next[j] = next[k];
            } else {
                k = next[k];
            }
        }

        return next;
    }

    public static int kmpMatch(String str, String pattern) {
        int[] next = KMPSearch.getNextVal(pattern);

        int i = 0;
        int j = 0;
        int sLen = str.length();
        int pLen = pattern.length();

        while (i < sLen && j < pLen) {
            //①如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || str.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                //②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                //next[j]即为j所对应的next值
                j = next[j];
            }
        }

        if (j == pLen)
            return i - j;
        else
            return -1;
    }

    public static void main(String[] args) {
        int index = KMPSearch.kmpMatch("abacababc","abab");

        System.out.println("done.." +index);
    }
}
