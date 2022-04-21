
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class PowerUp{
    protected int x, y;
    protected BufferedImage powerUpImage = null;
    protected Rectangle colliderRectangle;
    protected int countdown = 80;
    
    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
    }
      
    public void DrawPowerUp(Graphics g, ImageObserver imageObserver){
        g.drawImage(powerUpImage, x, y, imageObserver);
    }
    
    public boolean CollisionCheck(Rectangle rectangle){
        return colliderRectangle.intersects(rectangle);
    }
    
    public void UsePowerUp(SpaceShip ss){
        
    }
    
    public boolean Countdown(){
        return --countdown <= 0;
    }
}
