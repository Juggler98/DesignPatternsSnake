//import java.util.ArrayList;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
//import java.lang.reflect.Method;
//import java.lang.reflect.InvocationTargetException;
//public class Manazer extends KeyAdapter {
//    private final ArrayList<Object> spravovaneObjekty;
//
//    public void keyPressed(KeyEvent e) {
//        int key = e.getKeyCode();
//        switch (key) {
//            case KeyEvent.VK_DOWN:
//            case KeyEvent.VK_S:
//                Manazer.this.posliSpravu("down", key);
//                break;
//            case KeyEvent.VK_UP:
//            case KeyEvent.VK_W:
//                Manazer.this.posliSpravu("up", key);
//                break;
//            case KeyEvent.VK_LEFT:
//            case KeyEvent.VK_A:
//                Manazer.this.posliSpravu("left", key);
//                break;
//            case KeyEvent.VK_RIGHT:
//            case KeyEvent.VK_D:
//                Manazer.this.posliSpravu("right", key);
//                break;
//            case KeyEvent.VK_ENTER:
//                break;
//            case KeyEvent.VK_ESCAPE:
//                Manazer.this.posliSpravu("cancel");
//                break;
//            case KeyEvent.VK_N:
//                Manazer.this.posliSpravu("newPoint");
//                break;
//            case KeyEvent.VK_R:
//                Manazer.this.posliSpravu("restart");
//                break;
//            case KeyEvent.VK_1:
//                Manazer.this.posliSpravu("easy");
//                break;
//            case KeyEvent.VK_2:
//                Manazer.this.posliSpravu("middle");
//                break;
//            case KeyEvent.VK_3:
//                Manazer.this.posliSpravu("hard");
//                break;
//            case KeyEvent.VK_SPACE:
//                Manazer.this.posliSpravu("pause");
//                break;
//        }
//    }
//
//    private void posliSpravu(String selektor) {
//        for (Object adresat : this.spravovaneObjekty) {
//            try {
//                Method sprava = adresat.getClass().getMethod(selektor);
//                sprava.invoke(adresat);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    private void posliSpravu(String selektor, int prvyParameter) {
//        for (Object adresat : this.spravovaneObjekty) {
//            try {
//                Method sprava = adresat.getClass().getMethod(selektor, Integer.TYPE);
//                sprava.invoke(adresat, prvyParameter);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * Vytvori novy manazer, ktory nespravuje zatial ziadne objekty.
//     */
//    public Manazer() {
//        this.spravovaneObjekty = new ArrayList<>();
//    }
//
//    /**
//     * Manazer bude spravovat dany objekt.
//     */
//    public void spravujObjekt(Object objekt) {
//        this.spravovaneObjekty.add(objekt);
//    }
//
//    /**
//     * Zrusi spravovanie objektu.
//     */
//    public void nespravujObjekt(Object objekt) {
//        this.spravovaneObjekty.remove(objekt);
//    }
//}
