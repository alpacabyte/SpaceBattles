import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class SpeedPowerUp extends PowerUp{
    
    public SpeedPowerUp(int x, int y) {
        super(x, y);
        
        try {
            powerUpImage = ImageIO.read(new FileImageInputStream(new File("speed.png")));
        } catch (IOException ex) {
            Logger.getLogger(SpeedPowerUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        colliderRectangle = new Rectangle(x, y, powerUpImage.getWidth(), powerUpImage.getHeight());
    }

    @Override
    public void UsePowerUp(SpaceShip ss) {
        ss.GainMaxVelocity(2);
    }
}
