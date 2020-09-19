package nl.ru.ai.breakers.exercise3;

import java.util.Scanner;

public class Solver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        getABCandPrintSolution(scanner);

        scanner.close();
    }

    public static void getABCandPrintSolution(Scanner scanner) {

        System.out.print("A: ");
        double a = scanner.nextFloat();
        System.out.print("B: ");
        double b = scanner.nextFloat();
        System.out.print("C: ");
        double c = scanner.nextFloat();

        if (a == 0) {

            System.out.println("\'A\' cannout be zero.");

        } else {

            printSolution(a, b, c);

        }

    }

    public static void printSolution(double a, double b, double c) {

        double discriminant = (b * b) - 4 * a * c;

        if (discriminant == 0) {

            printOneSolution(a, b, c);

        } else if (discriminant > 0) {

            printTwoSolutions(a, b, c, discriminant);

        } else {

            System.out.println("There are no real solutions");
            
        }

    }

    public static void printOneSolution(double a, double b, double c) {

        double solution = -b / (2 * a);

        System.out.println(solution);

    }

    public static void printTwoSolutions(double a, double b, double c, double discriminant) {

        double solution1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double solution2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        System.out.println(solution1);
        System.out.println(solution2);

    }

}
