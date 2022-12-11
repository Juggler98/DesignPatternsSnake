import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 * V tejto triede je vytvoreny zoznam (HashMap), do ktoreho sa zo suboru uklada 
 * skore prisluchajucej obtiaznosti.
 * HashMap nam zamedzi duplicitne mena hracov.
 * 
 * @author (Adam Beliansky) 
 * @version (a version number or a date)
 */
public class Skore {
    private String meno;
    private int skore;
    private HashMap<String, Integer> hashMap;
    private LinkedHashSet<Integer> linkedHashSet;
    private ArrayList<Integer> skoreArrayList;
    private ArrayList<String> menaArrayList;
    
    /**
     * Vytvori HashMap a pomocne Listy a Set na zoradenie statistiky podla velkosti skore.
     */
    public Skore() {
        this.hashMap  = new HashMap<String, Integer>();
        this.skoreArrayList = new ArrayList<Integer>();
        this.menaArrayList = new ArrayList<String>();
        this.linkedHashSet = new LinkedHashSet<Integer>();
    }
    
    /**
     * Vlozi udaj do HashMap.
     */
    public void putToHashMap(String meno, int skore) {
        this.hashMap.put(meno, skore);
    }
    
    public HashMap<String, Integer> getHashMap() {
        return this.hashMap;
    }
    
    public ArrayList<Integer> getSkoreArrayList() {
        return this.skoreArrayList;
    }
    
    public ArrayList<String> getMenaArrayList() {
        return this.menaArrayList;
    }
    
    /**
     * Zoradi vysledky hier podla velkosti skore.
     */
    public void zorad() {
        //prida skore do ArrayListu a zoradi od najvacsieho
        this.skoreArrayList.clear();
        for (String name : this.hashMap.keySet()) {
            this.skoreArrayList.add(this.hashMap.get(name));
        }
        Collections.sort(this.skoreArrayList);
        Collections.reverse(this.skoreArrayList);
        //prida skore do LinkedHashSetu. To sposobi zamadzenie duplicitnych hodnot
        this.linkedHashSet.clear();
        for (int points : this.skoreArrayList) {
            this.linkedHashSet.add(points);
        }
        //zoradi mena podla prislusneho skore
        this.menaArrayList.clear();
        for (Integer points : this.linkedHashSet) {
            for (String name : this.hashMap.keySet()) {
                if (this.hashMap.get(name) == points.intValue()) {
                    this.menaArrayList.add(name);
                }
            }
        }
    }
    
    /**
     * Vypise uz zoradene vysledky.
     */
    public String vypis() {
        this.zorad();
        String vypis = "";
        for (int i = 0; i < this.menaArrayList.size(); ++i) {
            vypis += this.menaArrayList.get(i) + " " + this.skoreArrayList.get(i) + "\n";
        }
        return vypis;
    }
}
