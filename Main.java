import javax.swing.*;

/**
 * Dies ist ein kleines Zombie, welches mit Java läuft. Es werden theoretisch zwei Spieler benötigt, da dieses Spiel die 
 * Punktestände miteinander vergleicht.
 * 
 * @author Median Alomir
 * @author Fernando Yacila Meza
 * @version 1.0
 * 
 */
public class Main {
    static int windowWidth = 900;
    static int windowHeight = 600;

    /**
     * 
     * @param args Ein String, welcher vom Benutzer bein Starten übergeben wird.
     * @return Es wird nichts zurückgegeben
     */
    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld();
        Zombie zombie = new Zombie();
        Player player = new Player();

        /**
         * Threads, damit Spieler und Zombies unabhängig voneinander laufen können.
         */
        Thread playerThread = new Thread(player);
        Thread zombieThread = new Thread(zombie);
        playerThread.start();
        zombieThread.start();

        /**
         * Allgmemeine Rahmenbedingung für Swing
         */
        gameWorld.setSize(windowWidth, windowHeight);
        gameWorld.setVisible(true);
        gameWorld.setLocationRelativeTo(null);
        gameWorld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWorld.setResizable(false);
        return;
    }
}