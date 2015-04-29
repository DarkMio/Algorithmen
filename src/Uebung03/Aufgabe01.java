package Uebung03;

public class Aufgabe01 {

    public static void main(String[] args) {
        System.out.println(calcSomething(14));
        System.out.println(calcSomethingBetter(14));
        /* -------------------------------------------- */
        System.out.println(calcSomethingElse(new int[]{1, 2, 3, 4}, 10));
        System.out.println(calcSomethingElseBetter(new int[]{1, 2, 3, 4}, 10));

    }


    public static int calcSomething(int n) {
        // Result: 4 + 5n
        int k = 0;
        for (int i = 0; i <= n; i++) {
            k = k + i;
        }
        return k * 2 - n;
    }

    public static int calcSomethingBetter(int n) {
        // Result: 2
        return n * n;
    }

    public static int calcSomethingElse(int[] a, int k) {
        // Result: 2 + 8(a-1)
        int p = 0;
        for (int i = 1; i < a.length; i++) {
            p = p + a [i] * k;
        }
        return p;
    }

    public static int calcSomethingElseBetter(int[] a, int k) {
        // Result: 3 + 6(a-1)
        int len = a.length;
        int p = 0;
        for (int i = 1; i < len; i++) {
            p += a[i];
        }
        return p*k;
    }
}
