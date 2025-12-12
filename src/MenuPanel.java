import javax.swing.*;
import java.awt.*;

/**
 * 游戏菜单面板类
 * 显示游戏开始界面，包含开始游戏按钮
 */
public class MenuPanel extends JPanel {
    /**
     * 构造方法，创建游戏菜单界面
     * @param gameFrame 游戏主窗口引用，用于面板间切换
     */
    public MenuPanel(GameFrame gameFrame) {
        setBackground(Color.CYAN); // 设置背景色为青色
        
        // 创建开始按钮
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("微软雅黑", Font.BOLD, 36)); // 设置字体样式和大小
        
        // 添加按钮点击事件监听器
        startButton.addActionListener(e -> gameFrame.showGame()); // 点击后显示游戏面板
        
        add(startButton); // 将按钮添加到面板中
    }
}