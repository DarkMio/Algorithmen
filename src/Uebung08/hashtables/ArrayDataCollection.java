package Uebung08.hashtables;

public class ArrayDataCollection {
    
    private VisualArray a;
    int p;
    
    public ArrayDataCollection() {
        a = new VisualArray(10);
        p = 0;
    }
    
    public void put(Object o) {
        if (p >= a.length()) {
            VisualArray b = new VisualArray(a.length() * 2);
            for (int i = 0; i < a.length(); i++) {
                b.set(i, a.get(i));
            }
            a.close();
            a = b;
        }
        a.set(p, o);
        p++;
    }
    

    public boolean contains(Object o) {
        for (int i = 0; i < a.length(); i++) {
            if (a.get(i).equals(o)) return true;
        }
        return false;
    }
    
    public static void main(String[] a) {
        ArrayDataCollection adc = new ArrayDataCollection();
        for (int i = 0; i < 2000; i++) {
            adc.put(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    

}
