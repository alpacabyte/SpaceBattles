
import javax.swing.JFrame;

public class Level1 extends Levels{
    
    public Level1(int gameScreenX, int gameScreenY, JFrame gameScreen, int spaceShipValue) {
        super(gameScreenX, gameScreenY, gameScreen, spaceShipValue);
        
        saveFile = "score1.txt";
        
        this.gameScreenX = gameScreenX;
        this.gameScreenY = gameScreenY;
        
        alienShips.add(new alienShipType1(gameScreenX, gameScreenY, 0 , 10));
        
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 0 , 100));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 240 , 100));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 480 , 100));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 720 , 100));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 960 , 100));
        
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 0 , 200));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 240 , 200));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 480 , 200));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 720 , 200));
        alienShips.add(new alienShipType2(gameScreenX, gameScreenY, 960 , 200));
        
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 0 , 300));
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 240 , 300));
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 480 , 300));
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 720 , 300));
        alienShips.add(new alienShipType3(gameScreenX, gameScreenY, 960 , 300));
        
        barricades.add(new Barricade(0, 450));
        barricades.add(new Barricade(190, 450));
        barricades.add(new Barricade(380, 450));
        barricades.add(new Barricade(570, 450));
        barricades.add(new Barricade(760, 450));
        barricades.add(new Barricade(950, 450));
        
        LoadScore(saveFile);
    }
    
}
