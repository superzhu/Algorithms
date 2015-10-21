package util;

/**
 * 完美洗牌算法-- https://github.com/julycoding/The-Art-Of-Programming-By-July/blob/master/ebook/zh/02.09.md
 * 18.2(Cracking) -- Write a method to shuffle a deck of cards. It must be a perfect shuffle—in other
 * words, each of the 52! permutations of the deck has to be equally likely. Assume that
 * you are given a random number generator which is perfect
 */
public class CardShuffle {
    /* Random number between lower and higher, inclusive */
    public static int rand(int lower, int higher) {
        return lower + (int)(Math.random() * (higher - lower + 1));
    }

    public static int[] shuffleArrayRecursively(int[] cards, int i) {
        if (i == 0) {
            return cards;
        }

		/* shuffle elements 0 through index - 1 */
        shuffleArrayRecursively(cards, i - 1);
        int k = rand(0, i);

		/* Swap element k and index */
        int temp = cards[k];
        cards[k] = cards[i];
        cards[i] = temp;

		/* Return shuffled array */
        return cards;
    }

    public static void shuffleArrayInteratively(int[] cards) {
        for (int i = 0; i < cards.length; i++) {
            int k = rand(0, i);
            int temp = cards[k];
            cards[k] = cards[i];
            cards[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] cards = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        //System.out.println(AssortedMethods.arrayToString(cards));
        shuffleArrayInteratively(cards);
        //System.out.println(AssortedMethods.arrayToString(cards));
    }
}
