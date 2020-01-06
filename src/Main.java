import java.awt.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Controler ctrl = new Controler();
        Model model = new Model();
        View view = new View();
        ctrl.setModel(model);
        ctrl.setView(view);
        model.setCtrl(ctrl);
        view.setCtrl(ctrl);
        //call view to display menu
        ctrl.startGame(1,100);
        ctrl.checkSeq(Color.red);
    }

}
