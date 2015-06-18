package Uebung05.sorting.algorithms;

import Uebung05.sorting.Container;
import Uebung05.sorting.SortingObject;
import Uebung05.sorting.SortingAlgorithm;


public class HeapSort extends SortingAlgorithm {

    public HeapSort() {
        super("HeapSort");
    }

    public void sort(Container s) {
        initHeapForm(s);
        sortFromHeapForm(s);
    }

    private void vertausche(Container s, int li, int re) {
        SortingObject c = s.get(re);
        s.set(re, s.get(li));
        s.set(li, c);
    }

    private void heapify(Container s, int li, int re) {
        int k = 2*li;
        if (k >= re) return;
        if (k==re) {
            if (s.get(k).getValue() < s.get(li).getValue()) {
                vertausche(s, li, k);
                return;
            }
        }
        if (s.get(k).getValue() < s.get(k+1).getValue()) k=k+1;
        if (s.get(li).getValue() < s.get(k).getValue()) {
            vertausche(s, li, k);
            heapify(s, k, re);
        }
        return;
    }

    private void sortFromHeapForm(Container s) {
        int k = s.getSize()-1;
        while (k > 0) {
            vertausche(s, 0, k);
            k--;
            heapify(s, 0, k);
        }
    }

    private void initHeapForm(Container s) {
        for (int i = s.getSize()/2; i>=0; i--) {
            heapify(s, i, s.getSize()-1);
        }
    }

}
