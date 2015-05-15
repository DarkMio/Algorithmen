package Uebung05.sorting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Container {

    private JFrame mainWindow; 
    private BufferedImage viewPort;
    private int[] background;   
    private int dataSize;
    private SortingObject[] data;
    private StoragePanel panel;
    private int delay = 0;
    private Dimension screenSize;
    private Dimension bufSize;
    private int maxBarHeight;
    private static int containerCount = 0;
    private static final int CONTAINER_PER_SCREEN = 3;
    private static final int VERT_BORDER_SIZE = 20;
    
    
    /**
     * Konstruiert einen neuen Container mit size vielen Zufallsobjekten.
     * @param name Der Name des Containers (wird in der Titelleiste angezeigt).
     * @param size Die Anzahl der Objekte im Container.
     */
    public Container(String name, int size) {
        initData(size);
        
        delay = 50;

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
        maxBarHeight = screenSize.height/CONTAINER_PER_SCREEN - 20;
        bufSize = new Dimension(screenSize.width - VERT_BORDER_SIZE, maxBarHeight);
        
        if (size > bufSize.width) throw new RuntimeException("Size too large: " + size + ". Maximal allowed size is: " + bufSize.width);
        if (size <= 0) throw new RuntimeException("Size too small: " + size + ". Minimal allowed size is: " + 1);
        
        viewPort = new BufferedImage(bufSize.width, bufSize.height, BufferedImage.TYPE_3BYTE_BGR);
        panel = new StoragePanel();
        mainWindow = new JFrame(name);
        
        int xk = VERT_BORDER_SIZE / 4;
        int yk = containerCount * maxBarHeight;
        
        mainWindow.setLocation(xk, yk);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel globalPanel = new StoragePanel();
        globalPanel.setLayout(new BoxLayout(globalPanel, BoxLayout.Y_AXIS));
        mainWindow.getContentPane().add(panel);
        mainWindow.pack();
        mainWindow.setVisible(true);
        
        background = new int[bufSize.width * bufSize.height];
        setBackgroundColor(255,255,255);
        clearViewPort();
        containerCount = (++containerCount) % CONTAINER_PER_SCREEN;
        
    }
    
    
    private void initData(int size) {
        dataSize = size;
        data = new SortingObject[dataSize];
    	for (int i = 0; i<size; i++) {
    		data[i] = new SortingObject();
    	}
    }
    
    
    
    private class StoragePanel extends JPanel {
		private static final long serialVersionUID = 983771892332L;
		public StoragePanel() {
            setPreferredSize(new Dimension(bufSize.width, bufSize.height));
        }
        
        protected void paintComponent(Graphics g ) {
            super.paintComponent(g);
            draw(g);
        }
    }
    
    
    /**
     * Schreibt das gegebene value an die Position mit dem gegebenen index.
     * @param index Der index an den das Objekt geschrieben werden soll.
     * @param value Das Objekt, das in den Container geschrieben werden soll.
     */
    public void set(int index, SortingObject value) {
        if ((index <0) || (index > data.length)) throw new
            IllegalArgumentException("Der Index " + index + " liegt außerhalb des gültigen Datenbereichs (0 - " + data.length + ").");
        data[index] = value;
        redraw();
        delay();
    }
    
    /**
     * Liest das Objekt, das im Container an der durch index gegebenen
     * Stelle liegt aus.
     * @param index Der Index des auszulesenden Objekts.
     * @return Das Objekt im Container, das an der durch index identifizierten Stelle liegt.
     */
    public SortingObject get(int index) {
        if ((index <0) || (index > data.length)) throw new
            IllegalArgumentException("Der Index " + index + " liegt außerhalb des gültigen Datenbereichs (0 - " + data.length + ").");
        delay();
        return data[index];
    }
    
        
    
    private void delay() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e1) {
            e1.printStackTrace(System.err);
        }
    }
    
        
    
    
    private void setBackgroundColor(int red, int green, int blue) {
        Color c = new Color(red, green, blue);
        prepareBackground(c);
    }
    
    private void prepareBackground(Color c) {
        int colorCode = getColorCode(c);
        for (int i=0; i<bufSize.height*bufSize.width; i++) {
            background[i] = colorCode;
        }
    }

    
    
    private void draw(Graphics g) {
        clearViewPort();
        int normBarWidth = bufSize.width / dataSize;
        int rest = bufSize.width % dataSize;
        Color barCol = new Color(255, 0, 0);
        int xOffset = 0;
        int fakt = 10000;
        int step = (rest * fakt)  / dataSize;
        int k = 0;
        for (int i = 0; i<dataSize; i++) {
        	SortingObject so = data[i];
        	int h = so.normalizedValue(bufSize.height);
        	int w = normBarWidth;
        	if (k > fakt) {
        		w++;
        		k = k - fakt;
        	}
        	drawBar(xOffset, bufSize.height - h - 1, w, h, barCol);
        	k = k + step;
        	xOffset = xOffset + w;
        }
        g.drawImage(viewPort, 0,0,null);
    }
    
    
    
    private void drawBar(int xUL, int yUL, int width, int height, Color col) {
    	int colorCode = getColorCode(col);
    	int black = getColorCode(0, 0, 0);
    	for (int x=0; x<width; x++) {
        	for (int y=0; y<height; y++) {
        		drawPix(x + xUL,y + yUL, colorCode);
        	}    		
    	}
    	for (int x=0; x<width; x++) {
    		drawPix(xUL + x, yUL, black);
    		drawPix(xUL + x, yUL + height - 1, black);
    	}
    	for (int y=0; y<height; y++) {
    		drawPix(xUL, y + yUL, black);
    		drawPix(xUL + width - 1, y + yUL, black);
    	}    		
    }
    
    
    private void drawPix(int x, int y, int col) {
    	if (x < 0 || x >= bufSize.width || y < 0 || y>= bufSize.height) {
    		throw new RuntimeException("Point out of range: (" + x + ", " + y + ")");
    	}
    	viewPort.setRGB(x, y, col);
    }
    
    
    private int getColorCode(Color c) {
        return (c.getBlue() << 16) + (c.getGreen() << 8) + c.getRed();
    }
    
    private int getColorCode(int red, int green, int blue) {
        return (red << 16) + (green << 8) + blue;
    }

    
    private void clearViewPort() {
        viewPort.setRGB(0,0, bufSize.width, bufSize.height, background, 0, bufSize.width);
    }
    
    
    private void redraw() {
        mainWindow.repaint();
    }
    
    
    /**
     * @return Die Anzahl der im Container gespeicherten Objekte.
     */
    public int getSize() {
    	return dataSize;
    }
    
    
    /**
     * Gibt die Verzögerung in Millisekunden zurück, die der Container
     * bei jedem Lese- und Schreibzugriff wartet. 
     */
    public int getDelay() {
    	return delay;
    }
    
    
    /**
     * Legt die Verzögerung in Millisekunden fest, die der Container
     * bei jedem Lese- und Schreibzugriff warten soll. 
     */
    public void setDelay(int d) {
        delay = d;
    }
        
    
    
	
}
