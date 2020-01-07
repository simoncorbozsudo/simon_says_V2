import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.*;
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
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;


public class View extends Application {

    private DoubleProperty tempSequence = new SimpleDoubleProperty();
    private IntegerProperty tailleSequence = new SimpleIntegerProperty();
    private IntegerProperty tempUserProp = new SimpleIntegerProperty();

    private int tempUserValue;
    private double tempSequen ;
    private int tailleSequenceVal;
    private BorderPane root = new BorderPane();
    private FlowPane jeu = new FlowPane();
    private Button vert = new Button("Vert");
    private Button bleu = new Button("Bleu");
    private Button jaune = new Button("Jaune");
    private Button rouge = new Button("Rouge");
    private TextArea textABleu = new TextArea();
    private TextArea textAVert = new TextArea();
    private TextArea textARouge = new TextArea();
    private TextArea textAJaune = new TextArea();
    private Menu tempSeq = new Menu("Temps séq");
    private Menu tempUser = new Menu("Temps input");
    private Menu seqInit = new Menu("Longueur séq init");

    private MenuBar mBar = new MenuBar();
    private VBox rightVbox = new VBox();
    private HBox bleuVertHbox = new HBox();
    private HBox jauneRougeHbox = new HBox();
    private Button showresultat = new Button("Affiche résultats");
    private Button stopGame = new Button("Stoper jeu");
    private HBox botHbox = new HBox();
    private Button newGame = new Button("Nouveau jeu");
    private Controler ctrl;
    private BooleanProperty readyForInput ;
    private TextField input = new TextField();
    private Popup popup = new Popup();
    private TextInputDialog inDialog = new TextInputDialog("Guest");
    private  Alert dialog = new Alert(Alert.AlertType.INFORMATION);
    private Slider tempSeqSlider = new Slider(0.5, 4 , 1);
    private Slider tailleSeqsli = new Slider(2, 5 , 3);
    private Slider tempUsersli = new Slider(1,10,5);




    public Timeline displayColorSequence(ArrayList<Color> color) {
        tempSequen = tempSequence.getValue();
        Timeline timeline = new Timeline();
        Duration delayBetweenMessages = Duration.seconds(tempSequen);
        Duration frame = delayBetweenMessages ;
        for (Color msg : color) {
            System.out.println(msg);
            timeline.getKeyFrames().add(new KeyFrame(frame, e -> {
                if (Color.BLUE == msg) {
                    textABleu.setStyle("-fx-control-inner-background:#0095ff");
                    textARouge.setStyle("-fx-control-inner-background:#87121b");
                    textAJaune.setStyle("-fx-control-inner-background:#808211");
                    textAVert.setStyle("-fx-control-inner-background:#0e5914");
                } else if (Color.RED == msg) {
                    textARouge.setStyle("-fx-control-inner-background:#ff0015");
                    textAJaune.setStyle("-fx-control-inner-background:#808211");
                    textAVert.setStyle("-fx-control-inner-background:#0e5914");
                    textABleu.setStyle("-fx-control-inner-background:#0f47a6");
                } else if (Color.YELLOW == msg) {
                    textAJaune.setStyle("-fx-control-inner-background:#fbff00");
                    textAVert.setStyle("-fx-control-inner-background:#0e5914");
                    textABleu.setStyle("-fx-control-inner-background:#0f47a6");
                    textARouge.setStyle("-fx-control-inner-background:#87121b");
                } else if (Color.GREEN == msg) {
                    textAVert.setStyle("-fx-control-inner-background:#00ff15");
                    textABleu.setStyle("-fx-control-inner-background:#0f47a6");
                    textARouge.setStyle("-fx-control-inner-background:#87121b");
                    textAJaune.setStyle("-fx-control-inner-background:#808211");
                }else{
                    textABleu.setStyle("-fx-control-inner-background:#005eff");
                    textARouge.setStyle("-fx-control-inner-background:#87121b");
                    textAJaune.setStyle("-fx-control-inner-background:#808211");
                    textAVert.setStyle("-fx-control-inner-background:#0e5914");
                }
            }));
            frame = frame.add(delayBetweenMessages);

        }
        resetcolor();
        timeline.statusProperty().addListener((obs, oldStatus, newStatus) -> {
            readyForInput.set(newStatus != Animation.Status.RUNNING);
            if (newStatus != Animation.Status.RUNNING) {
                input.requestFocus();
            }
        });
        return timeline ;
    }


    public void resetcolor(){
        textABleu.setStyle("-fx-control-inner-background:#0f47a6");
        textARouge.setStyle("-fx-control-inner-background:#87121b");
        textAJaune.setStyle("-fx-control-inner-background:#808211");
        textAVert.setStyle("-fx-control-inner-background:#0e5914");
    }

    public String displaynameasking() {
        Optional<String> textIn = inDialog.showAndWait();
        showresultat.setDisable(false);
        newGame.setDisable(false);
        disableButtons(true);
        if (textIn.isPresent()) {
           return textIn.get();
        }
        return "Guest";
    }

    public void displayResult(String resultat){
        dialog.setTitle("Liste des résultats");
        dialog.setHeaderText("Liste des résultats enregistrés :");
        dialog.setContentText(resultat);
        dialog.showAndWait();
    }

    public void disableButtons(boolean state){
        vert.setDisable(state);
        jaune.setDisable(state);
        rouge.setDisable(state);
        bleu.setDisable(state);
        stopGame.setDisable(state);
    }
    @Override
    public void init() {
        tempSequence.bind(tempSeqSlider.valueProperty());
        tempSequen = tempSequence.getValue();
        tailleSequence.bind(tailleSeqsli.valueProperty());
        tailleSequenceVal = tailleSequence.getValue();
        tempUserProp.bind(tempUsersli.valueProperty());
        tempUserValue = tempUserProp.getValue();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Simon says");
        ctrl = new Controler();
        Model model = new Model();
        ctrl.setView(this);
        ctrl.setModel(model);

        jeu.getChildren().addAll(vert, bleu, jaune, rouge);
        Menu mOption = new Menu("_Options");
        tempSeqSlider.setShowTickLabels(true);
        tempSeqSlider.setShowTickMarks(true);
        tempSeqSlider.setMajorTickUnit(1);
        tempSeqSlider.setMinorTickCount(1);
        tempSeqSlider.setSnapToTicks(true);
        tempSeqSlider.setBlockIncrement(1);

        tailleSeqsli.setShowTickLabels(true);
        tailleSeqsli.setShowTickMarks(true);
        tailleSeqsli.setMajorTickUnit(1);
        tailleSeqsli.setMinorTickCount(1);
        tailleSeqsli.setSnapToTicks(true);
        tailleSeqsli.setBlockIncrement(1);

        tempUsersli.setShowTickLabels(true);
        tempUsersli.setShowTickMarks(true);
        tempUsersli.setMajorTickUnit(1);
        tempUsersli.setMinorTickCount(0);
        tempUsersli.setSnapToTicks(true);
        tempUsersli.setBlockIncrement(1);

        CustomMenuItem tseqsli = new CustomMenuItem(tempSeqSlider);
        CustomMenuItem taillseq = new CustomMenuItem(tailleSeqsli);
        CustomMenuItem userTime = new CustomMenuItem(tempUsersli);
        tempUser.getItems().add(userTime);
        tempSeq.getItems().add(tseqsli);
        seqInit.getItems().add(taillseq);
        mOption.getItems().addAll(tempSeq,seqInit,tempUser);
        mBar.getMenus().add(mOption);
        botHbox.getChildren().add(newGame);
        inDialog.setTitle("Enregistrement du score");
        inDialog.setHeaderText("Veuillez entrer votre nom pour enregistrer votre score");
        inDialog.setContentText("Nom :");
        textABleu.setStyle("-fx-control-inner-background:#0f47a6");
        textABleu.setEditable(false);
        textARouge.setStyle("-fx-control-inner-background:#87121b");
        textARouge.setEditable(false);
        textAJaune.setStyle("-fx-control-inner-background:#808211");
        textAJaune.setEditable(false);
        textAVert.setStyle("-fx-control-inner-background:#0e5914");
        textAVert.setEditable(false);
        bleuVertHbox.getChildren().addAll(textABleu, textAVert);
        jauneRougeHbox.getChildren().addAll(textAJaune, textARouge);
        rightVbox.getChildren().addAll(bleuVertHbox, jauneRougeHbox);
        botHbox.getChildren().add(showresultat);
        stopGame.setDisable(true);
        vert.setDisable(true);
        jaune.setDisable(true);
        rouge.setDisable(true);
        bleu.setDisable(true);
        botHbox.getChildren().add(stopGame);
        showresultat.setOnAction(event -> {
            displayResult(ctrl.getData());
        });
        stopGame.setOnAction(event -> {
            ctrl.stopGameFromButton();
        });
        root.setTop(mBar);
        root.setCenter(jeu);
        root.setLeft(rightVbox);
        root.setBottom(botHbox);
        readyForInput = new SimpleBooleanProperty(false);
        newGame.setOnAction(event -> {
            tailleSequenceVal = tailleSequence.getValue();
            tempUserValue = tempUserProp.getValue();
            ctrl.startGame(tempUserValue,tailleSequenceVal);
            showresultat.setDisable(true);
            newGame.setDisable(true);

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