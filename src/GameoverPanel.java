import javax.swing.*;
import java.awt.*;

/**
 * 游戏结束面板类
 * 当玩家消灭足够多的潜艇后显示此面板
 */
public class GameoverPanel extends JPanel {
    /**
     * 构造方法，创建游戏结束面板界面
     * @param frame 游戏主窗口引用，用于面板间切换
     */
    public GameoverPanel(GameFrame frame) {
        setBackground(Color.DARK_GRAY); // 设置背景色为深灰色
        setLayout(new GridBagLayout());  // 居中布局
        
        // 创建胜利信息标签
        JLabel winLabel = new JLabel("胜利！消灭10艘潜艇！", JLabel.CENTER);
        winLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        winLabel.setForeground(Color.GREEN); // 设置文字颜色为绿色
        add(winLabel);

        // 创建重新开始按钮
        JButton replayBtn = new JButton("再玩一次");
        replayBtn.addActionListener(e -> frame.showMenu()); // 点击返回菜单面板
        add(replayBtn);

        // 创建退出游戏按钮
        JButton exitBtn = new JButton("退出游戏");
        exitBtn.addActionListener(e -> System.exit(0)); // 点击退出游戏
        add(exitBtn);
    }
}