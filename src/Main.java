import javax.swing.*;

/**
 * Hlavna trieda, ktora vytvori instanciu triedy Hra a teda spusti program.
 * 
 * @author (Adam Beliansky) 
 * @version (a version number or a date)
 */
public class Main {
    /**
     * Constructor for objects of class Main
     */
    public Main() {
        // initialise instance variables
    }
    
    /**
     * Spustenie hry.
     */
    public static void main(String[] args) {
        JFrame jFrame;
        jFrame = new JFrame();

        jFrame.add(new Hra());

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        //jFrame.setLocationRelativeTo(null);
        jFrame.pack();
        jFrame.setResizable(false);
    }
}
