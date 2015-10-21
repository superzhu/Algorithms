package string;

/**
 * 在一个字符串中找到第一个只出现一次的字符。如输入abaccdeff，则输出b。
 *
 * http://blog.csdn.net/ns_code/article/details/27106997
 */
public class FirstAppearChar {
    public static char firstOne(String s) {
        char result = '0';
        char temp;
        int[] num = new int[52];
        for (int i = 0; i < s.length(); i ++) {
            temp = s.charAt(i);
            if ( temp >= 'a' && temp <= 'z' ) {
                num[temp - 'a']++;
            }
            else
            if (temp >= 'A' && temp <= 'Z') {
                num[temp - 'A' + 26] ++;
            }
        }

        for (int i = 0; i < num.length; i ++) {
            if (num[i] == 1) {
                if (i >= 0 && i <=26) {
                    result = (char)(i + 'a');
                } else
                    result = (char)(i - 26 + 'A');
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "google";

        char c = firstOne(s);
        System.out.println(c);

        char test = '朱';
        System.out.println(test + ",its int value="+(short)test);

        String am= "I work at oracle.";
        System.out.println(am.replaceAll(" ","%20"));
    }
}
