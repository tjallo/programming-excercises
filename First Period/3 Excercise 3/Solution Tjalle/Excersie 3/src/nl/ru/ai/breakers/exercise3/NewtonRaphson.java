package nl.ru.ai.breakers.exercise3;

import java.util.Scanner;

public class NewtonRaphson {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("I want to know the square root of: ");
        double a = scanner.nextFloat();

        calculate(a);

        scanner.close();

    }

    public static void calculate(double a) {

        double xn = 1.0;
        double fx = 1.0;

        while (fx > 0.001) {

            xn = xn - ((xn * xn - a) / (2 * xn));
            fx = (xn * xn) - a;

        }

        System.out.print("The square root of ");
        System.out.print(a);
        System.out.print(" is ");
        System.out.println(Math.abs(xn));

    }

}
