
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class SpaceShip1 extends SpaceShip{
    
    public SpaceShip1(int gameScreenX, int gameScreenY, int maxY) {
        super(gameScreenX, gameScreenY, maxY);
        
        try {
            spaceShipImage = ImageIO.read(new FileImageInputStream(new File("rocket1.png")));
        } catch (IOException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        }

        spaceShipX = (gameScreenX / 2) - (spaceShipWitdh / 2);
        spaceShipY = gameScreenY - spaceShipImage.getHeight() - 25;
        spaceShipWitdh = spaceShipImage.getWidth();
        spaceShipHeight = spaceShipImage.getHeight();
        spaceShipLenght = (int)Math.sqrt((spaceShipWitdh/2) * (spaceShipWitdh/2) + ((spaceShipHeight / 2)) * (spaceShipHeight / 2));
        
        fireCooldown = 20;
        maxVelocity = 7;
    }
    
    @Override
    public void Go(){
        currentVelocity += 0.25;
        if (currentVelocity > maxVelocity){
            currentVelocity = maxVelocity;
        }
        
        double rad = Math.toRadians(angle - 90);
        spaceShipX += Math.round(currentVelocity * Math.cos(rad));
        if (spaceShipX < 0){
            spaceShipX = 0;
        }
        else if (spaceShipX > gameScreenX - spaceShipWitdh){
            spaceShipX = gameScreenX - spaceShipWitdh;
        }
        
        spaceShipY += Math.round(currentVelocity * Math.sin(rad));
        if (spaceShipY < maxY){
            spaceShipY = maxY;
        }
        else if (spaceShipY > gameScreenY - spaceShipHeight){
            spaceShipY = gameScreenY - spaceShipHeight;
        }
        spaceShipLenght = (int)Math.sqrt((spaceShipWitdh/2) * (spaceShipWitdh/2) + ((spaceShipHeight / 2)) * (spaceShipHeight / 2));
    }
}
