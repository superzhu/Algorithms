package string.design;

/**
 * To design a system that takes big URLs and converts them into a short 6 character URL
 * http://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/
 */
public class URLShortener {

    public static String idToHash(int id) {
        char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int size = map.length;

        StringBuilder hash = new StringBuilder(10);

        while(id > 0) {
            hash.append(map[id%size]);
            id = id / size;
        }

        hash.reverse();
        return hash.toString();
    }

    public static int hashToId(char[] hash) {
        int id = 0;
        // A simple base conversion logic
        for (int i=0; i < hash.length; i++) {
            if ('a' <= hash[i] && hash[i] <= 'z')
                id = id*62 + hash[i] - 'a';
            if ('A' <= hash[i] && hash[i] <= 'Z')
                id = id*62 + hash[i] - 'A' + 26;
            if ('0' <= hash[i] && hash[i] <= '9')
                id = id*62 + hash[i] - '0' + 52;
        }
        return id;
    }

    public static void main(String[] args) {
        String hash = URLShortener.idToHash(123459);
        System.out.println(hash);

        System.out.println(URLShortener.hashToId(hash.toCharArray()));
    }
}

