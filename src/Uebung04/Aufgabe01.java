package Uebung04;


public class Aufgabe01 {

    public static void main(String[] args) {
        System.out.println(log(2, 1023));
    }

    /**
     *  with n > 1: 1 + 3*(n-1)
     *  with n <= 1: n
     *  1 + 3*31 = 94
     * @param base logarithmis base
     * @param value value
     * @return int
     */
    static int log(int base,int value){
        if ( base > value ) return 0;
        return 1 + log(base, value / base);
    }

    public static int wurzel2(int x) {
        int i = 0;
        while (i
                *
                i <= x) i++;
        return i - 1;
    }

}
