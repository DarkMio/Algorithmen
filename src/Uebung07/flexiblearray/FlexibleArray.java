package Uebung07.flexiblearray;

import java.util.Objects;

public class FlexibleArray {

    protected Object[] data;
    protected int dataCount;

    public FlexibleArray() {
        data = new Object[10];
        dataCount = 0;
    }

    public FlexibleArray(int size) {
        data = new Object[size];
        dataCount = 0;
    }

    public void put(int index, Object o) {
        if (index < 0) throw new IndexOutOfBoundsException("index (" + index + ")can't be negative");
        if (o == null) throw new NullPointerException("o cannot be null.");
        if (index >= (data.length )) {
            Object[] cache = new Object[dataCount*2];
            System.arraycopy(data, 0, cache, 0, data.length);
            data = cache;
        }

        Object cache = data[index];
        data[index] = o;

        if (cache == null) dataCount++;
    }

    public Object remove(int index) {
        Object cache = data[index];
        data[index] = null;
        if (cache != null) dataCount--;
        return cache;
    }

    public Object get(int index) {
        return data[index];
    }

    public int usedSize() {
        return dataCount;
    }

    public int completeSize() {
        return data.length;
    }

    public void compress() {
        Object[] array = new Object[dataCount];
        for(int i = 0, j = 0; i <= data.length; i++) {
            if (!(data[i] == null)) {
                array[j] = data[i];
                j++;
            }
        }
        data = array;
    }
}
