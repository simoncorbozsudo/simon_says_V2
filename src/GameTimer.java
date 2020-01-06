import java.util.Timer;
import java.util.TimerTask;
public class GameTimer {
    private Timer timer;
    private Controler ctrl;
    private int timeout;

    public GameTimer(int timeout){
        this.timeout = timeout;
    }

    public void startTimer() {
        timer = new Timer();
        timer.schedule(new Task(), timeout * 1000);
    }

    class Task extends TimerTask {
        public void run() {
            ctrl.stopGame();
            timer.cancel(); //Terminate the timer thread
        }
    }
    public void cancelTimer(){
        this.timer.cancel();
    }
    public void setCtrl(Controler ctrl){
        this.ctrl = ctrl;
    }
}

