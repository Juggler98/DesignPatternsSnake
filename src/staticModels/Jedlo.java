package staticModels;

import movingModels.Hadik;
import utility.Config;
import utility.Position;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Jedlo {
    private Image image;


    /**
     * Vytvori prve jedlo a nastavi pociatocne hodnoty.
     */
    public Jedlo(String resource) {

    }

    /**
     * Sryje jedlo.
     */
    public void skryJedlo() {
//        this.hraciePole.skry(this.x, this.y);
    }

//    public Position getPosition() {
//        return position;
//    }

    public Image getImage() {
        return image;
    }


    public void zmenPoziciu() {

    }

    public void action(Hadik hadik) {
        hadik.action(this);
    }


}
