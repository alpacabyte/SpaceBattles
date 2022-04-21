import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class alienShipType3 extends AlienShip{
    
    alienShipType3(int gameScreenX, int gameScreenY, int x, int y) {
        super(gameScreenX, gameScreenY, x, y);
        
        try {
            alienShipImage = ImageIO.read(new FileImageInputStream(new File("alienShip3.png")));
        } catch (IOException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        maxX = gameScreenX - alienShipImage.getWidth();
        
        alienShipCollider = new Rectangle(alienShipX, alienShipY, alienShipImage.getWidth(), alienShipImage.getHeight());
    }
}
