
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class Barricade {
    private int barricadeX, barricadeY;
    private BufferedImage barricadeImage;
    private Rectangle barricadeCollider;

    public Rectangle getBarricadeCollider() {
        return barricadeCollider;
    }
    
    public Barricade(int x, int y) {
        barricadeX = x;
        barricadeY = y;
        
        try {
            barricadeImage = ImageIO.read(new FileImageInputStream(new File("shield.png")));
        } catch (IOException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        barricadeCollider = new Rectangle(barricadeX, barricadeY, barricadeImage.getWidth(), barricadeImage.getHeight());
    }
    
    public void DrawBarricade(Graphics g, ImageObserver imageObserver){
        g.drawImage(barricadeImage, barricadeX, barricadeY, imageObserver);
    }
}
