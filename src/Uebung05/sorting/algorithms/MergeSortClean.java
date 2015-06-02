package Uebung05.sorting.algorithms;

import Uebung05.sorting.Container;
import Uebung05.sorting.SortingAlgorithm;
import Uebung05.sorting.SortingObject;

public class MergeSortClean extends SortingAlgorithm {

    private Container s;
    private Container z;

    public MergeSortClean() {
        super("MergeSort");
    }

    public void sort(Container s) {
        this.s = s;
        z = new Container("z", s.getSize()-1);
        mergeSort(0, s.getSize()-1, 0, z.getSize()-1);
    }

    private void mergeSort(int s_left, int s_right, int z_left, int z_right) {
        int size = s.getSize();
        int half = size/2;
        int j = size%2;
        if (size <= 1) return;

        //Container x = new Container("x", half);
        // x = s_left, s_left + half
        //Container y = new Container("y", half+j);
        // Z = z_left, z_left + half + j
        for(int i = 0; i < half; i++) {
            z.set(i, s.get(i));
            z.set(i + half, s.get(i + half));
        }
        z.set(z.getSize()-1, s.get(s.getSize() - 1));
        // v should be mergeSort on x
        mergeSort(s_left, s_left + half, z_left, z_left + half + j);
        // v should be mergeSort on y
        mergeSort(z_left, z_left + half + j, z_left, z_left + half + j);
        merge(s_left, s_left+half, z_left, z_left+half+j);
    }

    private void merge(int zone1_left, int zone1_right, int zone2_left, int zone2_right) {
        int size_x = zone1_right - zone1_left;
        int size_y = zone2_right - zone2_left;
        int pointer_x = 0;
        int pointer_y = 0;
        int pointer_z = zone1_left;
        while (pointer_x < size_x && pointer_y < size_y) {
            if (z.get(zone1_left+pointer_x).getValue() < z.get(zone2_left+pointer_y).getValue()) {
                s.set(pointer_z, z.get(pointer_x));
                pointer_x ++;
            } else {
                z.set(pointer_z, z.get(zone2_left + pointer_y));
                pointer_y ++;
            }
            pointer_z ++;
        }

        while (pointer_x < size_x) {
            z.set(pointer_z, z.get(zone1_left + pointer_x));
            pointer_z++;
            pointer_x++;
        }
        while (pointer_y < size_y) {
            z.set(pointer_z, z.get(zone2_left + pointer_y));
            pointer_z++;
            pointer_y++;
        }
        return;
    }
}
