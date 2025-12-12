import java.awt.*;

/**
 * 军舰类，表示玩家控制的军舰
 * 继承自Sprite抽象类，具有基本的游戏对象属性和行为
 */
public class Warship extends Sprite{
    protected static final int SEA_Y = 160; // 军舰所在的海域Y坐标
    private long lastBombTime = 0;
    private static final long BOMB_INTERVAL = 500;

    /**
     * 构造方法，创建玩家控制的军舰
     * 初始化军舰的位置、尺寸和图像
     */
    public Warship() {
        super(350, SEA_Y, 100, 80, "src\\resources\\images\\ship0.png");
    }

    /**
     * 实现军舰的移动逻辑
     * 军舰的移动由键盘输入控制，因此此处无需实现具体逻辑
     */
    @Override
    public void move(){
        // 军舰移动由键盘控制，在GamePanel中处理
    }

    public Bomb shoot() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastBombTime >= BOMB_INTERVAL) {
            lastBombTime = currentTime;
            return new Bomb(this.x, this.y);
        }
        return null;
    }
}