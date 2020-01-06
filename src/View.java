import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class View {
    private Controler ctrl;
    private int seqSpeed;
    /**
     *
     * @param ColorSeq
     */
    public void displayColorSequence(ArrayList<Color> ColorSeq) {
    }

    public void onClick() {
        // TODO - implement View.onClick
        throw new UnsupportedOperationException();
    }

    public String askPlayerName() {
        // TODO - implement View.askPlayerName
        throw new UnsupportedOperationException();
    }

    public void afficheParametreDifficulte() {
        // TODO - implement View.afficheParametreDifficulte
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param initSize
     * @param TimeOut
     * @param VitessSeq
     */
    public boolean appliqueModification(int initSize, int TimeOut, int VitessSeq) {
        // TODO - implement View.appliqueModification
        throw new UnsupportedOperationException();
    }

    public void displayScores() {
        // TODO - implement View.displayScores
        throw new UnsupportedOperationException();
    }
    public void setCtrl(Controler ctrl){
        this.ctrl = ctrl;
    }
}