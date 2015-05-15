package Uebung05.sorting;


/**
 * Eine abstrakte Klasse für Sortieralgorithmen. Alle konkreten Sortieralgorithmen
 * sollten sich von dieser Klasse ableiten.
 */
public abstract class SortingAlgorithm {

    /**
     * Der Name des Algorithmus.
     */
	private String name;
	
	public SortingAlgorithm(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
    public abstract void sort(Container c); 
    
}
