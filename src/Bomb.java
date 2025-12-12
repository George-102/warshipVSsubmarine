/**
 * 炸弹类，表示从军舰发射的炸弹
 * 继承自Sprite抽象类，具有基本的游戏对象属性和行为
 */
public class Bomb extends Sprite {
    /**
     * 构造方法，创建一个新的炸弹对象
     * @param x 军舰的x坐标
     * @param y 军舰的y坐标
     */
    public Bomb(int x, int y) {
        // 从军舰中间位置掉落
        super(x + 40, y + 50, 20, 40, "src\\resources\\images\\boom.png");
    }

    /**
     * 实现炸弹的移动逻辑
     * 炸弹垂直向下移动，超出屏幕底部则标记为非存活状态
     */
    @Override
    public void move() {
        y += 8;  // 垂直下落
        if (y > 600) alive = false; // 超出屏幕范围则标记为死亡
    }
}