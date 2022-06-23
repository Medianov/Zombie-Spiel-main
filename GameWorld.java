import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameWorld extends JFrame {
    static Player spieler1 = new Player();
    static Player spieler2 = new Player();
    static JLabel Ausgang = new JLabel();
    static JLabel ausgabe = new JLabel();
    static int heilen = 100;
    static JLabel[] heilmittel = new JLabel[heilen];

    ArrayList<Integer> randomx = new ArrayList<>();
    ArrayList<Integer> randomy = new ArrayList<>();

    public GameWorld() {
        addKeyListener(new Player.keyboard());

        drow(GameWorld.spieler1.figur, playerLook.toString(), Color.red, 0, 0, 12, 10);
        drow(GameWorld.spieler1.zombie.figur, zombieLook.toString(), Color.red, 0, 0, 12, 10);
        drow(GameWorld.spieler1.score, "Score: " + GameWorld.spieler1.s, Color.red, 0, 520, 15, 60);
        drow(GameWorld.spieler1.levellabel, "Level: " + GameWorld.spieler1.l, Color.red, 100, 520, 15, 60);

        drow(GameWorld.spieler2.figur, playerTwoLook.toString(), Color.blue, 0, 0, 12, 10);
        drow(GameWorld.spieler2.zombie.figur, zombieLook.toString(), Color.blue, 0, 0, 12, 10);
        drow(GameWorld.spieler2.score, "Score: " + GameWorld.spieler2.s, Color.blue, 700, 520, 15, 60);
        drow(GameWorld.spieler2.levellabel, "Level: " + GameWorld.spieler2.l, Color.blue, 800, 520, 15, 60);

        drow(Ausgang, "#", Color.black, 0, 0, 12, 10);
        drow(ausgabe, "", Color.black, 350, 520, 15, 350);

        for (int i = 0; i < heilmittel.length; i++) {
            heilmittel[i] = new JLabel();
            drow(heilmittel[i], String.valueOf(i), Color.green, 0, 0, 12, 15);
        }
    }

    /**
     *
     * @param charakter
     * @param name
     * @param color
     * @param x
     * @param y
     * @param height
     * @param width
     */
    public void drow(JLabel charakter, String name, Color color, int x, int y, int height, int width){
        int probex;
        int probey;
        if (x == 0 && y == 0) {
            Random random = new Random();
            probex = random.nextInt((Main.windowWidth - 25));
            probey = random.nextInt((Main.windowHeight - 100));

            while(!randomy.contains(probey) || !randomx.contains(probex)) {
                y = probey;
                x = probex;
                randomy.add(y);
                randomx.add(x);
            }
        }

        this.getContentPane().setLayout(null);
        charakter.setText(name);
        charakter.setBounds(x, y, width, height);
        charakter.setForeground(color);
        this.getContentPane().add(charakter);
    }

    /**
     * Eine eigentlich unnötige Enumeration.
     */
    enum labelOnScreen {
        S, Z, T
    }

    labelOnScreen playerLook = labelOnScreen.S;
    labelOnScreen zombieLook = labelOnScreen.Z;
    labelOnScreen playerTwoLook = labelOnScreen.T;
}