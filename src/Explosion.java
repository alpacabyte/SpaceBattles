import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Explosion implements Runnable{
            
    protected BufferedImage explosionImage = null;
    
    protected int x, y;
    
    protected boolean isFinished = false;
    
    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
        
        Thread explode = new Thread(this);
        explode.start();
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    @Override
    public void run() {
        try {
            explosionImage = ImageIO.read(new FileImageInputStream(new File("explosion1.png")));
            Thread.sleep(100);
            explosionImage = ImageIO.read(new FileImageInputStream(new File("explosion2.png")));
            Thread.sleep(100);
            explosionImage = ImageIO.read(new FileImageInputStream(new File("explosion3.png")));
            Thread.sleep(100);
            explosionImage = ImageIO.read(new FileImageInputStream(new File("explosion4.png")));
            Thread.sleep(100);
            isFinished = true;
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Explosion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DrawExplosion(Graphics g, ImageObserver imageObserver){
        g.drawImage(explosionImage, x, y, imageObserver);
    }
}
