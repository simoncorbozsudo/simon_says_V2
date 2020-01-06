import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;


public class View extends Application {

    private BorderPane root = new BorderPane();
    private FlowPane jeu = new FlowPane();
    private Button vert = new Button("Vert");
    private Button bleu = new Button("Bleu");
    private Button jaune = new Button("Jaune");
    private Button rouge = new Button("Rouge");
    private TextArea textSeq = new TextArea();
    private MenuBar mBar = new MenuBar();
    private HBox botHbox = new HBox();
    private Button newGame = new Button("New Game");
    private Controler ctrl;
    private BooleanProperty readyForInput ;
    private TextField input = new TextField();
    private Popup popup = new Popup();
    private TextInputDialog inDialog = new TextInputDialog("Guest");




    public Timeline displayColorSequence(ArrayList<Color> color) {
        Timeline timeline = new Timeline();
        Duration delayBetweenMessages = Duration.seconds(1);
        Duration frame = delayBetweenMessages ;
        for (Color msg : color) {
            System.out.println(msg);
            timeline.getKeyFrames().add(new KeyFrame(frame, e -> {
                    textSeq.setStyle("-fx-control-inner-background:#FFFFFF");
                    if(Color.BLUE == msg)textSeq.setStyle("-fx-control-inner-background:#005eff");
                    else if (Color.RED == msg)textSeq.setStyle("-fx-control-inner-background:#f71b1b");
                    else if (Color.YELLOW == msg) textSeq.setStyle("-fx-control-inner-background:#fbff00");
                    else if (Color.GREEN == msg) textSeq.setStyle("-fx-control-inner-background:#00ff15");
                    else textSeq.setStyle("-fx-control-inner-background:#FFFFFF");
                }));
            frame = frame.add(delayBetweenMessages);

        }
        timeline.statusProperty().addListener((obs, oldStatus, newStatus) -> {
            readyForInput.set(newStatus != Animation.Status.RUNNING);
            if (newStatus != Animation.Status.RUNNING) {
                input.requestFocus();
            }
        });
        return timeline ;
    }




    public void onClick() {
        // TODO - implement View.onClick
        throw new UnsupportedOperationException();
    }

    public String displaynameasking() {
        Optional<String> textIn = inDialog.showAndWait();
        if (textIn.isPresent()) {
           return textIn.get();
        }
        return "rien";
    }

    public void afficheParametreDifficulte() {
        // TODO - implement View.afficheParametreDifficulte
        throw new UnsupportedOperationException();
    }

    /**
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

    public void setCtrl(Controler ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simon says");
        ctrl = new Controler();
        ctrl.setView(this);
        Model model = new Model();
        jeu.getChildren().addAll(vert, bleu, jaune, rouge);
        Menu mOption = new Menu("_Options");
        mBar.getMenus().add(mOption);
        botHbox.getChildren().add(newGame);
        inDialog.setTitle("A Text-Input Dialog");
        inDialog.setHeaderText("Account Login (or Guest Access)");
        inDialog.setContentText("Username :");
        root.setTop(mBar);
        root.setCenter(jeu);
        root.setLeft(textSeq);
        root.setBottom(botHbox);
        readyForInput = new SimpleBooleanProperty(false);
        newGame.setOnAction(event -> {
            ctrl.startGame(2,4);
        });
        vert.setOnAction(event -> {
            ctrl.checkSeq(Color.GREEN);
        });
        jaune.setOnAction(event -> {
            ctrl.checkSeq(Color.YELLOW);
        });
        rouge.setOnAction(event -> {
            ctrl.checkSeq(Color.RED);
        });
        bleu.setOnAction(event -> {
            ctrl.checkSeq(Color.BLUE);
        });

        popup.setAutoHide(true);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}