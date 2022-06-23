import javax.swing.*;

/**
 * 
 */
public abstract class Entity implements Runnable {
    JLabel figur = new JLabel();
    int speed = 1;
    static Player entity = new Player();

/**
 * 
 * @param charakter
 * @param x
 * @param y
 */


    /**
     * 
     * @param charakter
     */
    public static void rand_check(JLabel charakter) {
        if (charakter.getX() <= 0)
            entity.Bewegung(charakter, 1, 0);
        if (charakter.getX() >= (Main.windowWidth - 25))
            entity.Bewegung(charakter, -1, 0);
        if (charakter.getY() <= 0)
            entity.Bewegung(charakter, 0, 1);
        if (charakter.getY() > (Main.windowHeight - 100))
            entity.Bewegung(charakter, 0, -1);
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
     * Abrakte Methoden
     */
    @Override
    public abstract void run();
    public abstract void Bewegung(JLabel charakter, int x, int y);
}
