package nl.ai.ru.exercise2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Exercise2 {
    public static void main(String[] arguments) throws IOException {
        //Exercise 1.1
        ArrayList<Character> source = new ArrayList<Character>();
        ArrayList<Character> destination = new ArrayList<Character>();
        source = fillArray(source, "AliceSortedNoCRNL.txt");
        int numberOfComparisons = removeDuplicates(source, destination);
        System.out.printf("Source: %s\n", source);
        System.out.printf("Destination: %s\n", destination);
        System.out.printf("%d comparisons made\n", numberOfComparisons);

        /*Exercise 1.2
        The order of run time complexity of removeDuplicates is O(n^2).
        The method removeDuplicates uses a for loop which loops as long as the input size is,
        and utilizes the method addWithoutDuplicates inside this loop. This method addWithoutDuplicates
        has two loops inside it, from which the while loop loops through another arrayList. This leaves us
        with two loops which loop through elements of arrayLists which leads to an order of n^2.
        */
        //Exercise 1.3
        /*
        The method removeDuplicates goes over every element of the arrayList source.
        the method addWithoutDuplicates goes over every of the arrayList destination one over another
        and checks whether the character is featured in the arrayList. Every checking time is counted.
        If then the time of checks is the length of the destination arrayList we know that the character
        is not featured in the arrayList yet and the character is added to the arrayList.
        However if the elements are sorted, we do not have to check for every element whether
        this element is in the destination arrayList yet and thus there is no need in looping through the
        destination arrayList.

        Because we know that if we add an element from the source to the destination arrayList and the next item
        in the source arrayList differs from the item we copied into the destination arrayList before,
        this item will not occur in the source arrayList anymore we do not have to loop through
        the destination arrayList over
        and over again which reduced the run time order.

        We can place this check in the for loop of reduce duplicates. So before copying an item
        from the source arrayList to the destination arrayList we check
        from position 1 to the length of the source arrayList
        if the item on position i equals the item on position i-1.
        If this is the case we increment i and check again. If it is the case that i!=i-1
        we add the item on index i to the destination arrayList.
   */
        //Exercise 1.4
        ArrayList<Character> source2 = new ArrayList<Character>();
        source2 = fillArray(source2, "AliceSorted.txt");
        ArrayList<Character> destination2 = new ArrayList<Character>();

        int numOfChecks = removeSortedDuplicates(source2, destination2);
        System.out.printf("Destination: %s\n", destination2);
        System.out.printf("%d checks made\n", numOfChecks);
        /*
        we can see that the run time complexity is indeed O(n) because the checks equals half of the number
        of characters in the text and the order ignores the 1/2. Therefore the Order is n.
        */
        //Exercise 2a
        /*
        Algorithm 1 is a boolean function which outputs whether a certain number is a prime number or not.
        It is of the order O(n) because the algorithm contains one for loop.
        Algorithm 2 returns the size of the arrayList which was put it as a parameter. This has a constant running time
        complexity because the size of the arrayList does not change the running time of the method .size().
        Big O is therefore O(1).
        Algorithm 3 loops through an arrayList of integers and checks for every integer whether it is a prime number.
        The algorithm is linearly dependent on the size of the arrayList and therefore the big O is O(n).
        Algorithm 4 is a boolean function which returns whether elements in an array are sorted  lowest
        to highest and returns true if they are. Once again the running time complexity is O(n) because
        the algorithm loops through the whole array.

        Exercise 2b
        part 1 in the jpg

        part 2
        pushUp
        pushUp traverses every parent in the array once so it has the complexity O(log n)

        buildHeap
        buildHeap processes pushUp as long as the array is not sorted so it has ru time complexity O(n)

        PushDown
        pushDown is the counterpart of pushUp and functions in a similar way. The run time complexity
        is therefore O(log n)

        pickHeap
        just restores the heap on one side of the tree. It traverses at most as many parents as the
        tree is big-1. The run time complexity is therefore O(log n)

        heap sort
        the whole algorithm heap sort is of running time complexity O(n log n)


         */




    }

    /**
     * fills the arrayList with the Characters of a file
     *
     * @param source
     * @param fileName
     * @return the arrayList filled with the characters
     */
    public static ArrayList<Character> fillArray(ArrayList<Character> source, String fileName) {
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            InputStreamReader reader = new InputStreamReader(inputStream);
            while (reader.read() != -1) {
                char c = (char) reader.read();
                source.add(c);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + "file could not be found");
        } catch (IOException e) {
            System.out.println(e.getMessage() + "file could not be read");
        }
        return source;
    }


    /**
     * Copies Characters from the source arrayList to the destination arrayList, without duplicates
     *
     * @param source
     * @param destination
     * @return number of comparisons made
     */
    private static int removeDuplicates(ArrayList<Character> source, ArrayList<Character> destination) {
        assert source != null : "Source array should be initialized";
        assert destination != null : "Destination array should be initialized";
        int numberOfComparisons = 0;
        for (int i = 0; i < source.size(); i++)
            numberOfComparisons += addWithoutDuplicates(source.get(i), destination);
        return numberOfComparisons;
    }

    /**
     * Copy a character to the destination arrayList, without duplicates
     *
     * @param character
     * @param destination
     * @return number of comparisons made
     */
    private static int addWithoutDuplicates(Character character, ArrayList<Character> destination) {
        assert destination != null : "Destination array should be initialized";
        int i = 0;
        while (i < destination.size() && destination.get(i) != character)
            i++;
        if (i == destination.size())
            destination.add(character);
        return i;
    }

    private static int removeSortedDuplicates(ArrayList<Character> source, ArrayList<Character> destination) {
        assert source != null : "Source array should be initialized";
        assert destination != null : "Destination array should be initialized";
        destination.add(source.get(0));
        int checks = 1;
        for (int i = 1; i < source.size(); i++) {
            if (source.get(i) != (source.get(i - 1))) {
                destination.add(source.get(i));
            }
            checks++;
        }
        return checks;
    }
}
