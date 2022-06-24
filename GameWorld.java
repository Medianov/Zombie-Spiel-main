import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * In GameWorld werden Player, Zombie und Heilmittel zusammengetragen.
 */
public class GameWorld extends JFrame {
    enum labelOnScreen {
        S, Z, T
    }

    labelOnScreen playerLook = labelOnScreen.S;
    labelOnScreen zombieLook = labelOnScreen.Z;
    labelOnScreen playerTwoLook = labelOnScreen.T;

    static Player spieler1 = new Player();
    static Player spieler2 = new Player();
    static JLabel Ausgang = new JLabel();
    static JLabel ausgabe = new JLabel();
    static int heilmittelAnzahl = 100;
    static JLabel[] heilmittel = new JLabel[heilmittelAnzahl];
    ArrayList<Integer> randomx = new ArrayList<>();
    ArrayList<Integer> randomy = new ArrayList<>();

    public GameWorld() {
        addKeyListener(new Player.keyboard());

        drawing(GameWorld.spieler1.figur, playerLook.toString(), Color.red, 0, 0, 12, 10);
        drawing(GameWorld.spieler1.zombie.figur, zombieLook.toString(), Color.red, 0, 0, 12, 10);
        drawing(GameWorld.spieler1.score, "Score: " + GameWorld.spieler1.scorePlayer, Color.red, 0, 520, 15, 60);
        drawing(GameWorld.spieler1.levellabel, "Level: " + GameWorld.spieler1.levelPlayer, Color.red, 100, 520, 15, 60);

        drawing(GameWorld.spieler2.figur, playerTwoLook.toString(), Color.blue, 0, 0, 12, 10);
        drawing(GameWorld.spieler2.zombie.figur, zombieLook.toString(), Color.blue, 0, 0, 12, 10);
        drawing(GameWorld.spieler2.score, "Score: " + GameWorld.spieler2.scorePlayer, Color.blue, 700, 520, 15, 60);
        drawing(GameWorld.spieler2.levellabel, "Level: " + GameWorld.spieler2.levelPlayer, Color.blue, 800, 520, 15, 60);

        drawing(Ausgang, "#", Color.black, 0, 0, 12, 10);
        drawing(ausgabe, "", Color.black, 350, 520, 15, 350);

        /**
         * erstellt die Heilmittel, die Position wird in drawing erstellt
         */
        for (int i = 0; i < heilmittel.length; i++) {
            heilmittel[i] = new JLabel();
            drawing(heilmittel[i], "\u2695", Color.green, 0, 0, 12, 15);
        }
    }

    /**
     * Diese Methode nimmt die erstllen Objekte und macht diese in JLabels, damit diese in der grafischen Oberfläche
     * erscheinen.
     * 
     * @param charakter Spieler, Zombie und Heilmittel 
     * @param aussehen Das Aussehen der oben gennanten Objekte
     * @param color Farbe des Objekts
     * @param x Startposition (x-Koordinate)
     * @param y Startposition (y-Koordinate)
     * @param height fuer das JLabel, damit wird die hoehe festgelegt
     * @param width gleiches fuer die Breite
     */
    public void drawing(JLabel charakter, String aussehen, Color color, int x, int y, int height, int width) {
        int probex;
        int probey;

        /**
         * Wenn die Startposition 0/0 ist, werden zufällige Werte übergeben
         */
        if (x == 0 && y == 0) {
            Random random = new Random();
            probex = random.nextInt((Main.windowWidth - 25));
            probey = random.nextInt((Main.windowHeight - 100));
            while (true) {
                if (!randomx.contains(probex) || !randomy.contains(probey)) {
                    x = probex;
                    randomx.add(x);
                    y = probey;
                    randomy.add(y);
                } else {
                    break;
                }
            }
        }

        /**
         * Hinzufuegen der Eigenschaft in die JLabls
         */
        this.getContentPane().setLayout(null);
        charakter.setText(aussehen);
        charakter.setBounds(x, y, width, height);
        charakter.setForeground(color);
        this.getContentPane().add(charakter);
    }
}