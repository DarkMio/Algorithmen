package misc.simplevisuals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * Eine Klasse für einfache Grafiken.
 *
 * <br/><br/>
 *
 * Damit ist es möglich ein Grafikfenster zu öffnen, in dem
 * beliebige Grafiken dargestellt werden können.
 * Dabei wird mit einem Hintergrundspeicher gearbeitet.
 * Das heißt, dass alle Punkte, die mit den Methoden
 * {@link #drawPix(int, int, Color)} und 
 * {@link #drawPix(int, int, int, int, int)}
 * gezeichnet werden zunächst in einen Hintergrundspeicher
 * geschrieben werden (sie erscheinen also nicht sofort sichtbar
 * auf dem Bildschirm).
 * Um den Hintergrundspeicher sichtbar zumachen, sollte die 
 * Methode {@link #copyBackgroundBuffer()}
 * benutzt werden. 
 *
 * <br/><br/>
 *
 *
 * @author Elmar Böhler
 */
public class ViewPort  {

    
    /* ----------------------------- Attribute ---------------------------- */

    private JFrame mainWindow;
    private Dimension rawSize;
    private BufferedImage viewPort;
    private BufferedImage preparationBuffer;
    private BufferedImage background;
    private ViewPortPanel panel;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();;
    private String title;
    private LinkedList<FinalizationMethod> finalizer;
    private LinkedList<Character> keyHistory;
    
    

    /* --------------------------- Konstruktoren --------------------------------- */

    /**
     * Erzeugt ein neues Grafikfenster.
     *
     * @param title Der Titel des erzeugten Fensters.
     * @param width Die Breite des erzeugten Fensters.
     * @param height Die Höhe des erzeugten Fensters.
     * @param xPos Die x-Position der linken oberen Ecke des Grafikfensters auf dem Bildschirm.
     * @param yPos Die y-Position der linken oberen Ecke des Grafikfensters auf dem Bildschirm.
     */
    public ViewPort(String title, int width, int height, int xPos, int yPos) {
        this.title = title;
        rawSize = new Dimension(width, height);

        viewPort = new BufferedImage(rawSize.width, rawSize.height, BufferedImage.TYPE_3BYTE_BGR);
        preparationBuffer = new BufferedImage(rawSize.width, rawSize.height, BufferedImage.TYPE_3BYTE_BGR);
        background = new BufferedImage(rawSize.width, rawSize.height, BufferedImage.TYPE_3BYTE_BGR);
        panel = new ViewPortPanel(rawSize.width, rawSize.height);

        mainWindow = new JFrame(this.title);
        keyHistory = new LinkedList<>();
        mainWindow.addKeyListener(new MyKeyListener());

        mainWindow.setLocation(xPos, yPos);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.addWindowListener(new ViewPortalWindowListener());
        JPanel globalPanel = new JPanel();
        globalPanel.setLayout(new BoxLayout(globalPanel, BoxLayout.Y_AXIS));
        mainWindow.getContentPane().add(panel);
        mainWindow.pack();
        mainWindow.setVisible(true);

        setBackgroundColor(new Color(255,255,255));
        clearViewPort();
        finalizer = new LinkedList<FinalizationMethod>();
    }



    /**
     * Erzeugt ein neues Grafikfenster und positioniert es
     * mittig im Bildschirm.
     *
     * @param title Der Titel des erzeugten Fensters.
     * @param width Die Breite des erzeugten Fensters.
     * @param height Die Höhe des erzeugten Fensters.
     */
    public ViewPort(String title, int width, int height) {
        this(title, width, height, (screenSize.width - width) / 2, (screenSize.height - height) / 2);
    }
    
    
    
    /* ------------------------------ Public Methoden --------------------------------- */


    /**
     * Setzt die Hintergrundfarbe auf die gegebene Farbe.
     * Die Methode bereitet dabei nur den Hintergrund vor,
     * so dass bei einem Aufruf von {@link #clearViewPort()}
     * das gesamte Grafikfenster mit der gegebenen Farbe
     * befüllt wird. 
     *
     * Ein Aufruf dieser Methode befüllt aber das Grafikfenster
     * nicht unmittelbar.
     *
     * @param c Die neue Farbe des Hintergrunds.
     */
    public void setBackgroundColor(Color c) {
        prepareBackground(c);
    }


    /**
     * Gibt die Farbe des Bildpunktes an den gegebenen Koordinaten zurück.
     *
     * @param x Die x-Koordinate des gefragten Pixels.
     * @param y Die y-Koordinate des gefragten Pixels.
     * @return Der Farbwert des Bildpunktes an den gegebenen Koordinaten.
     */
    public Color getPix(int x, int y) {
        int col = preparationBuffer.getRGB(x, y);
        return new Color ((col & 0xFF0000) >> 16, (col & 0xFF00) >> 8, col & 0xFF);
    }


    /**
     * Befüllt den Hintergrundspeicher 
     * mit der durch die Rot-, Grün-, und Blaukomponente
     * gegebenen Farbe an die gegebenen Koordinaten.
     *
     * @param x Die x-Koordinate des neu im Hintergrund zu setzenden Punktes. 
     * @param y Die y-Koordinate des neu im Hintergrund zu setzenden Punktes.
     * @param red Der Rotwert der Farbe des neuen Punktes. Zulässiger Bereich: 0-255.
     * @param green Der Grünwert der Farbe des neuen Punktes. Zulässiger Bereich: 0-255.
     * @param blue Der Blauwert der Farbe des neuen Punktes. Zulässiger Bereich: 0-255.
     */
    public void drawPix(int x, int y, int red, int green, int blue) {
        if ((x>=0) && (y>=0) && (x<rawSize.width) && (y<rawSize.height))
            preparationBuffer.setRGB(x, y, getColorCode(red, green, blue));
    }


    /**
     * Befüllt den Hintergrundspeicher 
     * mit der gegebenen Farbe an die gegebenen Koordinaten.
     *
     * @param x Die x-Koordinate des neu im Hintergrund zu setzenden Punktes. 
     * @param y Die y-Koordinate des neu im Hintergrund zu setzenden Punktes.
     * @param col Die Farbe des neuen Punktes. Zulässiger Bereich: 0-255.
     */
    public void drawPix(int x, int y, Color col) {
        if ((x>=0) && (y>=0) && (x<rawSize.width) && (y<rawSize.height))
            preparationBuffer.setRGB(x, y, getColorCode(col));
    }


    /**
     * Leert den Hintergrundspeicher, bzw. setzt alle Pixel des
     * Hintergrundspeichers mit der Hintergrundfarbe, die in
     * {@link #setBackgroundColor(Color)} eingestellt werden kann.
     */
    public void clearViewPort() {
        byte[] backbuf = ((DataBufferByte) background.getRaster().getDataBuffer()).getData();
        byte[] viewPortbuf = ((DataBufferByte) preparationBuffer.getRaster().getDataBuffer()).getData();
        System.arraycopy(backbuf, 0, viewPortbuf, 0, backbuf.length);
    }


    /**
     * Kopiert den Hintergrundspeicher ins Grafikfenster. 
     */
    public void copyBackgroundBuffer() {
        byte[] backbuf = ((DataBufferByte) preparationBuffer.getRaster().getDataBuffer()).getData();
        byte[] viewPortbuf = ((DataBufferByte) viewPort.getRaster().getDataBuffer()).getData();
        System.arraycopy(backbuf, 0, viewPortbuf , 0, backbuf.length);
        mainWindow.repaint();
    }


    /**
     * @return Die Gr��e des ViewPorts in horizontaler Richtung.
     */
    public int getXSize() {
        return rawSize.width;
    }


    /**
     * @return Die Gr��e des ViewPorts in vertikaler Richtung.
     */
    public int getYSize() {
        return rawSize.height;
    }
    
    
    /* ----------------------------- Private Methoden --------------------------------*/


    private void draw(Graphics g) {
        drawColorMode(g);
    }

    private void drawColorMode(Graphics g) {
        g.drawImage(viewPort, 0, 0, null);
    }



    private int getColorCode(Color c) {
        return (c.getRed() << 16) + (c.getGreen() << 8) + c.getBlue();
    }

    private int getColorCode(int red, int green, int blue) {
        return (red << 16) + (blue << 8) + green;
    }

    private void prepareBackground(Color c) {
        int colorCode = getColorCode(c);
        for (int y = 0; y<rawSize.height; y++) {
            for (int x = 0; x<rawSize.width; x++) {
                background.setRGB(x, y, colorCode);
            }
        }
    }

    private Color kompromissFarbe(Color[] colorArray, int abst, boolean random) {
        int r = 0;
        int g = 0;
        int b = 0;
        // luminosity to keep color persistant
        int y = (int) (0.33 * r + 0.5 * g + 0.16 * b);
        for(Color c: colorArray) {
            if (c.getRGB() == -1) {
                continue;
            }
            r += c.getRed();
            g += c.getGreen();
            b += c.getBlue();
        }
        int rand = 0;
        if (random) {
            rand = random(abst);
        }
        r = r/colorArray.length + rand;
        g = g/colorArray.length + rand;
        b = b/colorArray.length + rand;
        r = r + random(5);
        g = g + random(5);
        b = b + random(5);
        return new Color(bounds(r), bounds(g), bounds(b));
    }

    private int random(int abst) {
        int random = (int) ((Math.random() * (125 - -125)+ -125) * abst/100) ;
        return random;
        /*
        *  255 max
        *  0.01 * abst = 0 ... 255 (0 <= abst <= 100)
        *  Generate random number between -1 ... 1
        *  yields a random number between -255 ... 255
        */
        // return (int) (255 * 0.01 * (Math.random() * 2 - 1)) * abst;
    }

    private int bounds(int i) {
        if (i < 0) {
            return 0;
        }
        if (i > 255) {
            return 255;
        }
        return i;
    }
    /* ------------------------- Private Klassen und Interfaces ----------------------- */

    private class ViewPortalWindowListener implements WindowListener {
        @Override
        public void windowClosed(WindowEvent arg0) {
            // do nothing
        }
        @Override
        public void windowClosing(WindowEvent arg0) {
            for (FinalizationMethod fm : finalizer) fm.runFinalization();
        }
        @Override
        public void windowDeactivated(WindowEvent arg0) {
            // do nothing
        }
        @Override
        public void windowDeiconified(WindowEvent arg0) {
            // do nothing
        }
        @Override
        public void windowIconified(WindowEvent arg0) {
            // do nothing
        }
        @Override
        public void windowOpened(WindowEvent arg0) {
            // do nothing
        }
        @Override
        public void windowActivated(WindowEvent e) {
            // do nothing
        }
    }



    private class ViewPortPanel extends JPanel {
        private static final long serialVersionUID = 923837782211117374L;

        public ViewPortPanel(int width, int height) {
            setPreferredSize(new Dimension(rawSize.width,rawSize.height));
        }

        protected void paintComponent(Graphics g ) {
            super.paintComponent(g);
            draw(g);
        }
    }


    private interface FinalizationMethod {
        public void runFinalization();
    }


    public char getNextTypedChar() {
        synchronized(this) {
            return keyHistory.pop();
        }
    }


    public boolean hasNextTypedChar() {
        synchronized(this) {
            return keyHistory.size() > 0;
        }
    }


    private class MyKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            synchronized (ViewPort.this) {
                keyHistory.add(e.getKeyChar());
            }
        }



        @Override
        public void keyPressed(KeyEvent e) {
            // do nothing
        }



        @Override
        public void keyReleased(KeyEvent e) {
            // do nothing
        }
    }



    private void setPix(int x, int y, int r, int g, int b) {
        drawPix(x, y, r, g, b);
    }

    public void fill(int x1, int y1, int x2, int y2) {
        int ym = (y1+y2)/2;
        int xm = (x1+x2)/2;
        setBackgroundColor(Color.WHITE);
        setPix(x1, y1, 0, 0, 255);
        setPix(x2, y1, 0, 0, 255);
        setPix(x1, y2, 0, 255, 255);
        setPix(x2, y2, 0, 255, 255);

        setPix(xm, ym, 255, 0, 0);
        setPix(x1, ym, 255, 255, 0);
        setPix(xm, y1, 255, 255, 0);
        setPix(x2, ym, 255, 0, 255);
        setPix(xm, y2, 255, 0, 255);
        fillRek(x1, y1, x2, y2);
    }

    public void fillRek(int x1, int y1, int x2, int y2) {
        boolean random = true;
        if (x2-x1 < 50 || y2 - y1 < 50) {
            random = false;
        }
        if (x2-x1 < 2 && y2 - y1 < 2) return;


        int xm = (x1+x2)/2;
        int ym = (y1+y2)/2;
        final int abst = 25;

        if (getPix(xm, y1).getRGB() == -1) {
            Color c = kompromissFarbe(new Color[]{getPix(x1, y1), getPix(x2, y1)}, abst, random);
            setPix(xm, y1, c.getRed(), c.getBlue(), c.getGreen());
        }
        if (getPix(x1, ym).getRGB() == -1) {
            Color c = kompromissFarbe(new Color[]{getPix(x1, y1), getPix(x1, y2)}, abst, random);
            setPix(x1, ym, c.getRed(), c.getBlue(), c.getGreen());
        }
        if (getPix(x2, ym).getRGB() == -1) {
            Color c = kompromissFarbe(new Color[]{getPix(x2, y1), getPix(x2, y2)}, abst, random);
            setPix(x2, ym, c.getRed(), c.getBlue(), c.getGreen());
        }
        if (getPix(xm, y2).getRGB() == -1) {
            Color c = kompromissFarbe(new Color[]{getPix(x1, y2), getPix(x2, y2)}, abst, random);
            setPix(xm, y2, c.getRed(), c.getBlue(), c.getGreen());
        }

        if (getPix(xm, ym).getRGB() == -1) {
            Color c = kompromissFarbe(
                    new Color[]{
                            getPix(x1, y1),
                            getPix(xm, y1),
                            getPix(x2, y2),
                            getPix(x1, ym),
                            getPix(x2, ym),
                            getPix(x1, y2),
                            getPix(xm, y2),
                            getPix(x2, y1)
                    }, abst, random);
            setPix(xm, ym, c.getRed(), c.getBlue(), c.getGreen());
        }

        copyBackgroundBuffer();
/*      Boring ass renderer:*/
        fillRek(x1, y1, xm, ym);
        fillRek(xm, y1, x2, ym);
        fillRek(x1, ym, xm, y2);
        fillRek(xm, ym, x2, y2);

/*      Funky ass renderer:
        fillRek(xm, ym, x2, y2);
        fillRek(x1, y1, xm, ym);
        fillRek(xm, y1, x2, ym);
        fillRek(x1, ym, xm, y2);
*/
    }

    public void line(int x1, int y1, int x2, int y2, int r, int g, int b) {

        if (x1 > x2) {   // sicher stellen, dass x1 immer links von x2 ist (oder gleich)
            int z = x1;
            x1 = x2;
            x2 = z;

            z = y1;
            y1 = y2;
            y2 = z;
        }

        int steigungVz = 1;            // Vorzeichen f�r die Steigung ausrechnen
        if (y2 < y1) steigungVz = -1;

        if (x1 == x2) {                // Sonderfall einer senkrechten Linie behandeln.
            while (y1 != y2) {
                setPix(x1, y1, r, b, g);
                y1 = y1 + 1;
            }
        } else {                       // Es liegt keine senkrechte Linie vor

            int fakt = 2000000000/viewPort.getWidth();   // Faktor, mit dem alle Operationen multipliziert werden.
            // Dadurch wird der Mangel an Flieszkommazahlen umgangen.


            // Der Betrag der Steigung:
            int steigung = (((y2 - y1) * steigungVz + 1) * fakt) / (x2 - x1 + 1);
            int schritt = 0;
            while(x1 != x2 || y1 != y2) {
                setPix(x1, y1, r, b, g);
                if (steigung > fakt) {       // steile Steigung 
                    y1 = y1 + steigungVz;
                    schritt = schritt + fakt;
                    if (schritt >= steigung) {
                        x1 = x1 + 1;
                        schritt = schritt - steigung;
                    }
                } else {                     // flache Steigung
                    x1 = x1 + 1;
                    schritt = schritt + steigung;
                    if (schritt >= fakt) {
                        y1 = y1 + steigungVz;
                        schritt = schritt - fakt;
                    }
                }
            }
            setPix(x2, y2, r, b, g);
        }

        copyBackgroundBuffer();
    }

}