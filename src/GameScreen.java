import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GameScreen extends JFrame{
    
    public GameScreen(){
        setTitle("Space Battles");
        setResizable(false);
        setFocusable(false);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        MainMenu mm = new MainMenu(1050, 900, this);
        
        add(mm, BorderLayout.CENTER);
        
        mm.setLayout(null);
        
        setVisible(true);
    } 
   
}
