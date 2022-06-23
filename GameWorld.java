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
    public void drow(JLabel charakter, String name, Color color, int x, int y, int height, int width) {
        int probex;
        int probey;
        if (x == 0 && y == 0) {
            Random random = new Random();
            /*
             * xnochmal: while (true){
             * probex=random.nextInt((Main.windowWidth-25));
             * if(!randomx.contains(probex)){
             * x=probex;
             * randomx.add(x);
             * }else {
             * break xnochmal;
             * }
             * }
             * 
             * ynochmal: while(true) {
             * probey=random.nextInt((Main.windowHeight-100));
             * if(!randomy.contains(probey)){
             * y=probey;
             * randomy.add(y);
             * }else{
             * break ynochmal;
             * }
             * 
             * 
             * }
             */
            y = random.nextInt((Main.windowHeight - 100));
            x = random.nextInt((Main.windowWidth - 25));
        }
        this.getContentPane().setLayout(null);
        charakter.setText(name);
        charakter.setBounds(x, y, width, height);
        charakter.setForeground(color);
        this.getContentPane().add(charakter);
    }

    /**
     * 
     */
    public GameWorld() {
        addKeyListener(new Player.keyboard());
        drow(GameWorld.spieler1.figur, "S", Color.red, 0, 0, 12, 10);
        drow(GameWorld.spieler1.zombie.figur, "Z", Color.red, 0, 0, 12, 10);
        drow(GameWorld.spieler1.score, "Score: " + GameWorld.spieler1.s, Color.red, 0, 520, 15, 60);
        drow(GameWorld.spieler1.levellabel, "Level: " + GameWorld.spieler1.l, Color.red, 100, 520, 15, 60);

        drow(GameWorld.spieler2.figur, "T", Color.blue, 0, 0, 12, 10);
        drow(GameWorld.spieler2.zombie.figur, "Z", Color.blue, 0, 0, 12, 10);
        drow(GameWorld.spieler2.score, "Score: " + GameWorld.spieler2.s, Color.blue, 700, 520, 15, 60);
        drow(GameWorld.spieler2.levellabel, "Level: " + GameWorld.spieler2.l, Color.blue, 800, 520, 15, 60);

        drow(Ausgang, "#", Color.black, 0, 0, 12, 10);
        drow(ausgabe, "", Color.black, 350, 520, 15, 350);

        for (int i = 0; i < heilmittel.length; i++) {
            heilmittel[i] = new JLabel();
            drow(heilmittel[i], "\u2695", Color.green, 0, 0, 12, 10);
        }
    }
}
