package Uebung08.hashtables;

import java.awt.Color;

public class GrafikPrimitive {

    
    public static void fuelleRechteck(ViewPort vp, int xLO, int yLO, int breite, int hoehe, Color c) {
        for (int i = 0; i<breite; i++) {
            for (int j = 0; j<hoehe; j++) {
                vp.drawPix(xLO + i, yLO + j, c);
            }            
        }
    }
    

    public static void zeichneRechteck(ViewPort vp, int xLO, int yLO, int breite, int hoehe, Color c) {
        for (int i = 0; i<breite; i++) {
            vp.drawPix(xLO + i, yLO, c);
            vp.drawPix(xLO + i, yLO + hoehe - 1, c);
        }
        for (int i = 0; i<hoehe; i++) {
            vp.drawPix(xLO, yLO + i, c);
            vp.drawPix(xLO + breite - 1, yLO + i, c);
        }
    }
    
}
