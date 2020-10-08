package nl.ru.ai.breakers.exercise3;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Encryption {

    public static void main(String[] arguments) {
        Random generator = new Random(42);
        //code to ask for users input
        Scanner scanner = new Scanner(System.in);
        System.out.println("do you want to encrypt or decrypt (en/de)");
        String ED = scanner.nextLine();
        System.out.println("type in the input filename");
        String inputFilename = scanner.nextLine();
        System.out.println("type in the output filename");
        String outputFilename = scanner.nextLine();

        //Exercise 1

        for(int i=32;i<127;i++)
            System.out.println((char)i+" "+crypt(((char)i), 42, true )+" "
                +crypt(crypt(((char)i), 42, true ), 42, false));
        System.out.println(enDeCprypt(ED));

       //Exercise 2

        textEnCryptor(generator,inputFilename,outputFilename,enDeCprypt(ED));
    }



    /**
     * Calculates the encryption/ decryption value of the inserted char
     * @param input
     * @param random
     * @param encrypt
     * @return encrypted / decrypted character
     */

    static char crypt(char input, int random, boolean encrypt){
        assert true;
        int b;
        int a =  input;
        int r = random;
        if(encrypt)
            if(a<32 || a> 127)
                b=a;
            else
                b= (a-32 + r + 96)%96+32;
        else
            if(a<32 || a> 127)
                b=a;
            else
                b= (a-32 - r + 96)%96+32;
        return (char)b;
    }

    /**
     *
     * @param ED
     * @return whether the program should decrypt or encrypt
     */
    static boolean enDeCprypt(String ED) {
        assert true;
        switch (ED) {
            case "en":
                return true;
            default:
                return false;
        }
    }

    /**
     * reads the characters from the user typed file and converts them to a String
     * @param inputFilename
     * @return String out of all characters in the file
     */
    static String readFile(String inputFilename){
        assert true;
        String textInFile ="";

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(inputFilename));
            int a;

            while ((a = reader.read()) >= 0) {
                textInFile += (char) a;
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("something went wrong when reading the file:"+e.getMessage());
        }
        return textInFile;
    }

    /**
     * Writes the decrypted Text to a file the user types in
     * @param outputFilename
     * @param decryptedText
     */
    static void writeToFile(String outputFilename, String decryptedText){
        assert true;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFilename));
            writer.write(decryptedText);
            writer.close();

        } catch (IOException e) {
            System.out.println("something went wrong when writing to the file"+e.getMessage());
        }

    }

    /**
     * encrypts the Text from the input File and writes it to the output file
     * @param inputFile
     * @param outputFile
     * @param enDeCrypt
     * @param generator
     */
    static void textEnCryptor( Random generator  ,String inputFile ,String outputFile,  Boolean enDeCrypt)
    {
        assert true;
        String textInFile= readFile(inputFile);
        String decryptedText="";
        for(int i = 0; i<textInFile.length();i++)
        {
            char letter = textInFile.charAt(i);
            int randomNumber= generator.nextInt(96);
            decryptedText+=crypt(letter,randomNumber, enDeCrypt);
        }
        writeToFile(outputFile, decryptedText);
    }
}

