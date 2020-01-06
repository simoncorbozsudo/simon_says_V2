import java.awt.*;
import java.util.ArrayList;

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
        view.displayColorSequence(game.getColorSeq());
        gTimer.startTimer();
    }

    public void checkSeq(Color color) {
        //stop timer
        gTimer.cancelTimer();
        if(this.game.checkCurrSeq(color)){
            this.game.incrementScore();
            if(this.game.getCurrIndex() == this.game.getColorSeq().size()){
                this.game.setCurrIndex(0);
                this.game.addToColorSeq();
                view.displayColorSequence(game.getColorSeq());
            }else{
                this.game.incrementCurrIndex();
            }
            gTimer.startTimer();
        }else{
           stopGame();
        }
    }
    public void sendData(String name) {
        model.recordData(name,game.getScore());
    }

    public void stopGame(){
        //disable onclick method for game
       // view.displaynameasking
    }
}