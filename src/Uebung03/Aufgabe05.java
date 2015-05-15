package Uebung03;

import misc.simplevisuals.ViewPort;

public class Aufgabe05 {

    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        ViewPort vp = new ViewPort("ColorCloud", width, height);
        vp.fillAlt(10, 10, width-10, height-10);
    }
}
