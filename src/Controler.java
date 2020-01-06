import javafx.application.Platform;
import javafx.animation.Timeline;
import java.awt.*;
import java.util.ArrayList;
import static javafx.application.Platform.*;

public class Controler {
    private MainGame game;
    private View view;
    private Model model;
    private GameTimer gTimer;
    public void setView(View view){
        this.view = view;
    }
    public void setModel(Model model){
        this.model = model;
    }

    public void startGame(int timeout, int size) {
        //called when player hits start
        game = new MainGame(size);
        gTimer = new GameTimer(timeout);
        gTimer.setCtrl(this);
        Timeline tl = view.displayColorSequence(game.getColorSeq());
        System.out.println("fsdaf");
        tl.play();
        tl.setOnFinished(event -> gTimer.startTimer());
    }

    public void checkSeq(Color color) {
        //stop timer
        gTimer.cancelTimer();
        if(this.game.checkCurrSeq(color)){
            if(this.game.getCurrIndex() == this.game.getColorSeq().size()-1){
                this.game.incrementScore();
                this.game.setCurrIndex(0);
                this.game.addToColorSeq();
                Timeline t = view.displayColorSequence(game.getColorSeq());
                t.play();
                t.setOnFinished(event->gTimer.startTimer());
                gTimer.startTimer();
            }else{
                this.game.incrementCurrIndex();
                gTimer.startTimer();
            }
        }else{
           stopGame();
        }
    }
    public void sendData(String name) {
        model.recordData(name,game.getScore());
    }

    public String getData(){
        return model.getData();
    }

    public void stopGame(){
        //disable onclick method for game
        runLater(
                () -> {
                    view.displaynameasking();                }
        );

    }
    public void startTimer(){
        gTimer.startTimer();
    }
}