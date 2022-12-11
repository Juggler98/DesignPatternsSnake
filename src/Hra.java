import sun.management.jdp.JdpGenericPacket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Tato metoda riadi najdolezitejsie procesy ako pohyb hada, kontroly kolizie, vstup a vystup udajov, nastavenie naročnosti...
 * Tak isto vytvari vacsinu instancii tried ako sú Hadik, Jedlo, Hrac, HraciePole.
 *
 * @author (Adam Beliansky)
 * @version (a version number or a date)
 */
public class Hra extends JPanel implements ActionListener, IObserver {
    private ArrayList<IMovingObject> snakes = new ArrayList<>();
    private Jedlo jedlo;
    private Hrac hrac;
    private Obtiaznost obtiaznost = Obtiaznost.STREDNA;
    private boolean pauza;

    private final Timer timer;

    public Hra() {
//        Manazer manazer = new Manazer();
//
//        addKeyListener(manazer);

        MyKeyAdapter myKeyAdapter = new MyKeyAdapter();
        addKeyListener(myKeyAdapter);

//        try {
//            this.meno = JOptionPane.showInputDialog(null, "Tvoje meno:", "Meno", JOptionPane.QUESTION_MESSAGE).toLowerCase();
//            if (this.meno.equals("")) {
//                this.meno = "anonym";
//            }
//        } catch (NullPointerException e) {
//            this.meno = "anonym";
//        }
//        String[] moznosti = {"Lahka", "Stredna", "Tazka"};
//        int odpoved = JOptionPane.showOptionDialog(null, "Zvol si obtiaznost:", "Obtiaznost", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, moznosti, null);
//        switch (odpoved) {
//            case 0:
//                this.obtiaznost = Obtiaznost.LAHKA;
//                break;
//            case 1:
//                this.obtiaznost = Obtiaznost.STREDNA;
//                break;
//            case 2:
//                this.obtiaznost = Obtiaznost.TAZKA;
//                break;
//            default:
//                this.obtiaznost = Obtiaznost.STREDNA;
//        }
        this.pauza = false;
        this.hrac = new Hrac("a", this.obtiaznost);
        int[] keys = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
        int[] keys2 = {KeyEvent.VK_W,  KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D};

        snakes.add(new Hadik(keys));
        snakes.add(new Hadik(keys2));


        myKeyAdapter.attach((IObserver) snakes.get(0));
        myKeyAdapter.attach((IObserver) snakes.get(1));
        myKeyAdapter.attach(this);
//        manazer.spravujObjekt(snakes.get(0));
//        manazer.spravujObjekt(snakes.get(1));
//        manazer.spravujObjekt(this);

        this.jedlo = new Jedlo("src/resources/apple.png");

        setBackground(Color.black);
        setPreferredSize(new Dimension(Config.rozmerPlatna, Config.rozmerPlatna));
        setFocusable(true);

        //JOptionPane.showMessageDialog(null, "Stlacenim lubovolnej sipky zacnes hru.\nUkoncis ju stlacenim Esc.\nPre pauzu stlac medzernik.");

        timer = new Timer(Config.middleDelay, this);
        timer.start();
        this.nastavObtiaznost();
    }


    private void initGame() {

    }

    Random random = new Random();


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }


    private void doDrawing(Graphics g) {
        if (true) {
            g.drawImage(jedlo.getImage(), jedlo.getPosition().x, jedlo.getPosition().y, this);
            for (IMovingObject hadik : snakes) {
                Hadik h = (Hadik) hadik;
                for (int z = 0; z < h.telo.size(); z++) {
                    if (z == 0) {
                        g.drawImage(h.getHead(), h.telo.get(z).x, h.telo.get(z).y, this);
                    } else {
                        g.drawImage(h.getBody(), h.telo.get(z).x, h.telo.get(z).y, this);
                    }
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {

        }
    }

//    /**
//     * Prijima spravu z Manazera a na stlacenie prislusnej sipky reaguje.
//     */
//    public void posunVpravo(int key) {
//        for (Hadik hadik : snakes) {
//            hadik.right(key);
//        }
//
//    }
//
//    /**
//     * Prijima spravu z Manazera a na stlacenie prislusnej sipky reaguje.
//     */
//    public void posunVlavo(int key) {
//        for (Hadik hadik : snakes) {
//            hadik.left(key);
//        }
//    }
//
//    /**
//     * Prijima spravu z Manazera a na stlacenie prislusnej sipky reaguje.
//     */
//    public void posunHore(int key) {
//        for (Hadik hadik : snakes) {
//            hadik.up(key);
//        }
//    }
//
//    /**
//     * Prijima spravu z Manazera a na stlacenie prislusnej sipky reaguje.
//     */
//    public void posunDole(int key) {
//        for (Hadik hadik : snakes) {
//            hadik.down(key);
//        }
//    }

    /**
     * Toto je mensi cheat. Pri stlaceni klavesy Q sa prida hadikovy dalsi clanok, ale skore sa nazvacsi. Sluzi len na testovanie funkcnosti predlzovania.
     */
    public void newPoint() {
        for (IMovingObject h : snakes) {
            Hadik hadik = (Hadik) h;
            hadik.pridajClanok();
        }
    }

    /**
     * Tato metoda sa opakuje podla dlzky tiku nastaveneho v Managerovi.
     * Kontroluje koliziu, styk s jedlom a posuva hadika.
     */
    public void tik() {
        checkApple();
        for (IMovingObject hadik : snakes) {
            hadik.move();
        }
        repaint();

        if (!this.pauza) {

            // this.hadik.pridajClanok();
            this.hrac.setSkore(1);
            if (this.skontrolujKoliziu()) {
                this.restart();
            }
        }
    }

    /**
     * Dalsi cheat. Stlacenim klavesu 1 na anglickej klavesnici nad pismenami sa zmeni rychlost hadika. Sluzi len na testovanie
     */
    public void easy() {
        timer.setDelay(Config.easyDelay);
    }

    /**
     * Dalsi cheat. Stlacenim klavesu 2 na anglickej klavesnici nad pismenami sa zmeni rychlost hadika. Sluzi len na testovanie
     */
    public void middle() {
        timer.setDelay(Config.middleDelay);
    }

    /**
     * Dalsi cheat. Stlacenim klavesu 3 na anglickej klavesnici nad pismenami sa zmeni rychlost hadika. Sluzi len na testovanie
     */
    public void hard() {
        timer.setDelay(Config.hardDelay);
    }

    /**
     * Pozastavi/spusti pohyb hadika.
     */
    public boolean pause() {
        this.pauza = !this.pauza;
        return this.pauza;
    }

    /**
     * Vypne hru pri stlaceni Esc.
     */
    public void cancel() {
        System.exit(0);
    }

    /**
     * Nastavi rychlost pohybu hadika podla prislusnej obtiaznosti.
     */
    private void nastavObtiaznost() {
        switch (this.obtiaznost) {
            case LAHKA:
                easy();
                break;
            case STREDNA:
                middle();
                break;
            case TAZKA:
                hard();
                break;
        }
    }

    /**
     * Skontroluje koliziu.
     */
    private boolean skontrolujKoliziu() {
        //kontrola kolizie pre zjedenie sameho seba
//        for (int i = 0; i < this.hadik.getVelkost() - 3; ++i) {
//            if (this.hadik.getX(this.hadik.getVelkost() - 1) == this.hadik.getX(i) &&
//                    this.hadik.getY(this.hadik.getVelkost() - 1) == this.hadik.getY(i)) {
//                return true;
//            }
//        }
//        //kontrola narazenia do okraja
//        if (this.hadik.getPredokX() < 0 ||
//                this.hadik.getPredokX() > (Config.rozmerPlatna / Config.rozmerBodu - 1) ||
//                this.hadik.getPredokY() < 0 ||
//                this.hadik.getPredokY() > (Config.rozmerPlatna /  Config.rozmerBodu) - 1) {
//            return true;
//        }
        return false;
    }

    /**
     * Ak nastane kolizia, spusti sa metoda restart a hra sa restaruje.
     * <p>
     * Tak isto tu je mensi cheat.
     * Stlacenim klavesy W sa tak isto spusti tato metoda.
     * Tato funkcionalita sluzi len na testovanie.
     */
    public void restart() {
//        this.smer = Smer.STOJ;
        this.hrac.zistiNajvyssie(this.obtiaznost);
        JOptionPane.showMessageDialog(null, hrac.getMeno() + " prehral si. Tvoje skore: " + this.hrac.getSkore(), "Skore", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, this.hrac.getStatistika().getSkore().vypis(), "Leaderboard" + " " + this.obtiaznost.toString().toLowerCase(), JOptionPane.PLAIN_MESSAGE);
        //this.hadik.novyHadik();
        this.jedlo.skryJedlo();
        this.jedlo.zmenPoziciu();
        this.hrac.setSkore(-this.hrac.getSkore());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tik();
    }

    private void checkApple() {
        for (IMovingObject hadik : snakes) {
            Hadik h = (Hadik) hadik;
            if ((h.telo.get(0).equals(jedlo.getPosition()))) {
                h.pridajClanok();
                jedlo.zmenPoziciu();
            }
        }
    }

    @Override
    public void update(Observable observable) {

    }

//    private class MyKeyAdapter extends KeyAdapter {
//        @Override
//        public void keyPressed(KeyEvent e) {
//            int key = e.getKeyCode();
//            switch (key) {
//                case KeyEvent.VK_DOWN:
//                case KeyEvent.VK_S:
//                    posunDole(key);
//                    break;
//                case KeyEvent.VK_UP:
//                case KeyEvent.VK_W:
//                    posunHore(key);
//                    break;
//                case KeyEvent.VK_LEFT:
//                case KeyEvent.VK_A:
//                    posunVlavo(key);
//                    break;
//                case KeyEvent.VK_RIGHT:
//                case KeyEvent.VK_D:
//                    posunVpravo(key);
//                    break;
//                case KeyEvent.VK_ENTER:
//                    break;
//                case KeyEvent.VK_ESCAPE:
//                    zrus();
//                    break;
//                case KeyEvent.VK_Q:
//                    novy();
//                    break;
//                case KeyEvent.VK_R:
//                    restart();
//                    break;
//                case KeyEvent.VK_1:
//                    lahka();
//                    break;
//                case KeyEvent.VK_2:
//                    stredna();
//                    break;
//                case KeyEvent.VK_3:
//                    tazka();
//                    break;
//                case KeyEvent.VK_SPACE:
//                    pauza();
//                    break;
//                default:
//                    break;
//            }
//        }
//    }


}