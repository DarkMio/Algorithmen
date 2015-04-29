package Uebung02.hanoi;

import java.awt.Color;

import misc.simplevisuals.ViewPort;

public class Scheibe {
    
    
    private static int hoehe = 10;
    private static int breitenFakt = 10;
    private static Color scheibenFarbe = new Color(0, 0, 255); 
    private int size;
    
    public Scheibe(int s) {
        this.size = s;
    }
    
    public int getSize() {
        return size;
    }
    
    
    public boolean darfObenDrauf(Scheibe x) {
        return x.size < size;
    }
    
    public void draw(ViewPort vp, int xMitte, int y) {
        int breite = breitenFakt * size;
        GrafikPrimitive.zeichenRechteck(vp, xMitte - breite/2, y, breite, hoehe, scheibenFarbe);
    }
    
    public static int getHoehe() {
        return hoehe;
    }
    
    public static int getBreitenFakt() {
        return breitenFakt;
    }
    
    public static void setBreitenFakt(int f) {
        breitenFakt = f;
    }
    
    public static void setHoehe(int h) {
        hoehe = h;
    }

}
