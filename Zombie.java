public class Zombie extends Entity {

    public void zombie_check(Player spieler) {

        if (Touch(spieler.zombie.figur, spieler.figur)) {

            spieler.figur.setBounds(-10000, -10000, 0, 0);

            GameWorld.ausgabe.setText(" Spieler " + spieler.figur.getText() + " hat verloren.");
        }

    }

    public static void zombie_folgen(Player spieler) {
        if (spieler.figur.getX() > 0) {
            if (spieler.zombie.figur.getX() < spieler.figur.getX()) {
                Player.Bewegung(spieler.zombie.figur, spieler.zombie.speed, 0);
            }
            if (spieler.zombie.figur.getX() > spieler.figur.getX()) {
                Player.Bewegung(spieler.zombie.figur, -spieler.zombie.speed, 0);
            }
            if (spieler.zombie.figur.getY() < spieler.figur.getY()) {
                Player.Bewegung(spieler.zombie.figur, 0, spieler.zombie.speed);
            }
            if (spieler.zombie.figur.getY() > spieler.figur.getY()) {
                Player.Bewegung(spieler.zombie.figur, 0, -spieler.zombie.speed);
            }
        }

    }

    @Override
    public void run() {
        try {
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

}
