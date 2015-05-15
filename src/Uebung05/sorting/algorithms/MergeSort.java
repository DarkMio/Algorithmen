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

        for(int i = 0; i < half; i++) {
            x.set(i, s.get(i));
            y.set(i, s.get(i+half+j));
        }
        y.set(j + half-1, s.get(s.getSize() - 1));
        x = mergeSort(x);
        y = mergeSort(y);
        s = merge(x, y);
        return s;
    }

    private Container merge(Container x, Container y) {
        System.out.println(x.getSize() + " " + y.getSize());
        int size_x = x.getSize();
        int size_y = y.getSize();
        int pointer_x = 0;
        int pointer_y = 0;
        int pointer_z = 0;
        Container z = new Container("z", size_x + size_y);
        while ((pointer_x < size_x-1) || (pointer_y < size_y-1)) {
            if (x.get(pointer_x).getValue() > y.get(pointer_y).getValue()) {
                z.set(pointer_z, x.get(pointer_x));
                pointer_x ++;
            } else {
                z.set(pointer_z, y.get(pointer_x));
                pointer_y ++;
            }
            pointer_z ++;
        }
        if (pointer_x <= size_x) {
            z = append(z, y, pointer_z, pointer_y);
        } else {
            z = append(z, x, pointer_z, pointer_x);
        }
        return z;
    }

    private Container append(Container z, Container xy, int pointer_z, int pointer_xy) {
        for(int i = pointer_z, j = pointer_xy; i < xy.getSize()-1; i++, j++) {
            z.set(i, xy.get(j));
        }
        return z;
    }
}
