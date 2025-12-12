import javax.swing.*;
import java.util.Random;

/**
 * 潜艇类，表示游戏中敌方的潜艇单位
 * 继承自Sprite抽象类，具有基本的游戏对象属性和行为
 */
public class Submarine extends Sprite {
    private int dx;  // 移动速度（正右，负左）
    private static final Random random = new Random(); // 随机数生成器

    /**
     * 构造方法，创建一个新的潜艇对象
     * 潜艇会随机从左侧或右侧出现，并以随机速度移动
     */
    public Submarine() {
        super();
        // 随机Y在海下，宽度/高度根据图片（假设60x40）
        int seaY = Warship.SEA_Y;  // 复用军舰的海面Y
        int subY = seaY + 100 + random.nextInt(200);  // 海下100-400像素
        int subWidth = 60, subHeight = 40;

        // 随机方向：50%从左出现向右，50%从右向左
        boolean fromLeft = random.nextBoolean();
        if (fromLeft) {
            this.x = -subWidth;  // 从左外出现
            this.dx = 2 + random.nextInt(3);  // 速度2-4
        } else {
            this.x = 800;  // 从右外出现（窗口宽800）
            this.dx = - (2 + random.nextInt(3));
        }
        this.y = subY;
        this.width = subWidth;
        this.height = subHeight;

        // 随机图片：q1/q2/r1/h2
        String[] images = {"src\\resources\\images\\q1.png", "src\\resources\\images\\q2.png", "src\\resources\\images\\r1.png", "src\\resources\\images\\h2.png"};
        String imagePath = images[random.nextInt(images.length)];
        this.image = new ImageIcon(imagePath).getImage();

        this.alive = true;
    }

    /**
     * 实现潜艇的移动逻辑
     * 潜艇水平移动，超出屏幕则标记为非存活状态
     */
    @Override
    public void move() {
        x += dx;  // 水平移动
        // 超出屏幕消失
        if (x > 800 || x < -width) {
            alive = false;
        }
    }
}