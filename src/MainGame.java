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
        score = 0;
        createSequence(size);
    }

    private void createSequence (int size) {
        for (int i = 0; i <size ; i++) {
            addToColorSeq();
        }
    }
    public void addToColorSeq(){
        int rand = (int)(Math.random() * (3 - 0 +1)) + 0;
        if(rand == 0)colorSeq.add(Color.BLUE);
        if(rand == 1)colorSeq.add(Color.YELLOW);
        if(rand == 2)colorSeq.add(Color.RED);
        if(rand == 3)colorSeq.add(Color.GREEN);
    }

    public boolean checkCurrSeq(Color color) {
        return color.equals(colorSeq.get(currIndex));
    }

    public void incrementScore() {
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
    public ArrayList<Color> getColorSeq(){
        return colorSeq;
    }
    public int getCurrIndex(){
        return currIndex;
    }
    public void setCurrIndex(int val){
        this.currIndex = val;
    }
}