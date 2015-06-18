package Uebung08.hashtables;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class VisualArray {

    private Object[] data;
    private int[] readingCount;
    private ViewPort window;
    private static final int minX = 4;
    private static final int minY = 4;
    private int fieldXSize;
    private int fieldYSize;
    private int rowSize;
    private int columnSize;
    private static final int BORDER_SIZE = 40;
    private static final Color BORDER_COLOR = Color.BLACK;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color MARKING_COLOR = Color.green;
    private static final Color FILLED_COLOR = Color.BLUE;
    
    
    
        
    public VisualArray(int size) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        columnSize = ((int)screenSize.getWidth() - BORDER_SIZE) / (minX + 1);
        if (columnSize < size) {
            rowSize = size / columnSize;
            if (size % columnSize > 0) rowSize++;
            if (rowSize * (minY + 1) + 1 >= (int)screenSize.getHeight())
                throw new IllegalArgumentException("Das Array der Größe " + size + " ist zu groß für den Bildschirm.");
            fieldXSize = minX;
            fieldYSize = minY;
        } else {
            columnSize = size;
            rowSize = 1;
            fieldXSize = ((int)screenSize.getWidth() - BORDER_SIZE) / size;
            fieldYSize = fieldXSize;
        }
        data = new Object[size];
        readingCount = new int[size];
        window = new ViewPort("Array der Größe " + size, getTotalXSize(), getTotalYSize());
        window.setBackgroundColor(BACKGROUND_COLOR);
        draw();
    }
    
    
    private int getTotalXSize() {
        return (fieldXSize + 1) * columnSize + 1;
    }

    
    private int getTotalYSize() {
        return (fieldYSize + 1) * rowSize + 1;
    }
    
    // returns the x-coordinate of the upper left point
    // that belongs to the field with the given index.
    // the method ignores the grid, i.e. the point in 
    // question belongs to the field itself, not to its border
    private int getUpperLeftX(int index) {
        return (index % columnSize) * (fieldXSize + 1) + 1; 
    }
    
    // returns the y-coordinate of the upper left point
    // that belongs to the field with the given index.
    // the method ignores the grid, i.e. the point in 
    // question belongs to the field itself, not to its border
    private int getUpperLeftY(int index) {
        return (index / columnSize) * (fieldYSize + 1) + 1; 
    }
    
    private int getXOffset(int count) {
        return count % fieldXSize;
    }
    
    private int getYOffset(int count) {
        return count / fieldXSize;
    }
    
    
    private void drawGrid() {
        int count = 0;
        for (int i = 0; i < rowSize; i++) {
            int fieldsInRow = columnSize;
            if (i == rowSize - 1) {
                fieldsInRow = data.length % columnSize;
                if (fieldsInRow == 0) fieldsInRow = columnSize;
            }
            for (int j = 0; j < fieldsInRow; j++) {
                GrafikPrimitive.zeichneRechteck(window, getUpperLeftX(count)-1, getUpperLeftY(count)-1, 
                        fieldXSize + 2, fieldYSize + 2, BORDER_COLOR);
                count++;
            }
        }
    }
    
    
    private void drawFillingStatus(int index) {
        GrafikPrimitive.fuelleRechteck(window, getUpperLeftX(index), getUpperLeftY(index), 
                fieldXSize, fieldYSize, FILLED_COLOR);
    }
    
    private void drawReadingStatus(int index) {
        for (int i = 0; i < readingCount[index]; i++) {
            window.drawPix(getUpperLeftX(index) + getXOffset(i), 
                    getUpperLeftY(index) + getYOffset(i), MARKING_COLOR);
        }
    }
    
    
    public int length() {
        return data.length;
    }
    
    public void set(int i, Object o) {
        data[i] = o;
        draw();
    }
    
    public Object get(int i) {
        readingCount[i]++;
        draw();
        return data[i];
    }
    
    public void draw() {
        window.clearViewPort();
        drawGrid();
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) drawFillingStatus(i);
            drawReadingStatus(i);
        }
        // malen
        window.copyBackgroundBuffer();
    }
    
    public void close() {
        window.close();
    }

    
    public static void main(String[] a) {
        VisualArray va = new VisualArray(7);
        for (int i = 0; i<1000; i++)
            va.get(3);
    }
    
}
