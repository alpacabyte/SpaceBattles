
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class AlienShip{
    protected int alienShipX = 0, alienShipY = 5;
    protected int alienShipDirX = 5;
    protected BufferedImage alienShipImage;
    protected int gameScreenX, gameScreenY;
    protected int maxX = 0;
    protected Rectangle alienShipCollider;

    public Rectangle getAlienShipCollider() {
        return alienShipCollider;
    }

    public AlienShip(int gameScreenX, int gameScreenY, int x, int y) {
        alienShipX = x;
        alienShipY = y;
        
        this.gameScreenX = gameScreenX;
        this.gameScreenY = gameScreenY;
    }
    
    public void DrawSpaceShip(Graphics g, ImageObserver imageObserver){
        g.drawImage(alienShipImage, alienShipX, alienShipY, imageObserver);
    }

    public int getAlienShipX() {
        return alienShipX;
    }
    
    public int getAlienShipY() {
        return alienShipY;
    }

    public void setAlienShipX(int alienShipX) {
        this.alienShipX = alienShipX;
    }
    
    public void MoveAlienShip(){
        
    }
    
    public Explosion CreateExplosion(){
        return new Explosion(alienShipX, alienShipY);
    }
}
