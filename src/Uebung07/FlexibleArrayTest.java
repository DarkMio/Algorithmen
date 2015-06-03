package Uebung07;

import Uebung07.flexiblearray.FlexibleArray;

public class FlexibleArrayTest {

    public static void main(String[] arga) {
        FlexibleArray fa = new FlexibleArray();

        for(int i = 0; i <= 100000; i++) {
            fa.put(i, i);
            System.out.println(fa.get(i));
        }

        fa.compress();
        System.out.println(fa.usedSize() + " " + fa.completeSize());


    }
}
