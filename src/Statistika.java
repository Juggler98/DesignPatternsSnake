import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;
import java.util.Scanner;

/**
 * Tato trieda pracuje so subormi v ktorych su ulozene celkove vysledky hracov pri danych obtiaznostiach.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Statistika {
    private Skore skore;
    
    /**
     * Constructor for objects of class Statistika
     */
    public Statistika() {
        this.skore = new Skore();
    }
    
    /**
     * Nacita vsetky data z prislusneho suboru do HashMap.
     */
    public void nacitajDoHashu(Obtiaznost obtiaznost) {
        File subor;
        switch (obtiaznost) {
            case LAHKA:
                subor = new File("lahka.txt");
                break;
            case STREDNA: 
                subor = new File("stredna.txt");
                break;
            case TAZKA:
                subor = new File("tazka.txt");
                break;
            default: 
                subor = new File("stredna.txt");
        }
        if (!subor.exists()) {
            try {
                subor.createNewFile();
            } catch (Exception e) {
                System.out.println("chyba pri vytvarani noveho suboru");
            }
        }
        try {
            Scanner sc = new Scanner(subor);
            while (sc.hasNext()) {
                String meno = sc.next();
                int points = sc.nextInt();
                this.skore.putToHashMap(meno, points);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("subor je otvoreny alebo iny problem");
        }
    }
    
    /**
     * Prepise cely subor s novymi a usporiadanymi udajmi.
     */
    public void prepis(Obtiaznost obtiaznost) {
        String subor;
        switch (obtiaznost) {
            case LAHKA: 
                subor = "lahka.txt";
                break;
            case STREDNA:
                subor = "stredna.txt";
                break;
            case TAZKA:
                subor = "tazka.txt";
                break;
            default:
                subor = "stredna.txt";
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(subor));
            this.skore.zorad();
            for (int i = 0; i < this.skore.getMenaArrayList().size(); ++i) {
                writer.write(this.skore.getMenaArrayList().get(i) + " " + this.skore.getSkoreArrayList().get(i));
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("nastala chyba v prepisovani subora v statistike");
        }
    }
    
    public Skore getSkore() {
        return this.skore;
    }
}
