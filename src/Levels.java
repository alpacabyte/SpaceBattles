import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public abstract class Levels extends JPanel implements KeyListener, ActionListener, MouseListener{
    protected Set<Integer> pressedKeys = new HashSet<>();
    protected BufferedImage backgroundImage;
    protected int fireCooldown = 20, fireCooldownCounter = 0;
    protected int gameScreenX, gameScreenY;
    protected int score = 0;
    protected int highScore = 0;
    protected Timer timer = new Timer(1, this);
    
    protected ArrayList<Fire> fires = new ArrayList<>();
    
    protected ArrayList<AlienShip> alienShips = new ArrayList<>();
    
    protected ArrayList<Barricade> barricades = new ArrayList<>();
    
    protected ArrayList<PowerUp> powerUps = new ArrayList<>();
    
    protected ArrayList<Explosion> explosions = new ArrayList<>();
    
    protected SpaceShip spaceShip;
    
    protected boolean isStarted = false;
    
    protected JFrame gameScreenFrame;
    
    protected String saveFile = "score.txt";

    protected Countdown countdown;
    
    protected boolean isGameFinished = false;
    
    public Levels(int gameScreenX, int gameScreenY, JFrame gameScreen, int spaceShipValue){
        gameScreenFrame = gameScreen;
        
        timer.start();
        
        backgroundImage = returnImage("background.png");
        
        countdown = new Countdown(60);
        
        if (spaceShipValue == 1){
            spaceShip = new SpaceShip1(gameScreenX, gameScreenY, 520);
        }
        else if (spaceShipValue == 2){
            spaceShip = new SpaceShip2(gameScreenX, gameScreenY, 520);
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        g.drawImage(backgroundImage, 0, 0, this);
        
        for (Barricade barricade : barricades){
                barricade.DrawBarricade(g, this);
            }

            for (AlienShip alienShip : alienShips){
                alienShip.DrawSpaceShip(g, this);
            }

        for (Explosion explosion : explosions){
            if (!explosion.isFinished){
                explosion.DrawExplosion(g, this);
            }
            else {
                explosions.remove(explosion);
            }
        }
            
            g.setColor(Color.BLUE);

            for (Fire fire : fires){
                if (false){
                    fires.remove(fire);
                }

                else {
                    fire.DrawFire(g);

                    for (AlienShip alienShip : alienShips){
                        if (fire.CollisionCheck(alienShip.getAlienShipCollider())){
                            fires.remove(fire);
                            RandomPowerUp(alienShip);
                            alienShips.remove(alienShip);
                            explosions.add(alienShip.CreateExplosion());
                            score += 100;
                            if (alienShips.isEmpty()){
                                EndGame();
                            }
                        }
                    }

                    for (Barricade barricade : barricades){
                        if (fire.CollisionCheck(barricade.getBarricadeCollider())){
                            fires.remove(fire);
                        }
                    }
                }
            }
            
            for (PowerUp powerUp : powerUps){
                if (powerUp.Countdown()){
                    powerUps.remove(powerUp);
                }
                else {
                    powerUp.DrawPowerUp(g, this);
                }
            }

            spaceShip.DrawSpaceShip(g, this);
            g.setColor(Color.RED);
  
            g.setFont(new Font("Dialog", Font.BOLD, 20));
            
            spaceShip.DrawVelocityAndAngle(g, this);
            g.drawString("Score: " + score, 30, 45);   
            g.drawString("Time Left: " + countdown.getCountdownTime(), 900, 45);  
    }
    
    public void RandomPowerUp(AlienShip alienShip){
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        
        switch(randomNumber){
            case 0:
                powerUps.add(new SpeedPowerUp(alienShip.getAlienShipX(), alienShip.getAlienShipY()));
                break;
            case 1:
                powerUps.add(new DespeedPowerUp(alienShip.getAlienShipX(), alienShip.getAlienShipY()));
                break;  
        }
    }
    
    public void EndGame(){
        countdown.stopCountdown();
        
        String endNotification = "";
        timer.stop();
        
        if (countdown.getCountdownTime() > 0){
            score *= countdown.getCountdownTime() * 0.5;
            endNotification += "You won!" + "\nYour score is: " + score;
            endNotification += "\nTime Left: " + countdown.getCountdownTime();
        
            if (score > highScore){
                highScore = score;
                SaveScore(saveFile, highScore);
                endNotification += "\nNew High Score!!";
            }       
            else {
                endNotification += "\nHigh Score: " + highScore;
            }
        }
        
        else {
            endNotification += "You Lose! Time is up!";
            endNotification += "\nHigh Score: " + highScore;
        }
        
        gameScreenFrame.repaint();
        
        JOptionPane.showMessageDialog(this, endNotification);
        
        gameScreenFrame.getContentPane().removeAll(); 
        
        MainMenu mm = new MainMenu(1050, 900, gameScreenFrame);
        
        gameScreenFrame.add(mm, BorderLayout.CENTER);
        
        mm.setLayout(null);
        
        gameScreenFrame.setVisible(true);
        
        gameScreenFrame.repaint();
    }
    
    public void LoadScore(String fileName){
        File file = new File(fileName);
        
        if (!file.exists()){
            try {
                file.createNewFile();
                SaveScore(fileName, 0);
            } catch (IOException ex) {
                Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream(fileName);
            int value;
            String scoreString = "";
            while ((value = fis.read()) != -1){
                scoreString += (char) value;
            }
            highScore = Integer.parseInt(scoreString);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if (fis != null){
                    fis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void SaveScore(String fileName, int variable){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fileName);
            String highScoreString = String.valueOf(variable);
            byte[] highScoreArray = highScoreString.getBytes();
            fos.write(highScoreArray);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void repaint(){
        super.repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
            spaceShip.Stop();
        }
        pressedKeys.remove(e.getKeyCode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (countdown.getCountdownTime() == 0 && !isGameFinished){
                isGameFinished = true;
                EndGame();
            }
        
        for (Fire fire : fires){
            fire.MoveFire();
        }
        
        for (AlienShip alienShip : alienShips){
            alienShip.MoveAlienShip();
        }

        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_W:
                        spaceShip.Go();
                        break;
                    case KeyEvent.VK_A:
                        spaceShip.rotate(-5);
                        break;
                    case KeyEvent.VK_D:
                        spaceShip.rotate(+5);
                        break;
                    case KeyEvent.VK_SPACE:
                        spaceShip.createFire(fires);
                        break;    
                }
            }
        }
        
       spaceShip.Countdown();
       
       repaint();
    }
    
    public BufferedImage returnImage(String imageFileName){
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(new FileImageInputStream(new File(imageFileName)));
        } catch (IOException ex) {
            Logger.getLogger(Levels.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempImage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Rectangle temp = new Rectangle(e.getPoint().x - 1, e.getPoint().y - 1, 2, 2);
        for (PowerUp powerUp : powerUps){
            if (powerUp.CollisionCheck(temp)){
                powerUp.UsePowerUp(spaceShip);
                powerUps.remove(powerUp);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

}