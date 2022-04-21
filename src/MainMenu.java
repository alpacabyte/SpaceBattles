import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainMenu extends JPanel{
    protected BufferedImage backgroundImage;
    private int spaceShipValue = 0;
    public MainMenu(int gameScreenX, int gameScreenY, JFrame gameScreen) {
        JRadioButton jrbtSpaceship1 = new JRadioButton("Spaceship 1", new ImageIcon("rocket1.png"), true);
        JRadioButton jrbtSpaceship2 = new JRadioButton("Spaceship 2", new ImageIcon("rocket2.png"), false);
        
        gameScreen.setSize(gameScreenX, gameScreenY);
        
        JButton loadLevel1 = new JButton("Level 1 - High Score: " + LoadScore("score1.txt"));
        JButton loadLevel2 = new JButton("Level 2 - High Score: " + LoadScore("score2.txt"));
        JButton loadLevel3 = new JButton("Level 3 - High Score: " + LoadScore("score3.txt"));
        
        loadLevel1.setBounds(375, 350, 300, 30);
        loadLevel2.setBounds(375, 400, 300, 30);
        loadLevel3.setBounds(375, 450, 300, 30);
        
        loadLevel1.addActionListener((ActionEvent e) -> {
            if (jrbtSpaceship1.isSelected()){
                spaceShipValue = 1;
            }
            else {
                spaceShipValue = 2;
            }
            
            Level1 level = new Level1(gameScreenX, gameScreenY, gameScreen, spaceShipValue);
            LoadLevel(gameScreen, level);
        });

        loadLevel2.addActionListener((ActionEvent e) -> {
            if (jrbtSpaceship1.isSelected()){
                spaceShipValue = 1;
            }
            else {
                spaceShipValue = 2;
            }
            
            Level2 level = new Level2(gameScreenX, gameScreenY, gameScreen, spaceShipValue);
            LoadLevel(gameScreen, level);
        });
        
        loadLevel3.addActionListener((ActionEvent e) -> {
            if (jrbtSpaceship1.isSelected()){
                spaceShipValue = 1;
            }
            else {
                spaceShipValue = 2;
            }
            
            Level3 level = new Level3(gameScreenX, gameScreenY, gameScreen, spaceShipValue);
            LoadLevel(gameScreen, level);
        });
        
        add(loadLevel1);
        add(loadLevel2);
        add(loadLevel3);
        
        JLabel lblName = new JLabel("Space Battles");
        Font labelFont = lblName.getFont();
        lblName.setFont(new Font(labelFont.getName(), Font.ITALIC, 50));
        lblName.setBounds(365, 100, 310, 50);
        lblName.setForeground(Color.red);
        add(lblName);
        
        jrbtSpaceship1.setSelectedIcon(new ImageIcon("rocket1selected.png"));
        jrbtSpaceship1.setBounds(300, 650, 150, 150);
        add(jrbtSpaceship1);
        
        jrbtSpaceship2.setSelectedIcon(new ImageIcon("rocket2selected.png"));
        jrbtSpaceship2.setBounds(575, 650, 150, 150);
        add(jrbtSpaceship2);
        
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(jrbtSpaceship1);
        genderGroup.add(jrbtSpaceship2);
        
        JLabel j1 = new JLabel();
        j1.setIcon(new ImageIcon("background.png"));
        j1.setBounds(0, 0, gameScreenX, gameScreenY);
        add(j1);
    }
    
    private void LoadLevel(JFrame gameScreen, Levels level){
        gameScreen.getContentPane().removeAll();
        gameScreen.repaint();

        gameScreen.add(level);
            
        level.requestFocus();
        level.addMouseListener(level);
        level.addKeyListener(level);
        level.setFocusable(true);
        level.setFocusTraversalKeysEnabled(false);
            
        gameScreen.setVisible(true);
    }
    
    public int LoadScore(String fileName){
        File file = new File(fileName);
        int highScore = 0;
        
        if (!file.exists()){
            return highScore;
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
        return highScore;
    }
    
}