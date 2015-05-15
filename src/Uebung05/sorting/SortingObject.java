package Uebung05.sorting;

import java.util.Date;
import java.util.Random;


/**
 * Objekte, die in Instanzen von {@link Container} abgespeichert werden können.
 * Diese Objekte sollen nach der Größe ihres Wertes, der über die Methode
 * {@link #getValue()} abgefragt werden kann, sortiert werden.
 */
public class SortingObject {
	
    private static Random rand = new Random(new Date().getTime());
	private int b;
	private int e;
	private static final long p =  131071;
	
	
	public SortingObject() {
		b = rand.nextInt((int)p);
		e = rand.nextInt((int)p);
	}

	public SortingObject(int b, int e) {
		this.b = b;
		this.e = e;
	}
	
	
	private static long f(long x, long r) {
		if (r == 0) return 1;
		if ((r & 1) == 1) return (x * f(x, r - 1)) % p; 
		else {
			long z = f(x, r/2);
			return (z * z) % p;
		}
	}

	public int normalizedValue(int max) {
		int v = getValue();
		return (max*v)/(int)p;
	}
	
	public int getValue() {
		return (int)f(b, e);
	}

}
