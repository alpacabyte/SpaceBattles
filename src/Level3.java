
import javax.swing.JFrame;

public class Level3 extends Levels{
    
    public Level3(int gameScreenX, int gameScreenY, JFrame gameScreen, int spaceShipValue) {
        super(gameScreenX, gameScreenY, gameScreen, spaceShipValue);
        
        saveFile = "score3.txt";
        
        this.gameScreenX = gameScreenX;
        this.gameScreenY = gameScreenY;
        
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 0 , -5));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 240 , 85));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 480 , 175));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 720 , 265));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 960 , 355));
        
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 960 , -5));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 720 , 85));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 240 , 265));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 0 , 355));
        
        alienShips.add(new alienShipType1(gameScreenX, gameScreenY, 0 , 190));
        alienShips.add(new alienShipType1(gameScreenX, gameScreenY, 240 , 190));
        alienShips.add(new alienShipType1(gameScreenX, gameScreenY, 480 , 190));
        alienShips.add(new alienShipType1(gameScreenX, gameScreenY, 720 , 190));
        alienShips.add(new alienShipType1(gameScreenX, gameScreenY, 960 , 190));
        
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 480 , -5));
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 480 , 85));
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 480 , 265));
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 480 , 355));
        
        barricades.add(new Barricade(0, 500));
        barricades.add(new Barricade(190, 500));
        barricades.add(new Barricade(380, 500));
        barricades.add(new Barricade(570, 500));
        barricades.add(new Barricade(760, 500));
        barricades.add(new Barricade(950, 500));
        
        LoadScore(saveFile);
    }
    
}
