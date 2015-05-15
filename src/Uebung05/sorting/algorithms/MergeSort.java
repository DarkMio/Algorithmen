package Uebung05.sorting.algorithms;

import Uebung05.sorting.Container;
import Uebung05.sorting.SortingAlgorithm;

public class MergeSort extends SortingAlgorithm {

    public MergeSort() {
        super("MergeSort");
    }

    public void sort(Container s) {
        mergeSort(s);
    }

    private Container mergeSort(Container s) {
        int size = s.getSize();
        int half = size/2;
        int j = size%2;
        if (size <= 1) return s;

        Container x = new Container("x", half);
        Container y = new Container("y", half+j);

        for(int i = 0; i < half+1; i++) {
            x.set(i, s.get(i));
            y.set(i+size/2+j, s.get(i+size/2+j));
        }
        mergeSort(x);
        mergeSort(y);

    }

    private void merge(Container s, Container t) {

    }
}
