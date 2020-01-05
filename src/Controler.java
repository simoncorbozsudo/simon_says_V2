import java.awt.*;

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
        //view.switchToDisplay
        //view.
    }
    public void checkSeq(Color color) {
        //stop timer
        gTimer.cancelTimer();
        if(this.game.checkCurrSeq(color)){
            this.game.incrementCurrIndex();
        }else{
           stopGame();
        }
    }
    public void sendData(String name) {
        model.recordData(name,getScores());
    }

    public int getScores() {
        return game.getScore();
    }
    public void stopGame(){

    }
}