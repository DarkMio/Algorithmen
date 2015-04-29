package Uebung02.hanoi;

import java.awt.Color;

import misc.simplevisuals.ViewPort;

public class GrafikPrimitive {

    
    public static void zeichenRechteck(ViewPort vp, int xLO, int yLO, int breite, int hoehe, Color c) {
        for (int i = 0; i<breite; i++) {
            for (int j = 0; j<hoehe; j++) {
                vp.drawPix(xLO + i, yLO + j, c);
            }            
        }
    }
    
    
}
