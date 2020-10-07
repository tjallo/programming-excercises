package nl.ru.ai.breakers.encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;

public class Encryption {

    public static void main(String[] args) {
        // write your code here

        // Ex. 5.1
        printBetween32and127();


        // Ex 5.2
        Random generator = new Random(4711);
        fileCryptor(false, "src/nl/ru/ai/breakers/encryption/testCodedWith4711.txt",
        "src/nl/ru/ai/breakers/encryption/Decrypted4711.txt", generator);


        // Ex 5.3
        int seed = codeCracker("src/nl/ru/ai/breakers/encryption/secret.txt", 15000);
        System.out.println("The seed was: " + seed);
        // Generate corresponding file to check answer
        Random crackedGenerator = new Random(seed);
        fileCryptor(false, "src/nl/ru/ai/breakers/encryption/secret.txt",
                "src/nl/ru/ai/breakers/encryption/DecryptedSecret.txt", crackedGenerator);

    }

    /**
     * Tries al random seeds between 0 and sampleSize
     * 
     * @param inputfile
     * @param sampleSize
     * @return returns the seed that has the most 'novel-like' characters (A-Z, a-z,
     *         '.' and, ',')
     */
    public static int codeCracker(String inputfile, int sampleSize) {

        assert sampleSize > 0 : "The samplesize cannot be negative or zero.";

        String inputString = fileReader(inputfile);
        // Only check the first 2000 characters of the file (optimaziton)
        String first2kofInputString = inputString.substring(0, 200);
        int stringLen = first2kofInputString.length();
        int bestSeed = 0;
        double bestRatio = -1;

        for (int i = 0; i < sampleSize; i++) {
            Random generator = new Random(i);
            String decryptedString = cryptString(first2kofInputString, generator, false);
            int commonCharCount = countLettersAndPunctuation(decryptedString);
            double ratio = commonCharCount / (double) stringLen;

            if (ratio > bestRatio) {
                bestSeed = i;
                bestRatio = ratio;
            }

        }

        return bestSeed;
    }

    /**
     * Counts number of 'novel-like' charcters in string (A-Z, a-z, '.' and, ',')
     * 
     * @param inputString
     * @return Count of 'novel like' characters
     */
    public static int countLettersAndPunctuation(String inputString) {
        assert true;

        int count = 0;

        for (int i = 0; i < inputString.length(); i++) {

            int charAsUnicode = (int) inputString.charAt(i);

            if (charAsUnicode > 64 && charAsUnicode < 91) {
                count++;
            } else if (charAsUnicode > 96 && charAsUnicode < 123) {
                count++;
            } else if (charAsUnicode == 32 || charAsUnicode == 46 || charAsUnicode == 44) {
                count++;
            }

        }

        return count;

    }

    /**
     * Writes string to file
     * 
     * @param fileString
     * @param filename
     */
    public static void fileWriter(String fileString, String filename) {
        assert true;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename));
            writer.write(fileString);
            writer.close();

        } catch (IOException exception) {
            System.out.println("An error occured");
            System.out.println(exception.getMessage());
        }

    }

    /**
     * Reads content of file
     * 
     * @param filename
     * @return Contents of file as string
     */
    public static String fileReader(String filename) {
        assert true;
        String fileContents = "";

        try {

            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            int c;

            while ((c = reader.read()) >= 0) {
                fileContents += (char) c;
            }

            reader.close();

        } catch (IOException exception) {

            System.out.println("An error occured:");
            System.out.println(exception.getMessage());

        }

        return fileContents;
    }

    /**
     * Takes a file as input, de- or encrypts it with a give random generator and
     * writes the result to the give outputfile
     * 
     * @param encrypt
     * @param inputfile
     * @param outputfile
     * @param generator
     */
    static void fileCryptor(boolean encrypt, String inputfile, String outputfile, Random generator) {

        assert true;
        String inputString = fileReader(inputfile);
        String outputString = cryptString(inputString, generator, encrypt);
        fileWriter(outputString, outputfile);

    }

    /**
     * Loops for all characters with character codes between 32 and 127 and print
     * for each character a line with the character, its encrypted character, and
     * the decrypted character belonging to the encrypted character
     */
    static void printBetween32and127() {
        assert true;
        for (int i = 32; i < 128; i++) {
            char inChar = (char) i;
            System.out.print(inChar);
            System.out.print(": ");
            System.out.print(crypt(inChar, 42, true));
            System.out.print(" ");
            System.out.println(crypt(inChar, 42, false));

        }
    }

    /**
     * Encrypt or decrypt a single character
     * 
     * @param input
     * @param random
     * @param encrypt
     * @return resulting character of encryption
     */
    static char crypt(char input, int random, boolean encrypt) {
        assert (random < 96) && (random >= 0) : "The random number should be within the range of 0 - 96";
        char result;
        if (encrypt) {
            result = encryptChar(input, random);
        } else {
            result = decryptChar(input, random);
        }

        return result;

    }

    /**
     * En- or decrypts an entire string
     * 
     * @param inputString
     * @param generator
     * @return String result of en- or decryption
     */
    static String cryptString(String inputString, Random generator, boolean encrypt) {
        assert true;
        String outputString = "";

        for (int i = 0; i < inputString.length(); i++) {
            int randInt = generator.nextInt(96);
            char c = inputString.charAt(i);

            outputString += crypt(c, randInt, encrypt);
        }

        return outputString;
    }

    /**
     * Encrypt a single character
     * 
     * @param input
     * @param random
     * @return Encrypted Character
     */
    static char encryptChar(char input, int random) {
        assert (random < 96) && (random >= 0) : "The random number should be within the range of 0 - 96";
        int inputInt = (int) input;
        char result = input;
        if (inputInt < 128 && inputInt > 31) {
            int charInt = ((inputInt - 32 + random + 96) % 96) + 32;
            result = (char) charInt;
        }
        return result;
    }

    /**
     * Decrypt a single character
     * 
     * @param input
     * @param random
     * @return Decrypted character
     */
    static char decryptChar(char input, int random) {
        assert (random < 96) && (random >= 0) : "The random number should be within the range of 0 - 96";
        int inputInt = (int) input;
        char result = input;
        if (inputInt < 128 && inputInt > 31) {
            int charInt = ((inputInt - 32 - random + 96) % 96) + 32;
            result = (char) charInt;
        }
        return result;
    }
}
