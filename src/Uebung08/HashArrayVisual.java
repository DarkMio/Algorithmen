package Uebung08;

import Uebung08.hashtables.ArrayDataCollection;
import Uebung08.hashtables.IntKeyable;
import Uebung08.hashtables.Keyable;
import Uebung08.hashtables.VisualArray;

import java.math.BigInteger;

public class HashArrayVisual {

    private VisualArray array;
    private int max_depth;

    public static void main(String[] a) {
        HashArrayVisual adc = new HashArrayVisual();
        for (int i = 0; i < 1000000; i++) {
            adc.put(new IntKeyable((int) (Math.random()*10000)));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public HashArrayVisual(int size) {
        array = new VisualArray(10);
        max_depth = 1000;
    }

    public HashArrayVisual() {
        this(10);
    }

    public void setDepth(int i) {
        max_depth = i;
    }

    public void enlarge() {
        VisualArray cache = new VisualArray(array.length()*2);
        for(int i = 0; i < array.length(); i++) {
            Keyable k = (Keyable) array.get(i);
            cache.set(getIndex(k.getKey(), 0), k);
        }
        array.close();
        array = cache;
    }

    public void put(Keyable k) {
        int key = k.getKey();

        for(int i = 0; i < array.length(); i++) {
            if(array.get(getIndex(key, i)) == null) {
                array.set(getIndex(key, i), k);
                return;
            }
        }
        enlarge();
        put(k);
    }

    public boolean contains(Keyable k) {
        int key = k.getKey();
        for(int i = 0; i < array.length(); i++) {
            if(k.equals(array.get(getIndex(key, i)))) return true;
        }
        return false;
    }

    private int getIndex(int key, int i) {
        i += 1;
        BigInteger x = new BigInteger(""+array.length()/2);

        return (key+x.nextProbablePrime().intValue()*i)%array.length();
    }
}
