import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Player Klasse
 */
public class Player extends Entity {
    JLabel levellabel = new JLabel();
    JLabel score = new JLabel();

    // Zombie wird hier augerufen, damit der Speed vom Zombie verändert werden kann
    Zombie zombie = new Zombie();
    String scorePlayer = "0";
    String levelPlayer = "1";
    int heilmittel_anzahl = 0;
    int level = 1;
    int bedingungFuerLevel = 10;
    int xNeu;
    int yNeu;

    /**
     * Keyboard Klasse, welche benötigt wird, um die Tastatureingabe abzufangen.
     */
    public static class keyboard extends KeyAdapter {

        /**
         * Wenn der Spieler eine Taste drueckt, wird ein Event ausgelöst.
         */
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

        /**
         * Klasse keyReased, damit der Spieler nach der Eingabe einer Taste auch wieder aufhoert sich
         * zu bewegen.
         */
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
     * Siegesbedingung werden hier festgelegt. Wenn der Spieler verloren hat, wird die Position auf negative
     * Werte gesetzt, damit der Zombie den Spieler nicht mehr jagt, da dieser entfernt wurde.
     * 
     * @param spieler Spieler wird übergeben
     */
    public void gewinnen(Player spieler) {
        if (touched(spieler.figur, GameWorld.Ausgang)) {

            if (spieler.heilmittel_anzahl >= GameWorld.heilmittelAnzahl / 2) {
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
                if (spieler.heilmittel_anzahl < GameWorld.heilmittelAnzahl / 2) {
                    spieler.heilmittel_anzahl++;
                    spieler.scorePlayer = String.valueOf(spieler.heilmittel_anzahl);
                    spieler.score.setText("Score:" + spieler.scorePlayer);
                    GameWorld.heilmittel[i].setBounds(-50, -50, 0, 0);
                    GameWorld.ausgabe.setText("Spieler " + spieler.figur.getText() + " hat " + spieler.heilmittel_anzahl
                            + " Heilmittel eingesammelt");
                    if (spieler.heilmittel_anzahl == spieler.bedingungFuerLevel) {
                        spieler.level++;
                        spieler.levelPlayer = String.valueOf(spieler.level);
                        spieler.levellabel.setText("Level:" + spieler.levelPlayer);
                        spieler.zombie.speed = spieler.zombie.speed + 1;
                        spieler.speed = spieler.speed + 1;
                        spieler.bedingungFuerLevel = 2 * spieler.bedingungFuerLevel;
                    }
                }
            }
        }
    }

    /**
     * Fuer den Spieler-Thread. Hier werden die Spieler am laufen gehalten und es werden
     * die einzelnen Eigenschaften des Spiels überprueft
     */
    @Override
    public void run() {
        try {
            while (true) {
                bewegung(GameWorld.spieler1.figur, GameWorld.spieler1.xNeu, GameWorld.spieler1.yNeu);
                levels(GameWorld.spieler1);
                gewinnen(GameWorld.spieler1);
                rand_check(GameWorld.spieler1.figur);
                bewegung(GameWorld.spieler2.figur, GameWorld.spieler2.xNeu, GameWorld.spieler2.yNeu);
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
     * Bewegung, diese wird von Player definiert. Dieser bewegt sich anders als der Zombie.
     * 
     * @param charakter Player
     * @param x X Position in Swing
     * @param y Y Position in Swing
     */
    @Override
    public void bewegung(JLabel charakter, int x, int y) {
        charakter.setLocation(charakter.getX() + x, charakter.getY() + y);
        /**
         * Um die einzelnen Spieler selber anzusteuern.
         */
        if (charakter == GameWorld.spieler1.figur) {
            GameWorld.spieler1.xNeu = x;
            GameWorld.spieler1.yNeu = y;
        }
        if (charakter == GameWorld.spieler2.figur) {
            GameWorld.spieler2.xNeu = x;
            GameWorld.spieler2.yNeu = y;
        }
    }
}
