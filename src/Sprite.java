import javax.swing.*;
import java.awt.*;


public abstract class Sprite {
    protected int x,y, width, height;
    protected Image image;
    protected boolean alive = true;

    public  Sprite(int x, int y, int width, int height,String imagePath) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new ImageIcon(imagePath).getImage();
    }

    public void draw(Graphics g) {
        if(alive) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public abstract void move();
}
