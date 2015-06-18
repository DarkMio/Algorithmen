package Uebung05.sorting;

import Uebung05.sorting.algorithms.*;
// import Uebung05.sorting.algorithms.HeapSort;
// import Uebung05.sorting.algorithms.InsertionSort;
// import Uebung05.sorting.algorithms.MergeSort;
// import Uebung05.sorting.algorithms.QuickSort;


/**
 * Diese Klasse dient zum Starten eines oder mehrerer Sortierprozesse.
 * Wenn mehrere Sortierprozesse gestartet werden, laufen diese parallel.
 */
public class SortRunner implements Runnable {
	
	private Container myCont;
	private SortingAlgorithm sorter;
	
	public SortRunner(SortingAlgorithm sa, int size) {
		myCont = new Container(sa.getName(), size);
		sorter = sa;
		new Thread(this).start();
	}
	
	public SortRunner(SortingAlgorithm sa, int size, int delay) {
		myCont = new Container(sa.getName(), size);
		sorter = sa;
		myCont.setDelay(delay);
		new Thread(this).start();
	}


	@Override
	public void run() {
		sorter.sort(myCont);
	}
	
	
	public static void main(String[] a) {
		//new SortRunner(new BubbleSort(), 1900, 0);
        //new SortRunner(new InsertionSort(), 1900, 0);
		new SortRunner(new HeapSort(), 20, 1);
		// new SortRunner(new QuickSort(), 450, 1);
		//new SortRunner(new MergeSort(), 10, 40);
		//new SortRunner(new MergeSortAlt(), 1900, 1);
	}
}
