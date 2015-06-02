package Uebung05.sorting.algorithms;

import Uebung05.sorting.Container;
import Uebung05.sorting.SortingAlgorithm;
import Uebung05.sorting.SortingObject;

public class MergeSortAlt extends SortingAlgorithm {

    public MergeSortAlt() {
        super("MergeSort");
    }

    public void sort(Container s) {

        SortingObject[] z = mergeSort(fromContainerToArray(s));
        fromArrayToContainer(z);
    }

    private SortingObject[] mergeSort(SortingObject[] s) {
        int size = s.length;
        int half = size/2;
        int j = size%2;
        if (size <= 1) return s;

        SortingObject[] x = new SortingObject[half];
        SortingObject[] y = new SortingObject[half+j];

        for(int i = 0; i < half; i++) {
            x[i] = s[i];
            y[i] = s[i+half];
        }
        y[y.length-1] = s[s.length - 1];
        x = mergeSort(x);
        y = mergeSort(y);
        return merge(x, y);
    }

    private SortingObject[] merge(SortingObject[] x, SortingObject[] y) {
        int size_x = x.length;
        int size_y = y.length;
        int pointer_x = 0;
        int pointer_y = 0;
        int pointer_z = 0;
        SortingObject[] z = new SortingObject[size_x + size_y];
        while (pointer_x < size_x && pointer_y < size_y) {
            if (x[pointer_x].getValue() < y[pointer_y].getValue()) {
                z[pointer_z] = x[pointer_x];
                pointer_x ++;
            } else {
                z[pointer_z] = y[pointer_y];
                pointer_y ++;
            }
            pointer_z ++;
        }

        while (pointer_x < size_x) {
            z[pointer_z] = x[pointer_x];
            pointer_z++;
            pointer_x++;
        }
        while (pointer_y < size_y) {
            z[pointer_z] = y[pointer_y];
            pointer_z++;
            pointer_y++;
        }
        return z;
    }

    public static Container fromArrayToContainer(SortingObject[] s) {
        Container z = new Container("z", s.length);
        z.setDelay(1);
        for(int i = 0; i < z.getSize(); i++) {
            z.set(i, s[i]);
        }
        return z;
    }

    public static SortingObject[] fromContainerToArray(Container s) {
        SortingObject[] z = new SortingObject[s.getSize()];
        for(int i = 0; i < z.length; i++) {
            z[i] = s.get(i);
        }
        return z;
    }
}
