package Aufgabe1;

/**
 * Created by Mio on 16.04.2015.
 */
public class threader extends Thread {

    private ViewPort vp;
    private double x1, x2, x3, y1, y2, y3;

    public threader (ViewPort vp, double x1, double y1, double x2, double y2, double x3, double y3) {
        this.vp = vp;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    @Override
    public void run() {
        RekursiveDreiecke.drawTriangleRecursive(vp, x1, y1, x2, y2, x3, y3);
    }
}
