import java.awt.*;
import java.util.ArrayList;
public class MainGame {

    private int score;
    private ArrayList<Color> colorSeq;
    private int currIndex;
    private Controler ctrl;
    public MainGame(int size) {
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

    public boolean checkCurrSeq(Color color) {
        boolean ret =  color.equals(colorSeq.get(currIndex));
        if(currIndex == colorSeq.size()){
            incrementScore();
            currIndex=0;
        }
        return ret;
    }

    private void incrementScore() {
        score++;
    }
    public void incrementCurrIndex(){
        currIndex++;
    }


    public int getScore() {
        return this.score;
    }

    public void setCtrl(Controler controler){
        this.ctrl = controler;
    }
}