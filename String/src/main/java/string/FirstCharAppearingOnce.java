package string;

/**
 * Implement a function to find the first character in a stream that only appears once at any time
 * while reading the stream.
 *
 * For example, when the first two characters “go” are read from a stream, the first character which appears once is
 * the character ‘g’. When the first six characters “google” are read, the first character appearing only once is ‘l’.
 */
public class FirstCharAppearingOnce {
    // occurrence[i]: A character with ASCII value i;
    // occurrence[i] = -1: The character has not found;
    // occurrence[i] = -2: The character has been found for mutlple times
    // occurrence[i] >= 0: The character has been found only once
    private int[] occurrence;//[256];
    private int index = 0;

    public FirstCharAppearingOnce() {
        occurrence = new int[256];
        for(int i = 0; i < 256; ++i)
            occurrence[i] = -1;
    }

    void insert(char ch) {
        if(occurrence[ch] == -1)
            occurrence[ch] = index;
        else if(occurrence[ch] >= 0)
            occurrence[ch] = -2;

        index++;
    }

    char firstAppearingOnce() {
        char ch = '\0';
        int minIndex = Integer.MAX_VALUE;
        for(int i = 0; i < 256; ++i) {
            if(occurrence[i] >= 0 && occurrence[i] < minIndex) {
                ch = (char)i;
                minIndex = occurrence[i];
            }
        }

        return ch;
    }

    public static void main(String[] args) {
        FirstCharAppearingOnce once = new FirstCharAppearingOnce();
        once.insert('g');
        once.insert('o');
        once.insert('o');
        once.insert('g');
        once.insert('l');
        once.insert('e');
        System.out.println("First appearing character: " + once.firstAppearingOnce());
    }
}
