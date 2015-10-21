package string;

import java.util.HashMap;

/**
 * Longest Substring Which Contains 2 Unique Characters
 * Given a string, find the longest substring that contains only two unique characters. For example,
 * given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique character is
 * "bcbbbbcccb".
 */
public class SubStringKUniqueChars {
    public static String maxSubStringKUniqueChars(String s, int k) {
        //declare a counter
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int start = 0;
        int maxLen = 0;
        String maxSubstring = null;

        for (int i = 0; i < s.length(); i++) {
            //add each char to the counter
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }

            if(map.size() == k+1){
                //get maximum
                int range = i-start;
                if(range > maxLen){
                    maxLen = range;
                    maxSubstring = s.substring(start, i);
                }

                //move left cursor toward right, so that substring contains only k chars
                while(map.size()>k){
                    char first = s.charAt(start);
                    int count = map.get(first);
                    if(count>1){
                        map.put(first,count-1);
                    }else{
                        map.remove(first);
                    }
                    start++;
                }
            }
        }

        if (map.size() == k && maxLen == 0) {
            return s;
        }

        return maxSubstring;
    }

    public static void main(String[] args) {
        String text = "abcbbbbcccbdddadacb";
        String result = maxSubStringKUniqueChars(text,2);//expect: bcbbbbcccb

        System.out.println("Max substring = " + result);
    }
}
