package other;

import other.Obtiaznost;
import other.Statistika;

/**
 * Tato trieda pocita hracovi aktualne skore, takisto overuje nove najvyssie skore. Pripadne zmeny posiela do triedy Statistika.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hrac {
    private int skore;
    private final String meno;
    private final Statistika statistika;
    
    /**
     * Vytvori hraca. Ak je novy, tak ho prida do HashMap.
     */
    public Hrac(String meno, Obtiaznost obtiaznost) {
        this.meno = meno;
        this.statistika = new Statistika();
        this.statistika.nacitajDoHashu(obtiaznost);
        this.skore = 0;
        if (!this.statistika.getSkore().getHashMap().containsKey(this.meno)) {
            this.statistika.getSkore().getHashMap().put(this.meno, this.skore);
        }
        this.statistika.prepis(obtiaznost);
    }
    
    public void setSkore(int skore) {
        this.skore += skore;
    }
    
    /**
     * Overi ci hrac dosiahol nove skore, ak ano prepise to stare.
     */
    public void zistiNajvyssie(Obtiaznost obtiaznost) {
        if (this.statistika.getSkore().getHashMap().get(this.meno) < this.skore) {
            this.statistika.getSkore().getHashMap().put(this.meno, this.skore);
            this.statistika.prepis(obtiaznost);
        }
    }

    public String getMeno() {
        return meno;
    }

    public int getSkore() {
        return this.skore;
    }
    
    public Statistika getStatistika() {
        return this.statistika;
    }
}
