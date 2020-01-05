import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
public class MainGame {

    private int score;
    private Timer timer;
    private ArrayList<Color> colorSeq;
    private int currIndex;
    private Controler ctrl;
    public MainGame(int size, int timeout) {
        currIndex = 0;
        colorSeq = new ArrayList<>();
        createSequence(size);
    }

    private void createSequence (int size) {
        for (int i = 0; i <size ; i++) {
            addToColorSeq();
        }
    }
    private void addToColorSeq(){
        int rand = (int)(Math.random() * (3 - 0 +1)) + 0;
        if(rand == 0)colorSeq.add(Color.BLUE);
        if(rand == 1)colorSeq.add(Color.YELLOW);
        if(rand == 2)colorSeq.add(Color.RED);
        if(rand == 3)colorSeq.add(Color.GREEN);
    }

    private boolean checkCurrSeq(Color color) {
        return color.equals(colorSeq.get(currIndex));
    }

    private void incrementScore() {
        score++;
    }

    public void gameLoop(){

    }
    public int getScore() {
        return this.score;
    }

    public void setCtrl(Controler controler){
        this.ctrl = controler;
    }

    public boolean appliqueModification(int initSize, int TimeOut, int VitessSeq) {
        // TODO - implement MainGame.appliqueModification
        throw new UnsupportedOperationException();
    }

}