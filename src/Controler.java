import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Controler {
    private MainGame game;
    private View view;
    private Model model;
    public void setMainGame(MainGame game){
        //called when user clicks on start
        this.game = game;
    }
    public void setView(View view){
        this.view = view;
    }
    public void setModel(Model model){
        this.model = model;
    }
    public void checkSeq(Color color) {
        //stop timer

        if(this.game.checkCurrSeq(color)){
            //resets timer thread
            //increment score
            this.game.incrementScore();
            //increment currIndex
            this.game.incrementCurrIndex();
        }else{
            //destroy timer thread
            //interrupt game (show end game on view)
            //call view to enter name (send name will take the rest of the work
            //dereferences game instance
        }
    }
    public void sendData(String name) {
        model.recordData(name,getScores());
    }

    public int getScores() {
        return game.getScore();
    }

}