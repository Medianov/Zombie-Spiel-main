import javax.swing.*;

public  class Zombie extends Entity {

    public void zombie_check(Player spieler) {
        if (Touch(spieler.zombie.figur, spieler.figur)) {
            spieler.figur.setBounds(-10000, -10000, 0, 0);
            GameWorld.ausgabe.setText(" Spieler " + spieler.figur.getText() + " hat verloren.");
        }
    }

    public  void zombie_folgen(Player spieler) {
        if (spieler.figur.getX() > 0) {
            if (spieler.zombie.figur.getX() < spieler.figur.getX()) {
                Bewegung(spieler.zombie.figur, spieler.zombie.speed, 0);
            }
            if (spieler.zombie.figur.getX() > spieler.figur.getX()) {
                Bewegung(spieler.zombie.figur, -spieler.zombie.speed, 0);
            }
            if (spieler.zombie.figur.getY() < spieler.figur.getY()) {
                Bewegung(spieler.zombie.figur, 0, spieler.zombie.speed);
            }
            if (spieler.zombie.figur.getY() > spieler.figur.getY()) {
                Bewegung(spieler.zombie.figur, 0, -spieler.zombie.speed);
            }
        }
    }

    public static void zombie_folgen() throws InterruptedException {
        GameWorld.ausgabe.setText("Das Spiel beginnt und die Zombies schlafen noch :)");
        Thread.sleep(5000);
        GameWorld.ausgabe.setText("Die Zombies KOMMEN");
    }

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

    @Override
    public void Bewegung(JLabel charakter, int x, int y)  {
            charakter.setLocation(charakter.getX() + x, charakter.getY() + y);
    }
}
