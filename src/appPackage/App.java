package appPackage;

import command.Command;
import command.ICommand;
import command.PauseCommand;
import flyweight.MyImage;
import movingModels.Hadik;
import movingModels.IMovingObject;
import observer.IObserver;
import observer.ObservableKeyAdapter;
import observer.Observable;
import other.Hrac;
import other.Obtiaznost;
import staticModels.Apple;
import staticModels.Food;
import staticModels.Jedlo;
import utility.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Tato metoda riadi najdolezitejsie procesy ako pohyb hada, kontroly kolizie, vstup a vystup udajov, nastavenie naročnosti...
 * Tak isto vytvari vacsinu instancii tried ako sú moving.Hadik, staticModels.Jedlo, other.Hrac, HraciePole.
 *
 * @author (Adam Beliansky)
 * @version (a version number or a date)
 */
public class App extends JPanel implements ActionListener, IObserver {
    private final LinkedList<IMovingObject> movingObjects = new LinkedList<>();
    private Food apple;
    private Hrac hrac;
    private Obtiaznost obtiaznost = Obtiaznost.STREDNA;
    private boolean pauza;
    private final Timer timer;
    private static App instance;
    private static final LinkedList<MyImage> images = new LinkedList<>();
    public final PauseCommand pauseCommand = new PauseCommand(this);

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    private App() {
        timer = new Timer(Config.middleDelay, this);
        timer.start();

        init();
    }

    public static MyImage getOrAddImage(String resource) {
        for (MyImage m : images) {
            if (m.resource.equals(resource)) {
                return m;
            }
        }
        MyImage myImage = new MyImage(resource);
        images.add(myImage);
        return myImage;
    }

    private void init() {
        ObservableKeyAdapter observableKeyAdapter = new ObservableKeyAdapter();
        addKeyListener(observableKeyAdapter);

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

        movingObjects.add(new Hadik(new int[]{KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT}));
        movingObjects.add(new Hadik(new int[]{KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D}));
        movingObjects.add(new Hadik(new int[]{KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD6}));


        for (IMovingObject s : movingObjects) {
            observableKeyAdapter.attach((IObserver) s);
        }
        observableKeyAdapter.attach(this);

        this.apple = new Apple("src/resources/apple.png");

        setBackground(Color.black);
        setPreferredSize(new Dimension(Config.rozmerPlatna, Config.rozmerPlatna));
        setFocusable(true);

        //JOptionPane.showMessageDialog(null, "Stlacenim lubovolnej sipky zacnes hru.\nUkoncis ju stlacenim Esc.\nPre pauzu stlac medzernik.");


        this.nastavObtiaznost();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        if (true) {

            g.drawImage(apple.getMyImage().image, apple.getPosition().x, apple.getPosition().y, this);

            for (IMovingObject hadik : movingObjects) {

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

    /**
     * Toto je mensi cheat. Pri stlaceni klavesy Q sa prida hadikovy dalsi clanok, ale skore sa nazvacsi. Sluzi len na testovanie funkcnosti predlzovania.
     */
    private void newPoint() {
        for (IMovingObject h : movingObjects) {
            Hadik hadik = (Hadik) h;
            hadik.pridajClanok();
        }
    }

    /**
     * Tato metoda sa opakuje podla dlzky tiku nastaveneho v Managerovi.
     * Kontroluje koliziu, styk s jedlom a posuva hadika.
     */
    private void tik() {
        checkApple();
        for (IMovingObject movingObject : movingObjects) {
            movingObject.move();
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
    private void easy() {
        timer.setDelay(Config.easyDelay);
    }

    /**
     * Dalsi cheat. Stlacenim klavesu 2 na anglickej klavesnici nad pismenami sa zmeni rychlost hadika. Sluzi len na testovanie
     */
    private void middle() {
        timer.setDelay(Config.middleDelay);
    }

    /**
     * Dalsi cheat. Stlacenim klavesu 3 na anglickej klavesnici nad pismenami sa zmeni rychlost hadika. Sluzi len na testovanie
     */
    private void hard() {
        timer.setDelay(Config.hardDelay);
    }

    /**
     * Pozastavi/spusti pohyb hadika.
     */
    public void pause() {
        pauza = !pauza;
        if (pauza) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    /**
     * Vypne hru pri stlaceni Esc.
     */
    private void cancel() {
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
//                this.hadik.getPredokX() > (utility.Config.rozmerPlatna / utility.Config.rozmerBodu - 1) ||
//                this.hadik.getPredokY() < 0 ||
//                this.hadik.getPredokY() > (utility.Config.rozmerPlatna /  utility.Config.rozmerBodu) - 1) {
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
//        this.smer = position.Smer.STOJ;
        this.hrac.zistiNajvyssie(this.obtiaznost);
        JOptionPane.showMessageDialog(null, hrac.getMeno() + " prehral si. Tvoje skore: " + this.hrac.getSkore(), "Skore", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(null, this.hrac.getStatistika().getSkore().vypis(), "Leaderboard" + " " + this.obtiaznost.toString().toLowerCase(), JOptionPane.PLAIN_MESSAGE);
        //this.hadik.novyHadik();
//        this.apple.skryJedlo();
//        this.apple.zmenPoziciu();
        this.hrac.setSkore(-this.hrac.getSkore());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tik();
    }

    private void checkApple() {
        for (IMovingObject hadik : movingObjects) {
            Hadik h = (Hadik) hadik;
            if ((h.telo.get(0).equals(apple.getPosition()))) {
                h.pridajClanok();
                apple.move();
            }
        }
    }

    @Override
    public void update(Observable observable) {
        ObservableKeyAdapter observableKeyAdapter = (ObservableKeyAdapter) observable;
        int key = observableKeyAdapter.getPressedKey();
        switch (key) {
            case KeyEvent.VK_N:
                newPoint();
                break;
            case KeyEvent.VK_R:
                restart();
                break;
            case KeyEvent.VK_CANCEL:
                cancel();
                break;
            case KeyEvent.VK_SPACE:
                pauseCommand.execute();
//                for (ICommand command : commands) {
//                    if (command instanceof PauseCommand) {
//                        command.execute();
//                    }
//                }
                break;
            case KeyEvent.VK_1:
                easy();
                break;
            case KeyEvent.VK_2:
                middle();
                break;
            case KeyEvent.VK_3:
                hard();
                break;
        }

    }

}