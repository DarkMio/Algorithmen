package hanoi;

import java.awt.Color;

import simplevisuals.ViewPort;

public class ScheibenStapel {


    private static final int delay = 10;
    private static final int stangenBreite = 10;
    private static final Color farbe = new Color(100, 35, 0);
    
    private Scheibe[] stapel;
    private int groesze;
    private int indexObersteScheibe;
    private int xMitte;
    
    private TuermeVonHanoi myGame;
    
    
    
    public ScheibenStapel(TuermeVonHanoi g, int size, int xMitte) {
        stapel = new Scheibe[size];
        indexObersteScheibe = -1;
        this.xMitte = xMitte;
        groesze = size;
        myGame = g;
    }
    
    
    
    public void verschiebeObersteScheibeNach(ScheibenStapel anderer) {
        anderer.legeScheibeDrauf(nimmScheibeRunter());
        myGame.draw();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException exc) {
            throw new RuntimeException(exc);
        }
    }
    
    public int getIndexDerOberstenScheibe() {
        return indexObersteScheibe;
    }
    
    
    public void fill() {
        for (int i = groesze; i > 0; i--) legeScheibeDrauf(new Scheibe(i));
    }
    
    private boolean istLeer() {
        return indexObersteScheibe == -1;
    }
    
    private boolean istVoll() {
        return indexObersteScheibe == groesze - 1;
    }
    
    
    private void legeScheibeDrauf(Scheibe s) {
        if (istVoll()) throw new RuntimeException("Kann keine Scheibe mehr auf Stapel der Größe " + groesze + " legen.");
        if (istLeer()) {
            stapel[++indexObersteScheibe] = s;
            return;
        }
        if (stapel[indexObersteScheibe].darfObenDrauf(s)) stapel[++indexObersteScheibe] = s;
        else throw new RuntimeException("Kann Scheibe der Größe " + s.getSize() + 
                                        " nicht auf Scheibe der Größe " + 
                                        stapel[indexObersteScheibe].getSize() + 
                                        " drauflegen.");
    }
    
    private Scheibe nimmScheibeRunter() {
        if (istLeer()) throw new RuntimeException("Kann von einem leeren Stapel keine Scheibe runternehmen.");
        else return stapel[indexObersteScheibe--];
    }
    
    
    private void drawStange(ViewPort vp) {
        int hoehe = (stapel.length+1)*Scheibe.getHoehe();
        GrafikPrimitive.zeichenRechteck(vp, xMitte - stangenBreite/2, 
                                        vp.getYSize()-hoehe, stangenBreite, hoehe, farbe);
    }
    
    public void draw(ViewPort vp) {
        drawStange(vp);
        for (int i = 0; i<=indexObersteScheibe; i++) {
            stapel[i].draw(vp, xMitte, vp.getYSize() - ((i+1) * Scheibe.getHoehe()));
        }
    }
    
}
