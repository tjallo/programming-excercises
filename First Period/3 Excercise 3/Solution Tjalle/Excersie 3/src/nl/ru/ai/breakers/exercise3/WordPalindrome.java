package nl.ru.ai.breakers.exercise3;

import java.util.Scanner;

public class WordPalindrome {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Word: ");

        String word = scanner.nextLine();

        checkIfPalindrome(word);

        scanner.close();

    }

    public static void checkIfPalindrome(String word) {

        int wordLen = word.length();
        Boolean palindrome = true;

        for (int i = 0; i < wordLen / 2; i++) {

            int corrospondingI = wordLen - i - 1;

            Character letterAtPos = word.charAt(i);
            Character oppositeLetterAtPos = word.charAt(corrospondingI);

            if (!(letterAtPos == oppositeLetterAtPos)) {
                palindrome = false;
            }
        }

        System.out.println(palindrome);

    }

}
