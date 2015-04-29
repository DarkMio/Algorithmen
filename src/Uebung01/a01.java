package Uebung01;

/**
 * Created by Mio on 15.04.2015.
 */
public class a01 {

    public static void main(String[] args) {
        System.out.println(concatPow("Text", 100));
    }

    public static String concatPow(String w, int k) {
        if (k <= 0) {
            return "";
        } else {
            w = w + concatPow(w, k-1);
        }
        return w;
    }
}
