import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

    public static class keyboard extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                Entity.Bewegung(GameWorld.spieler1.figur, -GameWorld.spieler1.speed, 0);
            }
            if (key == KeyEvent.VK_RIGHT) {
                Entity.Bewegung(GameWorld.spieler1.figur, GameWorld.spieler1.speed, 0);
            }
            if (key == KeyEvent.VK_UP) {
                Entity.Bewegung(GameWorld.spieler1.figur, 0, -GameWorld.spieler1.speed);
            }
            if (key == KeyEvent.VK_DOWN) {
                Entity.Bewegung(GameWorld.spieler1.figur, 0, GameWorld.spieler1.speed);
            }
            if (key == KeyEvent.VK_A) {
                Entity.Bewegung(GameWorld.spieler2.figur, -GameWorld.spieler2.speed, 0);
            }
            if (key == KeyEvent.VK_D) {
                Entity.Bewegung(GameWorld.spieler2.figur, GameWorld.spieler2.speed, 0);
            }
            if (key == KeyEvent.VK_W) {
                Entity.Bewegung(GameWorld.spieler2.figur, 0, -GameWorld.spieler2.speed);
            }
            if (key == KeyEvent.VK_S) {
                Entity.Bewegung(GameWorld.spieler2.figur, 0, GameWorld.spieler2.speed);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                Entity.Bewegung(GameWorld.spieler1.figur, 0, 0);
            }
            if (key == KeyEvent.VK_RIGHT) {
                Entity.Bewegung(GameWorld.spieler1.figur, 0, 0);
            }
            if (key == KeyEvent.VK_UP) {
                Entity.Bewegung(GameWorld.spieler1.figur, 0, 0);
            }
            if (key == KeyEvent.VK_DOWN) {
                Entity.Bewegung(GameWorld.spieler1.figur, 0, 0);
            }
            if (key == KeyEvent.VK_A) {
                Entity.Bewegung(GameWorld.spieler2.figur, 0, 0);
            }
            if (key == KeyEvent.VK_D) {
                Entity.Bewegung(GameWorld.spieler2.figur, 0, 0);
            }
            if (key == KeyEvent.VK_W) {
                Entity.Bewegung(GameWorld.spieler2.figur, 0, 0);
            }
            if (key == KeyEvent.VK_S) {
                Entity.Bewegung(GameWorld.spieler2.figur, 0, 0);
            }

        }
    }

    public static void gewinnen(Player spieler) {

        if (Touch(spieler.figur, GameWorld.Ausgang)) {

            if (spieler.heilmittel_anzahl >= GameWorld.heilen / 2) {
                spieler.figur.setBounds(-100, -100, 0, 0);

                GameWorld.ausgabe.setText("Spieler " + spieler.figur.getText() + " hat gewonnen");
            }
            GameWorld.ausgabe.setText("Spieler " + spieler.figur.getText() + " muss noch "
                    + (50 - spieler.heilmittel_anzahl) + " Heilmittel einsammeln");
        }
    }

    public static void levels(Player spieler) {
        for (int i = 0; i < GameWorld.heilmittel.length; i++) {
            if (Touch(spieler.figur, GameWorld.heilmittel[i])) {
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

    @Override

    public void run() {

        try {
            while (true) {
                Bewegung(GameWorld.spieler1.figur, GameWorld.spieler1.Xneu, GameWorld.spieler1.Yneu);
                levels(GameWorld.spieler1);
                gewinnen(GameWorld.spieler1);
                rand_check(GameWorld.spieler1.figur);
                Bewegung(GameWorld.spieler2.figur, GameWorld.spieler2.Xneu, GameWorld.spieler2.Yneu);
                levels(GameWorld.spieler2);
                gewinnen(GameWorld.spieler2);
                rand_check(GameWorld.spieler2.figur);
                Thread.sleep(10);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
