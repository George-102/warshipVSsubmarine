import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(GameFrame gameFrame) {
        setBackground(Color.CYAN);
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("微软雅黑", Font.BOLD, 36));
        startButton.addActionListener(e -> gameFrame.showGame());
        add(startButton);
    }

}
