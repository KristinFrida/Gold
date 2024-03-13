package hi.verkefni.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import vinnsla.Klukka;
import vinnsla.Leikur;

import java.util.HashMap;
import java.util.Random;

public class GoldController {
    private boolean iGangi = false;

    private static GoldController instance;

    @FXML
    private Pane fxLeikbord;

    @FXML
    private Label fxStig;

    @FXML
    private Label fxTimiEftir;

    private HashMap<KeyCode, Stefna> map = new HashMap<KeyCode, Stefna>();

    @FXML
    private Rectangle fxGrafari;

    @FXML
    private Rectangle fxGull;

    private ObservableList<Gull> gullListi = FXCollections.observableArrayList();


    Klukka klukka = new Klukka(60);
    Leikur leikur;

    private static final int EASY_TIMER = 60;
    private static final int MEDIUM_TIMER = 45;
    private static final int HARD_TIMER = 30;

    private static MenuController menuController = new MenuController();

    boolean Igangi = false;


    // Variable to store the direction
    private int stefna;

    public void initialize() {
        leikur = new Leikur();
        //fxLeikbord.setLeikur(leikur);

        fxLeikbord.requestFocus();
        if (menuController != null) {
            menuController.setController(this);
        } else {
            System.err.println("menuController is null");
        }
        //fxStig.textProperty().bind(leikur.stiginProperty().asString());
        fxTimiEftir.textProperty().bind(klukka.timiProperty().asString());
    }

    public void setStefna(int upp) {
        // Assign the direction to the variable
        this.stefna = upp;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        System.out.println("Stilla erfiðleikastigið: " + difficultyLevel);

        switch (difficultyLevel) {
            case 1: // Easy
                klukka = new Klukka(EASY_TIMER);
                break;
            case 2: // Medium
                klukka = new Klukka(MEDIUM_TIMER);
                break;
            case 3: // Hard
                klukka = new Klukka(HARD_TIMER);
                break;
            default:
                System.err.println("Invalid difficulty level");
        }
        fxTimiEftir.textProperty().bind(klukka.timiProperty().asString());
    }

    private void orvatakkar(GoldController sc, Scene scene) {
        map.put(KeyCode.UP, Stefna.UPP);
        map.put(KeyCode.DOWN, Stefna.NIDUR);
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);

        stefna(sc, scene);
    }

    public void stefna(GoldController sc, Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    sc.setStefna(map.get(event.getCode()).getGradur());
                    afram();
                    grafaGull();
                });
    }

    public void getOrvatakkar(GoldController sc, Scene scene) {
        orvatakkar(sc, scene);
    }

    private void afram() {
        double X = fxGrafari.getX();
        double Y = fxGrafari.getY();
        double W = fxGrafari.getWidth();
        double H = fxGrafari.getHeight();

        if (Igangi) {
            if (stefna == 90 && Y > 0) { // UP
                fxGrafari.setY(Y - H);
            } else if (stefna == 180 && X > 0) { // LEFT
                fxGrafari.setX(X - W);
            } else if (stefna == 270 && Y + H < fxLeikbord.getHeight()) { // DOWN
                fxGrafari.setY(Y + H);
            } else if (stefna == 360 && X + W < fxLeikbord.getWidth()) { // RIGHT
                fxGrafari.setX(X + W);
            }
        }
    }


    private void randomStad(){
        double W = fxGull.getWidth();
        double H = fxGull.getHeight();
    }

    public void hefjaLeik() {
        Igangi = true;
        gullTimer();
    }

    public void raesaKlukku() {
        System.out.println("Initial Time: " + klukka.getTimi());
        klukka.tic();
        // Add a listener to the timi property to check when it becomes 0
        klukka.timiProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() == 0) {
                Igangi = false;
                System.out.println("Tími búinn, leikurinn er búinn");
            }
        });
    }

    public void onHaetta(){
        Igangi=false;
        klukka.setTimi(0);
        klukka.stop();
        showConfirmationDialog();
    }

    private void showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LEIKUR BÚINN");
        alert.setHeaderText(null);
        alert.setContentText("LEIKURINN ER BÚINN");

        // Customize button types
        ButtonType yesButton = new ButtonType("OK");

        alert.getButtonTypes().setAll(yesButton);

        // Show and wait for user response
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                // If user confirms, exit the application
                System.exit(0);
            }
        });
    }
    private void gullTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1800), event -> {
            if (Igangi) {
                virkjaGull();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    private static final Random random = new Random();

    private void virkjaGull() {
        int numberOfGoldItems = 2;  // Adjust the number as needed

        for (int i = 0; i < numberOfGoldItems; i++) {
            Gull gull = new Gull();
            randomStad(gull);
            fxLeikbord.getChildren().add(gull);
            gullListi.add(gull);
        }
        // Listener to ensure newly added Gull objects are displayed on the game board
        gullListi.addListener((ListChangeListener<Gull>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Gull gull1 : change.getAddedSubList()) {
                        if (!fxLeikbord.getChildren().contains(gull1)) {
                            fxLeikbord.getChildren().add(gull1);
                        }
                    }
                }
            }
        });
    }

    private void randomStad(Gull gull) {
        double boardWidth = fxLeikbord.getWidth();
        double boardHeight = fxLeikbord.getHeight();
        double goldWidth = gull.getWidth();
        double goldHeight = gull.getHeight();

        Random random = new Random();
        double randomX = random.nextDouble() * (boardWidth - goldWidth);
        double randomY = random.nextDouble() * (boardHeight - goldHeight);

        gull.setLayoutX(randomX);
        gull.setLayoutY(randomY);
    }

    private int score = 0; // Add a member variable to keep track of the score

    private void grafaGull() {
        var iterator = gullListi.iterator();
        while (iterator.hasNext()) {
            Gull gull = iterator.next();
            if (gull.getBoundsInParent().intersects(fxGrafari.getBoundsInParent())) {
                iterator.remove();
                gull.setVisible(false);

                // Increment the score and update the label
                score++;
                fxStig.setText("Stig : " + score);
            }
        }
    }
}

