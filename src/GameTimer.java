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
        System.out.println("TIMER STARTED");
        timer.schedule(new Task(), timeout * 1000);
    }

    class Task extends TimerTask {
        public void run() {
            System.out.println("TIMER IS OVER");
            timer.cancel(); //Terminate the timer thread
        }
    }
    public void cancelTimer(){
        System.out.println("timerCancelled");
        this.timer.cancel();
    }
    public void setCtrl(Controler ctrl){
        this.ctrl = ctrl;
    }
}

