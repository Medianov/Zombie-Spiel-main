import javax.swing.*;

/**
 * 
 */
public abstract class Entity implements Runnable {
    JLabel figur = new JLabel();
    int speed = 1;

/**
 * 
 * @param charakter
 * @param x
 * @param y
 */
    public static void Bewegung(JLabel charakter, int x, int y) {
        charakter.setLocation(charakter.getX() + x, charakter.getY() + y);
        if (charakter == GameWorld.spieler1.figur) {
            GameWorld.spieler1.Xneu = x;
            GameWorld.spieler1.Yneu = y;
        }
        if (charakter == GameWorld.spieler2.figur) {
            GameWorld.spieler2.Xneu = x;
            GameWorld.spieler2.Yneu = y;
        }
    }

    /**
     * 
     * @param charakter
     */
    public static void rand_check(JLabel charakter) {
        if (charakter.getX() <= 0)
            Player.Bewegung(charakter, 1, 0);
        if (charakter.getX() >= (Main.windowWidth - 25))
            Player.Bewegung(charakter, -1, 0);
        if (charakter.getY() <= 0)
            Player.Bewegung(charakter, 0, 1);
        if (charakter.getY() > (Main.windowHeight - 100))
            Player.Bewegung(charakter, 0, -1);
    }

    /**
     * 
     * @param check1
     * @param check2
     * @return
     */
    public static boolean Touch(JLabel check1, JLabel check2) {
        double checkx = Math.abs(check1.getBounds().getCenterX() - check2.getBounds().getCenterX());
        double checky = Math.abs(check1.getBounds().getCenterY() - check2.getBounds().getCenterY());
        if ((checky < (check1.getHeight() + check2.getHeight()) / 2)
                && (checkx < ((check1.getWidth() + check2.getWidth()) / 2))) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Abrakte Methode
     */
    @Override
    public void run() {

    }
}
