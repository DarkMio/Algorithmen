package Uebung08;

import Uebung08.hashtables.Keyable;

public class HashArray {

    private Keyable[] array;

    public HashArray(int size) {
        array = new Keyable[size];
    }

    public HashArray() {
        this(10);
    }

    public void enlarge() {
        Keyable[] cache = new Keyable[array.length*2];
        for(Keyable k: array) {
                put(k);
            }
        array = cache;
    }

    public void put(Keyable k) {
        int key = k.getKey();

        for(int i = 0; i < array.length; i++) {
            if(array[getIndex(key, i)] == null) {
                array[getIndex(key, i)] = k;
                return;
            }
        }
        enlarge();
        put(k);

    }

    public boolean contains(Keyable k) {
        int key = k.getKey();
        for(int i = 0; i < array.length; i++) {
            if(k.equals(array[getIndex(key, i)])) return true;
        }
        return false;
    }

    private int getIndex(int key, int i) {
        i += 1;
        return (key*i)%array.length;
    }
}
