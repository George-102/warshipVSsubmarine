import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏主面板类，负责游戏的主要逻辑和渲染
 * 包含军舰控制、炸弹投掷、潜艇生成和碰撞检测等功能
 */
public class GamePanel extends JPanel {

    private int killedCount = 0; // 已消灭潜艇计数
    private int frameCount = 0; // 帧计数器
    private final Image background = new ImageIcon("src\\resources\\images\\background.png").getImage(); // 背景图片
    private final Warship warship = new Warship(); // 玩家控制的军舰
    private final List<Bomb> bombs = new ArrayList<>(); // 炸弹列表
    private final List<Submarine> submarines = new ArrayList<>(); // 潜艇列表
    private final GameFrame frame; // 游戏主窗口引用
    private Timer gameTimer;
    private boolean gameStart = false;

    private boolean left = false;
    private boolean right = false;
    private boolean atack = false;

    /**
     * 构造方法，初始化游戏面板和游戏逻辑
     * @param frame 游戏主窗口引用
     */
    public GamePanel(GameFrame frame) {
        this.frame = frame;
        setFocusable(true);
        requestFocusInWindow();  // 重要！确保获得焦点

        initGameTimer();

        // ========== 键盘监听 ==========
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    left = true; // 向左移动军舰
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    right = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    atack = true;
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    left = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    right = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    atack = false;
                }
            }
        });

    }

    public void initGameTimer() {
        if(gameTimer != null && gameTimer.isRunning()) {
            gameTimer.stop();
        }
        this.gameTimer = new Timer(16, e -> {  // 约60FPS
            if(!gameStart) return;

            if (left) {
                warship.x -= 5;
            }
            if (right) {
                warship.x += 5;
            }
            // 边界检查
            if (warship.x < 0) warship.x = 0;
            if (warship.x > 800 - warship.width) warship.x = 800 - warship.width;

            // 发射炸弹（持续按空格会每500ms发射一次）
            if (atack) {
                Bomb newBomb = warship.shoot();
                if (newBomb != null) {
                    bombs.add(newBomb);
                }
                // 可选：如果想按一次发射一颗，改为只在按下瞬间触发
            }
            // 更新所有炸弹
            for (Bomb b : bombs) {
                b.move();
            }
            bombs.removeIf(b -> !b.alive);

            // 更新所有潜艇
            for (Submarine s : submarines) {
                s.move();
            }
            submarines.removeIf(s -> !s.alive);
            System.out.println("当前潜艇数量: " + submarines.size() + ", frame: " + frameCount);

            // 碰撞检测
            for (int i = bombs.size() - 1; i >= 0; i--) {  // 倒序避免IndexOutOfBounds
                Bomb bomb = bombs.get(i);
                for (int j = submarines.size() - 1; j >= 0; j--) {
                    Submarine sub = submarines.get(j);
                    if (bomb.getBounds().intersects(sub.getBounds())) {
                        bomb.alive = false;
                        sub.alive = false;
                        killedCount++;  // 击中潜艇计数增加
                        System.out.println("击中！已消灭: " + killedCount);  // 调试
                        break;  // 一个炸弹只击中一个潜艇
                    }
                }
            }

            // 生成新潜艇：每200帧（约3秒）随机生成
            if (frameCount % 100 == 0) {
                System.out.println("生成新潜艇！总消灭: " + killedCount);
                submarines.add(new Submarine());
            }
            frameCount++;  // 帧计数增加

            // 检查是否达到胜利条件
            if (killedCount >= 10) {
                frame.showOver();
                ((Timer)e.getSource()).stop();  // 停止定时器，避免继续运行
            }

            repaint();  // 每帧重绘
        });
    }

    public void startGame() {
        killedCount = 0;
        frameCount = 0;
        bombs.clear();
        submarines.clear();

        if(gameTimer == null){
            initGameTimer();
        }

        if(gameTimer.isRunning()) {
            gameTimer.restart();
        } else{
            gameTimer.start();
        }
        gameStart = true;
        repaint();
    }

    public void stopGame() {
        if(gameStart && gameTimer.isRunning()) {
            gameTimer.stop();
        }
        gameStart = false;
    }
    /**
     * 绘制游戏组件的方法
     * 负责绘制背景、军舰、炸弹、潜艇以及UI信息
     * @param g 图形绘制对象
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // 绘制背景（拉伸适应窗口）
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        
        // 绘制分数信息
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.drawString("得分: " + killedCount + "/10", 10, 30);
        
        // 绘制军舰和所有炸弹
        warship.draw(g);
        for (Bomb b : bombs) {
            b.draw(g);
        }
        for (Submarine s : submarines) {
            s.draw(g);
        }

        // 检查胜利条件
        if(killedCount >= 10) {
            frame.showOver(); 
        }
    }

    /**
     * 组件添加通知方法
     * 当组件被添加到容器中时自动调用，确保面板获取焦点
     */
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }
}