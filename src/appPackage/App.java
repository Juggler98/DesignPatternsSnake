package appPackage;

import command.*;
import controllable.ControllableObject;
import controllable.Hadik;
import movingModels.IMovingObject;
import observer.IObserver;
import observer.ObservableKeyAdapter;
import observer.Observable;
import utility.Obtiaznost;
import hittableModels.IServant;
import hittableModels.*;
import utility.Config;
import utility.Position;
import utility.PositionImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Tato metoda riadi najdolezitejsie procesy ako pohyb hada, kontroly kolizie, vstup a vystup udajov, nastavenie naročnosti...
 * Tak isto vytvari vacsinu instancii tried ako sú moving.Hadik, staticModels.Jedlo, other.Hrac, HraciePole.
 *
 * @author (Adam Beliansky)
 * @version (a version number or a date)
 * <p>
 * <p>
 * smer state?
 * position ?
 */
public class App extends JPanel implements ActionListener, IObserver {
    private final LinkedList<ControllableObject> controllableObjects = new LinkedList<>();
    private final LinkedList<Hittable> hittableObjects = new LinkedList<>();
    private Obtiaznost obtiaznost = Obtiaznost.STREDNA;
    private boolean pauza;
    private final Timer timer;
    private static App instance;

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    private App() {
//        JOptionPane.showMessageDialog(null, "Nahraj 100 bodov. Ovladanie sipkami.\nPre pauzu stlac medzernik.");
//
//        String[] moznosti = {"Lahka", "Stredna", "Tazka"};
//        int odpoved = JOptionPane.showOptionDialog(null, "Zvol si obtiaznost:", "Obtiaznost", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, moznosti, null);
//        switch (odpoved) {
//            case 0:
//                this.obtiaznost = Obtiaznost.LAHKA;
//                break;
//            case 2:
//                this.obtiaznost = Obtiaznost.TAZKA;
//                break;
//            default:
//                this.obtiaznost = Obtiaznost.STREDNA;
//        }

        timer = new Timer(Config.middleDelay, this);
        timer.start();

        init();
    }

    private ObservableKeyAdapter observableKeyAdapter;

    private void init() {
        init2();
        this.pauza = false;

        setBackground(Color.black);
        setPreferredSize(new Dimension(Config.rozmerPlatna, Config.rozmerPlatna));
        setFocusable(true);

        nastavObtiaznost(obtiaznost);

        Commands.addCommand(KeyEvent.VK_N, new NewPointCommand(this));
        Commands.addCommand(KeyEvent.VK_R, new RestartCommand(this));
        Commands.addCommand(KeyEvent.VK_SPACE, new PauseCommand(this));
        Commands.addCommand(KeyEvent.VK_ESCAPE, new CancelCommand(this));
        Commands.addCommand(KeyEvent.VK_1, new SetSpeedCommand(this, Obtiaznost.LAHKA));
        Commands.addCommand(KeyEvent.VK_2, new SetSpeedCommand(this, Obtiaznost.STREDNA));
        Commands.addCommand(KeyEvent.VK_3, new SetSpeedCommand(this, Obtiaznost.TAZKA));
    }

    private void init2() {
        removeKeyListener(observableKeyAdapter);

        observableKeyAdapter = new ObservableKeyAdapter();
        addKeyListener(observableKeyAdapter);

        observableKeyAdapter.attach(this);

        controllableObjects.add(new Hadik(new int[]{KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT}));
        controllableObjects.add(new Hadik(new int[]{KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D}));
        controllableObjects.add(new Hadik(new int[]{KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD6}));

        for (IMovingObject s : controllableObjects) {
            observableKeyAdapter.attach((IObserver) s);
        }

        hittableObjects.add(HittableFactory.createApple());
        hittableObjects.add(HittableFactory.createSpider());

        Position[] positions = {new Position(5, 5), new Position(6, 5), new Position(7, 5), new Position(5, 6), new Position(6, 6), new Position(7, 6)};
        Obstacle obstacle = HittableFactory.createObstacle(positions);
        hittableObjects.add(obstacle);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        for (Hittable s : hittableObjects) {
            for (PositionImage positionImage : s.getBody()) {
                g.drawImage(positionImage.getImage().image, positionImage.position.x, positionImage.position.y, this);
            }
        }
        int x = 16;
        for (ControllableObject c : controllableObjects) {
            for (PositionImage p : c.getState().getBody()) {
                g.drawImage(p.getImage().image, p.position.x, p.position.y, this);
            }
            g.setColor(Color.WHITE);
            g.drawString(c.getSize() + "", x, 16);
            x += 16;
        }
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Toto je mensi cheat. Pri stlaceni klavesy Q sa prida hadikovy dalsi clanok, ale skore sa nazvacsi. Sluzi len na testovanie funkcnosti predlzovania.
     */
    public void newPoint() {
        for (ControllableObject h : controllableObjects) {
            Hadik hadik = (Hadik) h;
            hadik.pridajClanok();
        }
    }

    private void tik() {
        checkFood();
        for (ControllableObject c : controllableObjects) {
            c.move();
        }
        repaint();
        this.skontrolujKoliziu();
        checkIfEnd();
        checkWin();
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
    public void cancel() {
        System.exit(0);
    }

    public void removeControllable(ControllableObject c) {
        controllableObjects.remove(c);
    }

    /**
     * Nastavi rychlost pohybu hadika podla prislusnej obtiaznosti.
     */
    public void nastavObtiaznost(Obtiaznost obtiaznost) {
        switch (obtiaznost) {
            case LAHKA:
                timer.setDelay(Config.easyDelay);
                break;
            case STREDNA:
                timer.setDelay(Config.middleDelay);
                break;
            case TAZKA:
                timer.setDelay(Config.hardDelay);
                break;
        }
    }

    /**
     * Skontroluje koliziu.
     */
    private void skontrolujKoliziu() {
        boolean end;
        for (int i = 0; i < controllableObjects.size(); i++) {
            ControllableObject h = controllableObjects.get(i);
            end = false;
            if (h.getHeadPosition().x < 0 || h.getHeadPosition().x > Config.rozmerPlatna || h.getHeadPosition().y < 0 || h.getHeadPosition().y > Config.rozmerPlatna) {
                h.end();
                continue;
            }
            for (int k = 0; k < controllableObjects.size(); k++) {
                ControllableObject h2 = controllableObjects.get(k);
                if (h != h2) {
                    for (PositionImage p : h2.getState().getBody()) {
                        Position position = p.position;
                        if (h.getHeadPosition().equals(position)) {
                            h.end();
                            end = true;
                            break;
                        }
                    }
                } else {
                    for (int j = 1; j < h2.getState().getBody().size(); j++) {
                        if (h.getHeadPosition().equals(h.getState().getBody().get(j).position)) {
                            h.end();
                            break;
                        }
                    }
                }
                if (end) {
                    break;
                }
            }
        }
    }

    private void checkIfEnd() {
        if (controllableObjects.size() == 0) {
            restart();
        }
    }

    private boolean win = false;

    private void checkWin() {
        if (!win) {
            for (ControllableObject c : controllableObjects) {
                if (c.getSize() == 100) {
                    JOptionPane.showMessageDialog(null, "You won!!!");
                    win = true;
                }
            }
        }
    }

    /**
     * Ak nastane kolizia, spusti sa metoda restart a hra sa restaruje.
     * <p>
     * Tak isto tu je mensi cheat.
     * Stlacenim klavesy R sa tak isto spusti tato metoda.
     * Tato funkcionalita sluzi len na testovanie.
     */
    public void restart() {
        controllableObjects.clear();
        hittableObjects.clear();
        init2();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tik();
    }

    private void checkFood() {
        for (int i = 0; i < controllableObjects.size(); i++) {
            ControllableObject m = controllableObjects.get(i);
            for (IServant s : hittableObjects) {
                s.action(m);
            }
        }
    }

    @Override
    public void update(Observable observable) {
        ObservableKeyAdapter observableKeyAdapter = (ObservableKeyAdapter) observable;
        int key = observableKeyAdapter.getPressedKey();
        Commands.execute(key);
    }

}