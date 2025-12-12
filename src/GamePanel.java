import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Image background = new ImageIcon("images/background.png").getImage();
    private Warship warship = new Warship();

    public GamePanel(GameFrame frame) {
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        warship.draw(g);
    }
}
