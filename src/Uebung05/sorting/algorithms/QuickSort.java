package Uebung05.sorting.algorithms;

import Uebung05.sorting.Container;
import Uebung05.sorting.SortingAlgorithm;
import Uebung05.sorting.SortingObject;

public class QuickSort extends SortingAlgorithm {

    public QuickSort() {
        super("QuickSort");
    }

    private void swap(Container s, int i1, int i2) {
        SortingObject x = s.get(i1);
        s.set(i1, s.get(i2));
        s.set(i2, x);
    }

    public void sort(Container s) {
        sort(s, 0, s.getSize()-1);
    }

    private void sort(Container s, int left, int right) {
        int li = left;
        int re = right;
        int pivot_value = s.get(left).getValue();

        while (left < right) {
            if(s.get(left+1).getValue() <= pivot_value) {
                swap(s, left, left+1);
                left++;
            } else if(s.get(left+1).getValue() > pivot_value) {
                swap(s, left+1, right);
                right--;
            }
        }

        if(left > li+1) {
            sort(s, li, left-1);
        }
        if(left + 1 < re) {
            sort(s, left+1, re);
        }
    }
}
