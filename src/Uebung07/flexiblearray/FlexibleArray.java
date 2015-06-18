package Uebung07.flexiblearray;

public class FlexibleArray {

    protected Object[] data;
    protected int dataCount = 0;

    public FlexibleArray() {
        data = new Object[10];
    }

    public FlexibleArray(int size) {
        data = new Object[size];
    }

    public void put(int index, Object o) {
        if (index < 0) throw new IndexOutOfBoundsException("index (" + index + ")can't be negative");
        if (o == null) throw new NullPointerException("o cannot be null.");
        if (index >= (data.length)) {
            Object[] cache = new Object[dataCount*2];
            System.arraycopy(data, 0, cache, 0, data.length);
            data = cache;
        }
        if (data[index] == null) dataCount++;
        data[index] = o;
    }

    public Object remove(int index) {
        if (data[index] != null) dataCount--;
        Object cache = data[index];
        data[index] = null;
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
        for(int i = 0, j = 0; i < data.length; i++) {
            if (data[i] != null) {
                array[j] = data[i];
                j++;
            }
        }
        data = array;
    }
}
