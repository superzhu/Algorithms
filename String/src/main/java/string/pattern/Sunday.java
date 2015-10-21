package string.pattern;

/**
 * http://wangyifu.github.io/myblog/2014/11/15/strStr-Sunday-Algorithm/
 * http://www.programering.com/a/MzM0UDMwATI.html
 * 只不过Sunday算法是从前往后匹配，在匹配失败时关注的是文本串中参加匹配的最末位字符的下一位字符。
 *   如果该字符没有在模式串中出现则直接跳过，即移动位数 = 匹配串长度 + 1；
 *   否则，其移动位数 = 模式串中最右端的该字符到末尾的距离+1。
 */
public class Sunday {
    public int strStr(String haystack, String needle) {
        if(needle==null || needle.length()==0) return 0;
        if(haystack == null || needle.length() > haystack.length()) return -1;

        char[] mainArr = haystack.toCharArray();
        char[] targetArr = needle.toCharArray();
        int i = 0;

        while(i<mainArr.length){
            int j = 0;
            while(j < targetArr.length && i+j < mainArr.length && mainArr[i+j] == targetArr[j]){ //match
                j++;
            }
            if(j == targetArr.length) return i;
            else { //shift
                if(i+targetArr.length < mainArr.length){
                    for(j = targetArr.length-1; j >= 0; j--){
                        if(targetArr[j] == mainArr[i+targetArr.length]){
                            break;
                        }
                    }
                }
                i += targetArr.length-j;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String text = "abacadabrabracabracadabrabrabracad";
        String pattern = "abracadabra";

        Sunday su = new Sunday();
        int index = su.strStr(text, pattern);
        System.out.println("Pattern: " + index);
    }
}
