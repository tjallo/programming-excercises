package exercise3;


import static java.lang.Integer.min;

public class Exercise3 {
    public static void main(String[] args) {
        //Exercise 1.a
        System.out.println(sum(100));
        System.out.println(power(2, 8));
        int[] array = {12, 8, 14, 23, 16};
        System.out.println(minimum(array, array.length));
        System.out.println(gcd(1650, 2250));
        System.out.println("\n");
        //Exercise 1.b
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col <= row; col++)
                System.out.print(pascal(row, col) + " ");
            System.out.println();
        }
        System.out.println("\n");
        //Exercise 2.a
        //int i;
        //for(i=0; i<=1000; i++){
        //    System.out.println(fib(i)+" ");
        //}
        /*
        Exercise 2.a

         we can see that for bigger i, the time the machine needs to calculate the fibonacci numbers
         gets extremely long very fast and we cannot extend a certain number without waiting very long.
         We can conclude that to compute the fibonacci numbers with this algorithm
         we need to traverse back with recursion very many times to the beginning  which leads to
         an extremely big computation time.
         */
        //Exercise 2.b
        int[] fibArray = new int[1000];
        fibImproved(1000, fibArray);


    }

    private static int sum(int n) {
        if (n == 0)
            return 0;
        else {
            return n + sum(n - 1);
        }
    }

    private static int power(int x, int n) {
        if (n == 0)
            return 1;
        else {
            return x * power(x, n - 1);
        }
    }

    private static int minimum(int[] a, int n) {
        if (n == 0)
            return a[0];
        else {
            return min(a[n - 1], minimum(a, n - 1));
        }

    }

    private static int gcd(int n, int m) {
        if (m == 0)
            return n;
        else if (n < m)
            return gcd(m, n);
        else {
            return gcd(m, n % m);
        }
    }

    public static int pascal(int row, int col) {
        if (row == 0 || row == 1 || col == 0 || col == row)
            return 1;
        else {
            return pascal(row - 1, col - 1) + pascal(row - 1, col);
        }
    }

    static int fib(int n) {
        if (n == 0 || n == 1)
            return 1;
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    static int fibImproved(int n, int[] array) {
        array[0] = 1;
        array[1] = 1;
        if (array[n] != 0)
            return array[n];
        else {
            return fibImproved(n - 1, array) + fibImproved(n - 2, array);
        }
    }
}
