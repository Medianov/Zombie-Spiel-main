import javax.swing.*;

public class Main {
    static int windowWidth = 900;
    static int windowHeight = 600;
    
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        GameWorld gameWorld = new GameWorld();
        Zombie zombie = new Zombie();
        Player player = new Player();
        Thread playerThread = new Thread(player);
        Thread zombieThread = new Thread(zombie);
        playerThread.start();
        zombieThread.start();
        gameWorld.setSize(windowWidth, windowHeight);
        gameWorld.setVisible(true);
        gameWorld.setLocationRelativeTo(null);
        gameWorld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWorld.setResizable(false);
    }
}

/*
 * technische Anforderungen
 * Mindestens zwei abstrakte Methoden (noch eine)
 * Mindestens eine überladene Methode (noch eine)
 * Mindestens eine Enumeration (noch eine)
 * Mindestens eine selbst definierte Exception fehlt
 * 
 * Pflicht-Anforderungen
Dokumentation des Programms
UML Diagramm
Natürlichsprachliche Beschreibung des Programmaufbaus
Kurze Spielanleitung
JavaDoc für alle Klassen und mindestens alle public Methoden
Kurzer Erfahrungsbericht, wie gut das alte Programm portiert werden konnte
 * 
 * Wahlanforderungen (mindestens 0 aus der Liste)
Zusätzlicher Monstertyp
Einstellbarer Schwierigkeitsgrad
Hindernisse, z.B. Felsen oder Mauern
Sinnvoller Einsatz zweier Design Patterns (https://www.youtube.com/watch?v=8lZxxxOUO_A)
 * 
 * 
 * 
 * 
 */