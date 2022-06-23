import javax.swing.*;

public class Main {


    static int windowWidth = 900;
    static int windowHeight = 600;


    public static void main(String[] args)  {
        GameWorld gameWorld = new GameWorld();
        Zombie zombie = new Zombie();
        Player player= new Player();
        Thread p = new Thread(player);
        Thread z = new Thread(zombie);
        p.start();
        z.start();
        gameWorld.setSize(windowWidth, windowHeight);
        gameWorld.setVisible(true);
        gameWorld.setLocationRelativeTo(null);
        gameWorld.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWorld.setResizable(false);



    }


}
