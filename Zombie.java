import javax.swing.*;

/**
  * Zombie-Klasse. Dient in diesen Spiel als Monster bzw. Jäger.
  */
public class Zombie extends Entity {

    /**
     * Siegesbedingung fuer den Zombie, dieser prueft, ob der Player und der Zombie sich berührt
     * haben.
     * @param spieler Der Zombie-Check, also die Überprüfung, ob die sich berhührt haben
     */
    public void zombie_check(Player spieler) {
        if (touched(spieler.zombie.figur, spieler.figur)) {
            spieler.figur.setBounds(-10000, -10000, 0, 0);
            GameWorld.ausgabe.setText(" Spieler " + spieler.figur.getText() + " hat verloren.");
        }
    }

     /**
      * Hier wird der Algorithmus fuer den Zombie ausgefuehrt, der den Spieler jagt.
      * Ueberladene Methode:
      * @param spieler fuer den Zombie, damit dieser weiß, wo sich der Spieler befindet.
      */
    public  void zombie_folgen(Player spieler) {
        if (spieler.figur.getX() > 0) {
            if (spieler.zombie.figur.getX() < spieler.figur.getX()) {
                bewegung(spieler.zombie.figur, spieler.zombie.speed, 0);
            }
            if (spieler.zombie.figur.getX() > spieler.figur.getX()) {
                bewegung(spieler.zombie.figur, -spieler.zombie.speed, 0);
            }
            if (spieler.zombie.figur.getY() < spieler.figur.getY()) {
                bewegung(spieler.zombie.figur, 0, spieler.zombie.speed);
            }
            if (spieler.zombie.figur.getY() > spieler.figur.getY()) {
                bewegung(spieler.zombie.figur, 0, -spieler.zombie.speed);
            }
        }
    }

    /**
     * Zombie Anfangsverhalten
     * @throws InterruptedException fuer den Thread-Delay
     */
    public static void zombie_folgen() throws InterruptedException {
        GameWorld.ausgabe.setText("Das Spiel beginnt und die Zombies schlafen noch :)");
        Thread.sleep(5000);
        GameWorld.ausgabe.setText("Die Zombies KOMMEN");
    }

    /**
     * fuer den Zombie Thread
     */
    @Override
    public void run() {
        try {
            zombie_folgen();
            while (true) {
                zombie_check(GameWorld.spieler1);
                zombie_check(GameWorld.spieler2);
                rand_check(GameWorld.spieler1.zombie.figur);
                zombie_folgen(GameWorld.spieler1);
                rand_check(GameWorld.spieler2.zombie.figur);
                zombie_folgen(GameWorld.spieler2);
                Thread.sleep(25);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Abtrakte Methode fuer den Zombie modifiziert.
     */
    @Override
    public void bewegung(JLabel charakter, int x, int y)  {
            charakter.setLocation(charakter.getX() + x, charakter.getY() + y);
    }
}
