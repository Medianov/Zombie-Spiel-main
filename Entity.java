import javax.swing.*;

/**
 * Entity Klasse
 */
public abstract class Entity implements Runnable {
    JLabel figur = new JLabel();
    int speed = 1;
    static Player entity = new Player();

    /**
     * Prueft, ob sich ein Spieler/Player am Rand des Bildes befindet. Dieser wird dann zurückgedrängt.
     * @param charakter
     */
    public static void rand_check(JLabel charakter) {
        if (charakter.getX() <= 0)
            entity.bewegung(charakter, 1, 0);
        if (charakter.getX() >= (Main.windowWidth - 25))
            entity.bewegung(charakter, -1, 0);
        if (charakter.getY() <= 0)
            entity.bewegung(charakter, 0, 1);
        if (charakter.getY() > (Main.windowHeight - 100))
            entity.bewegung(charakter, 0, -1);
    }

    /**
     * Prueft, ob sich zwei Objekte sich berühren.
     * Was tut diese Methode?
     * @param label1
     * @param label2
     * @return boolean
     */
    public static boolean touched(JLabel label1, JLabel label2) {
        double checkx = Math.abs(label1.getBounds().getCenterX() - label2.getBounds().getCenterX());
        double checky = Math.abs(label1.getBounds().getCenterY() - label2.getBounds().getCenterY());
        if ((checky < (label1.getHeight() + label2.getHeight()) / 2)
                && (checkx < ((label1.getWidth() + label2.getWidth()) / 2))) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Abrakte Methoden, welche in Zombie in Spieler definiert werden.
     */
    @Override
    public abstract void run();
    public abstract void bewegung(JLabel charakter, int x, int y);
}
