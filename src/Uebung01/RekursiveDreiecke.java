package Uebung01;

import misc.simplevisuals.ViewPort;

public class RekursiveDreiecke extends Thread {

    /**
     * Zeichnet ein schwarzes Dreieck in den angegebenen Viewport
     * @param vp Der ViewPort in den gezeichnet werden soll.
     * @param x1 x-Koordinate des 1. Punkts.
     * @param y1 y-Koordinate des 1. Punkts.
     * @param x2 x-Koordinate des 2. Punkts.
     * @param y2 y-Koordinate des 2. Punkts.
     * @param x3 x-Koordinate des 3. Punkts.
     * @param y3 y-Koordinate des 3. Punkts.
     */
    private static void drawTriangle(ViewPort vp, int x1, int y1, int x2, int y2, int x3, int y3) {
        vp.line(x1, y1, x2, y2, 0, 0, 0);
        vp.line(x1, y1, x3, y3, 0, 0, 0);
        vp.line(x2, y2, x3, y3, 0, 0, 0);
    }


    /**
     * Berechnet die Distanz zwischen zwei gegebenen Punkten.
     * @param x1 x-Koordinate des 1. Punkts.
     * @param y1 y-Koordinate des 1. Punkts.
     * @param x2 x-Koordinate des 2. Punkts.
     * @param y2 y-Koordinate des 2. Punkts.
     */
    private static double distance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }


    /**
     * Recursive function that prioritizes top, right. left (in that order).
     * Draw calls are converted to ints, function runs on doubles. This enhances draw precision.
     * @param vp Target ViewPort
     * @param x1 one triangle corner, x coordinate
     * @param y1 one triangle corner, y coordinate
     * @param x2 second triangle corner, x corner
     * @param y2 second triangle corner, y corner
     * @param x3 third triangle corner, x corner
     * @param y3 third triangle corner, y corner
     */
    public static void drawTriangleRecursive(ViewPort vp, double x1, double y1, double x2, double y2, double x3, double y3) {

        if (distance(x1, y1, x2, y2) >= 2){
            drawTriangle(vp, (int) x1, (int) y1, (int) x2, (int) y2, (int) x3, (int) y3);

            // top
            drawTriangleRecursive(
                                               vp,
                    (x1 + x2) / 2 + (x2 - x3) / 2,
                    (y1 + y2) / 2 + (y2 - y3) / 2,
                    (x1 + x2) / 2 + (x1 - x3) / 2,
                    (y1 + y2) / 2 + (y1 - y3) / 2,
                    (x1 + x2) / 2,
                    (y1 + y2) / 2
            );

            // right
            drawTriangleRecursive(             vp,
                    (x3 + x2) / 2 + (x2 - x1) / 2,
                    (y3 + y2) / 2 + (y2 - y1) / 2,
                    (x3 + x2) / 2 + (x3 - x1) / 2,
                    (y3 + y2) / 2 + (y3 - y1) / 2,
                    (x3 + x2) / 2,
                    (y3 + y2) / 2
            );

            // left
            drawTriangleRecursive(                     vp,
                            (x1 + x3) / 2 + (x3 - x2) / 2,
                            (y1 + y3) / 2 + (y3 - y2) / 2,
                            (x1 + x3) / 2 + (x1 - x2) / 2,
                            (y1 + y3) / 2 + (y1 - y2) / 2,
                            (x1 + x3) / 2,
                            (y1 + y3) / 2
            );
        }
    }


    public static void drawFunnyTriangles() {
        int width = 1600;
        int height = 1200;
        ViewPort vp = new ViewPort("Dreiecke", width, height);
        drawTriangle(vp, 0, height, width, height, width/2, 0);
        drawTriangleRecursive(vp, width/4, height/2, width/2 + width/4, height/2, width/2, height);
    }


    public static void main(String[] a) {
        drawFunnyTriangles();
    }
}
