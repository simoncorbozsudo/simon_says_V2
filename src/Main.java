import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        Application.launch(View.class, args);
        Controler ctrl = new Controler();
        Model model = new Model();
        View view = new View();
        ctrl.setModel(model);
        ctrl.setView(view);
        model.setCtrl(ctrl);
        view.setCtrl(ctrl);
        //call view to display menu
    }
}
