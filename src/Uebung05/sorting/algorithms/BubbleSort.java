package Uebung05.sorting.algorithms;

import Uebung05.sorting.Container;
import Uebung05.sorting.SortingObject;
import Uebung05.sorting.SortingAlgorithm;


public class BubbleSort extends SortingAlgorithm {

	public BubbleSort() {
    	super("BubbleSort");
	}
	
    public void sort(Container s) {
        int n = s.getSize()-1;
        while (n > 0) {
            for (int i=0; i<n; i++) {
                if (s.get(i).getValue() >= s.get(i+1).getValue()) {
                    SortingObject x = s.get(i);
                    s.set(i, s.get(i+1));
                    s.set(i+1, x);
                }
            }
            n--;
        }
    }
    
    

}
