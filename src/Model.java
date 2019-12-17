import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class Model extends Application {

    private BorderPane root = new BorderPane();
    private FlowPane jeu = new FlowPane();
    private Button vert = new Button("Vert");
    private Button bleu = new Button("Bleu");
    private Button jaune = new Button("Jaune");
    private Button rouge = new Button("Rouge");
    private MenuBar mBar = new MenuBar();


    /**
     * @param name
     * @param score
     */
    public void recordData(String name, int score) {
        // TODO - implement Model.recordData
        throw new UnsupportedOperationException();
    }

    public String getScores() {
        // TODO - implement Model.getScores
        throw new UnsupportedOperationException();
    }

    @Override
    public void init() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simon says");

        jeu.getChildren().addAll(vert, bleu, jaune, rouge);
        Menu mOption = new Menu("_Options");
        mBar.getMenus().add(mOption);
        root.setTop(mBar);
        root.setCenter(jeu);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        String[] a = {"rouge", "vert", "bleu", "jaune"};
        showSeq(a, 100);
    }

    public void showSeq(String[] seq, int vitesse) {
        for (String bouton : seq) {
            switch (bouton) {
                case "vert":
                    vert.setStyle("-fx-background-color: #03fc0f");
                    try {
                        //wait(1000);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //  vert.setStyle("-fx-background-color: #ffffff");
                    break;
                case "bleu":
                    bleu.setStyle("-fx-background-color: #002aff");
                    try {
                        // wait(1000);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //  bleu.setStyle("-fx-background-color: #ffffff");
                    break;
                case "rouge":
                    rouge.setStyle("-fx-background-color: #ff0000");
                    try {
                        // wait(1000);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //  rouge.setStyle("-fx-background-color: #ffffff");
                    break;
                case "jaune":
                    jaune.setStyle("-fx-background-color: #fffb00");
                    try {
                        //wait(1000);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // jaune.setStyle("-fx-background-color: #ffffff");
                    break;
            }

        }

    }

}