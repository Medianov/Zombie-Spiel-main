import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Player Klasse
 */
public class Player extends Entity {

    JLabel levellabel = new JLabel();
    JLabel score = new JLabel();
    Zombie zombie = new Zombie();
    String s = "0";
    String l = "1";
    int heilmittel_anzahl = 0;
    int level = 1;
    int bedingung = 10;
    int Xneu;
    int Yneu;

    /**
     * Keyboard Klasse, welche benötigt wird, um die Tastatureingabe abzufangen.
     */
    public static class keyboard extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                entity.bewegung(GameWorld.spieler1.figur, -GameWorld.spieler1.speed, 0);
            }
            if (key == KeyEvent.VK_RIGHT) {
                entity.bewegung(GameWorld.spieler1.figur, GameWorld.spieler1.speed, 0);
            }
            if (key == KeyEvent.VK_UP) {
                entity.bewegung(GameWorld.spieler1.figur, 0, -GameWorld.spieler1.speed);
            }
            if (key == KeyEvent.VK_DOWN) {
                entity.bewegung(GameWorld.spieler1.figur, 0, GameWorld.spieler1.speed);
            }
            if (key == KeyEvent.VK_A) {
                entity.bewegung(GameWorld.spieler2.figur, -GameWorld.spieler2.speed, 0);
            }
            if (key == KeyEvent.VK_D) {
                entity.bewegung(GameWorld.spieler2.figur, GameWorld.spieler2.speed, 0);
            }
            if (key == KeyEvent.VK_W) {
                entity.bewegung(GameWorld.spieler2.figur, 0, -GameWorld.spieler2.speed);
            }
            if (key == KeyEvent.VK_S) {
                entity.bewegung(GameWorld.spieler2.figur, 0, GameWorld.spieler2.speed);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                entity.bewegung(GameWorld.spieler1.figur, 0, 0);
            }
            if (key == KeyEvent.VK_RIGHT) {
                entity.bewegung(GameWorld.spieler1.figur, 0, 0);
            }
            if (key == KeyEvent.VK_UP) {
                entity.bewegung(GameWorld.spieler1.figur, 0, 0);
            }
            if (key == KeyEvent.VK_DOWN) {
                entity.bewegung(GameWorld.spieler1.figur, 0, 0);
            }
            if (key == KeyEvent.VK_A) {
                entity.bewegung(GameWorld.spieler2.figur, 0, 0);
            }
            if (key == KeyEvent.VK_D) {
                entity.bewegung(GameWorld.spieler2.figur, 0, 0);
            }
            if (key == KeyEvent.VK_W) {
                entity.bewegung(GameWorld.spieler2.figur, 0, 0);
            }
            if (key == KeyEvent.VK_S) {
                entity.bewegung(GameWorld.spieler2.figur, 0, 0);
            }

        }
    }

    /**
     * Siegesbedingung werden hier festgelegt.
     * 
     * @param spieler
     */
    public void gewinnen(Player spieler) {
        if (touched(spieler.figur, GameWorld.Ausgang)) {

            if (spieler.heilmittel_anzahl >= GameWorld.heilen / 2) {
                spieler.figur.setBounds(-10000, -10000, 0, 0);

                GameWorld.ausgabe.setText("Spieler " + spieler.figur.getText() + " hat gewonnen");
            }
            GameWorld.ausgabe.setText("Spieler " + spieler.figur.getText() + " muss noch "
                    + (50 - spieler.heilmittel_anzahl) + " Heilmittel einsammeln");
        }
    }

    /**
     * Score-System: mit steigenden Level steigt die Bedingung fuer das naechste
     * Level und die Geschwindigkeit (speed).
     * 
     * @param spieler
     */
    public void levels(Player spieler) {
        for (int i = 0; i < GameWorld.heilmittel.length; i++) {
            if (touched(spieler.figur, GameWorld.heilmittel[i])) {
                if (spieler.heilmittel_anzahl < GameWorld.heilen / 2) {
                    spieler.heilmittel_anzahl++;
                    spieler.s = String.valueOf(spieler.heilmittel_anzahl);
                    spieler.score.setText("Score:" + spieler.s);
                    GameWorld.heilmittel[i].setBounds(-50, -50, 0, 0);
                    GameWorld.ausgabe.setText("Spieler " + spieler.figur.getText() + " hat " + spieler.heilmittel_anzahl
                            + " Heilmittel eingesammelt");
                    if (spieler.heilmittel_anzahl == spieler.bedingung) {
                        spieler.level++;
                        spieler.l = String.valueOf(spieler.level);
                        spieler.levellabel.setText("Level:" + spieler.l);
                        spieler.zombie.speed = spieler.zombie.speed + 1;
                        spieler.speed = spieler.speed + 1;
                        spieler.bedingung = 2 * spieler.bedingung;
                    }
                }
            }
        }
    }

    /**
     * Fuer den Spieler-Thread.
     */
    @Override
    public void run() {
        try {
            while (true) {
                bewegung(GameWorld.spieler1.figur, GameWorld.spieler1.Xneu, GameWorld.spieler1.Yneu);
                levels(GameWorld.spieler1);
                gewinnen(GameWorld.spieler1);
                rand_check(GameWorld.spieler1.figur);
                bewegung(GameWorld.spieler2.figur, GameWorld.spieler2.Xneu, GameWorld.spieler2.Yneu);
                levels(GameWorld.spieler2);
                gewinnen(GameWorld.spieler2);
                rand_check(GameWorld.spieler2.figur);
                Thread.sleep(10);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * @param charakter
     * @param x
     * @param y
     */
    @Override
    public void bewegung(JLabel charakter, int x, int y) {
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
}
