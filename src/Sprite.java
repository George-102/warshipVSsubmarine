import javax.swing.*;
import java.awt.*;

/**
 * 游戏精灵抽象基类
 * 所有游戏中的可视对象（如军舰、潜艇、炸弹等）都继承此类
 */
public abstract class Sprite {
    protected int x, y, width, height; // 精灵的位置和尺寸属性
    protected Image image; // 精灵的图像
    protected boolean alive = true; // 精灵的存活状态

    /**
     * 构造方法，创建一个指定属性的游戏精灵
     * @param x 精灵的x坐标
     * @param y 精灵的y坐标
     * @param width 精灵的宽度
     * @param height 精灵的高度
     * @param imagePath 精灵图像的路径
     */
    public Sprite(int x, int y, int width, int height, String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new ImageIcon(imagePath).getImage();
    }

    /**
     * 无参构造方法
     */
    public Sprite() {
    }

    /**
     * 绘制精灵的方法
     * @param g 图形绘制对象
     */
    public void draw(Graphics g) {
        if(alive) { // 只有存活的精灵才会被绘制
            g.drawImage(image, x, y, width, height, null);
        }
    }

    /**
     * 获取精灵边界矩形的方法
     * 用于碰撞检测
     * @return 表示精灵边界的矩形
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * 抽象移动方法，由子类实现具体的移动逻辑
     */
    public abstract void move();
}