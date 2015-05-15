package Uebung05.sorting.algorithms;

import Uebung05.sorting.Container;
import Uebung05.sorting.SortingObject;
import Uebung05.sorting.SortingAlgorithm;

public class InsertionSort extends SortingAlgorithm {

    public InsertionSort() {
        super("InsertionSort");
    }

    public void sort(Container s) {
        int n = 0;
        while (n <= s.getSize()-1) {
            for (int i = n; i > 0; i--) {
                if (s.get(i).getValue() < s.get(i-1).getValue()) {
                    SortingObject x = s.get(i);
                    s.set(i, s.get(i-1));
                    s.set(i-1, x);
                }
            }
            n++;
        }
    }
}
