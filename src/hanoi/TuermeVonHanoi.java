package hanoi;

import simplevisuals.ViewPort;

public class TuermeVonHanoi {

    private ScheibenStapel[] sStapel;
    private static int horAbstand = 10;
    private static int vertAbstand = 10;
    private ViewPort window;
    
    public TuermeVonHanoi(int anzahlScheiben, int vpBreite, int vpHoehe) {
        Scheibe.setHoehe((vpHoehe - vertAbstand) / anzahlScheiben);
        Scheibe.setBreitenFakt((vpBreite - horAbstand * 4)/(3 * anzahlScheiben));
        
        int maxScheibenBreite = Scheibe.getBreitenFakt()*anzahlScheiben;
        sStapel = new ScheibenStapel[3];        
        sStapel[0] = new ScheibenStapel(this, anzahlScheiben, horAbstand + maxScheibenBreite/2);
        sStapel[1] = new ScheibenStapel(this, anzahlScheiben, 2*horAbstand + maxScheibenBreite + maxScheibenBreite/2);
        sStapel[2] = new ScheibenStapel(this, anzahlScheiben, 3*horAbstand + 2*maxScheibenBreite + maxScheibenBreite/2);

        sStapel[0].fill();
        
        window = new ViewPort("Türme von Hanoi", 800, 600);

    }
    
    
    public void draw() {
        window.clearViewPort();
        for (int i = 0; i < 3; i++) sStapel[i].draw(window);
        window.copyBackgroundBuffer();
    }
    

    /**
     * Verschiebt einen Scheibenstapel von der Stange mit dem Index von
     * zur Stange mit dem Index nach.
     */
    public void verschiebe(int von, int untersteScheibe, int nach) {
        int cache = 3 - (nach + von);
        if (untersteScheibe > -1) {
            verschiebe(von, untersteScheibe-1, cache);
            sStapel[von].verschiebeObersteScheibeNach(sStapel[nach]);
            verschiebe(cache, untersteScheibe-1, nach);
        }
    }
    
    public ViewPort getViewPort() {
        return window;
    }
    
    public static void main(String[] a) {
        TuermeVonHanoi t = new TuermeVonHanoi(10, 800, 600);
        t.draw();
        t.verschiebe(0, 9, 1);
    }
}
