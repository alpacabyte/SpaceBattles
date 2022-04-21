
import java.awt.Graphics;
import java.awt.Rectangle;

public class Fire{
    private int x, y;
    private int fireSize = 10;
    private Rectangle fireCollider;
    private int angle = 0;
    private int fireSpeed = 25;

    public Fire(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        fireCollider = new Rectangle(x, y, fireSize, fireSize);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public boolean CollisionCheck(Rectangle rectangle){
        return fireCollider.intersects(rectangle);
    }
    
    public void DrawFire(Graphics g){
        g.fillOval(x, y, fireSize, fireSize);
    }
    
    public void MoveFire(){
        double rad = Math.toRadians(angle - 90);
        x += fireSpeed * Math.cos(rad);
        y += fireSpeed * Math.sin(rad);
        fireCollider.x = x;
        fireCollider.y = y;
    }
}