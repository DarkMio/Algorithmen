package Uebung02;

public class Wurzel {

    public static void main(String[] args) {
        System.out.print(wurzel(65026));
    }

    public static int wurzel(int x) {
        if (x<0) return 0;
        return wurzelRek(x, 0, x);
    }

    public static int wurzelRek(int x, int l, int r) {
        int k = (l+r)/2;
        if (l == r) return k;
        if (k*k <= x && (k+1)*(k+1) > x) return k;
        if (k*k > x) return wurzelRek(x, l, k);
        return wurzelRek(x, k, r);
    }
}
