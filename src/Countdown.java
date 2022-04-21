import java.util.logging.Level;
import java.util.logging.Logger;

public class Countdown implements Runnable{

    private boolean isFinished = false;
    
    private int countdownTime = 0;

    public int getCountdownTime() {
        return countdownTime;
    }
    
    public Countdown(int countdownTime) {
        this.countdownTime = countdownTime;
        
        Thread countdown = new Thread(this);
        countdown.start();
    }

    @Override
    public void run() {
        try {
            for (;countdownTime > 0 && !isFinished; countdownTime--){
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Countdown.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stopCountdown(){
        isFinished = true;
    }
}
