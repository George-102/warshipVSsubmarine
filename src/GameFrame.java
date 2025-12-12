import javax.swing.*;
import java.awt.*;


public class GameFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel panel = new JPanel(cardLayout);

    public GameFrame() {
        setTitle("军舰大战潜艇");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel.add(new MenuPanel(this),"MENU");
        panel.add(new GamePanel(this),"GAME");
//        panel.add(new GameoverPanel(this),"OVER");

        add(panel);
        cardLayout.show(panel,"MENU");
        setVisible(true);

    }

    public void showGame() {
        cardLayout.show(panel, "GAME");
    }
    public void showMenu() {
        cardLayout.show(panel, "MENU");
    }
    public void showOver() {
        cardLayout.show(panel, "OVER");
    }


    static void main() {
        new GameFrame();

    }

}
