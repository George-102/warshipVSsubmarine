import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 游戏主窗口类，负责管理整个游戏界面和流程控制
 * 使用CardLayout管理不同的游戏面板（菜单、游戏、结束）
 */
public class GameFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout(); // 卡片布局管理器
    private JPanel panel = new JPanel(cardLayout); // 主面板容器
    private GamePanel gamePanel;

    /**
     * 构造方法，初始化游戏窗口和各个游戏面板
     */
    public GameFrame() {
        setTitle("军舰大战潜艇"); // 设置窗口标题
        setSize(800, 600); // 设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭操作
        setLocationRelativeTo(null); // 设置窗口居中显示
        
        // 添加各个游戏面板到卡片布局中
        MenuPanel menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        GameoverPanel gameoverPanel = new GameoverPanel(this);

        panel.add(menuPanel,"MENU");
        panel.add(gamePanel,"GAME");
        panel.add(gameoverPanel,"OVER");

        add(panel);
        cardLayout.show(panel,"MENU"); // 默认显示菜单面板
        setVisible(true); // 显示窗口
    }

    /**
     * 显示游戏面板的方法
     */
    public void showGame() {
        cardLayout.show(panel, "GAME");
        // 切换后强制重绘和焦点
        gamePanel.startGame();
        SwingUtilities.invokeLater(() -> {
            gamePanel.requestFocusInWindow();
        });
    }
    
    /**
     * 显示菜单面板的方法
     */
    public void showMenu() {
        gamePanel.stopGame();
        cardLayout.show(panel, "MENU");
    }
    
    /**
     * 显示游戏结束面板的方法
     */
    public void showOver() {
        gamePanel.stopGame();
        cardLayout.show(panel, "OVER");
    }

    /**
     * 程序入口点
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        new GameFrame(); // 创建并显示游戏窗口
    }
}