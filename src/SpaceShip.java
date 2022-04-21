import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public abstract class SpaceShip {
    protected BufferedImage spaceShipImage = null;
    protected int spaceShipX = 0, spaceShipY = 0;
    protected int spaceShipWitdh, spaceShipHeight;
    protected int spaceShipLenght;
    protected int gameScreenX, gameScreenY, maxY;
    protected int angle = 0;
    protected double currentVelocity = 0;
    protected double maxVelocity = 5;
    protected int fireCooldownCounter = 0, fireCooldown = 20;

    public int getAngle() {
        return angle;
    }

    public int getSpaceShipY() {
        return spaceShipY;
    }
    
    public int getSpaceShipX() {
        return spaceShipX;
    }
    
    public void createFire(ArrayList<Fire> fires){
        if (fireCooldownCounter < 0){
        int centerX = spaceShipX + (spaceShipWitdh / 2) - 5;
        int centerY = spaceShipY + (spaceShipHeight / 2) - 5;
        
        double rad = Math.toRadians(angle - 90);
        
        int coorX = centerX + (int)Math.round(spaceShipLenght * Math.cos(rad) * 0.75);
        int coorY = centerY + (int)Math.round(spaceShipLenght * Math.sin(rad) * 0.75);
        
        fires.add(new Fire(coorX, coorY, angle));
        fireCooldownCounter = fireCooldown;
        }
        
    }

    public SpaceShip(int gameScreenX, int gameScreenY, int maxY) {
        this.gameScreenX = gameScreenX;
        this.gameScreenY = gameScreenY;
        
        this.maxY = maxY;
    }
    
    public void DrawSpaceShip(Graphics g, ImageObserver imageObserver){
        AffineTransform at = AffineTransform.getTranslateInstance(spaceShipX, spaceShipY);
        at.rotate(Math.toRadians(angle), spaceShipImage.getWidth() / 2, spaceShipImage.getHeight() / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(spaceShipImage, at, null);
    }
    
    public void DrawVelocityAndAngle(Graphics g, ImageObserver imageObserver){
        g.drawString("Velocity: " + (int)currentVelocity * 13 + " km/sn", 850, 800);
        g.drawString("Angle: " + angle, 890, 850);
    }
    
    public void rotate(int degree)
    {
       angle += degree;
       angle %= 360;
       if (angle < 0){
           angle = angle + 360;
       }
    }
    
    public void Stop(){
        currentVelocity = 0;
    }
    
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
        if (spaceShipY < maxY + spaceShipHeight/2){
            spaceShipY = maxY + spaceShipHeight/2;
        }
        else if (spaceShipY > gameScreenY - spaceShipHeight){
            spaceShipY = gameScreenY - spaceShipHeight;
        }
        spaceShipLenght = (int)Math.sqrt((spaceShipWitdh/2) * (spaceShipWitdh/2) + ((spaceShipHeight / 2)) * (spaceShipHeight / 2));
    }
    
    public void GainMaxVelocity(int addition){
        maxVelocity += addition;
        if (maxVelocity <= 0){
            maxVelocity = 1;
        }
    }
    
    public void Countdown(){
        fireCooldownCounter--;
    }
}
