package hi.verkefni.vidmot;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import vinnsla.Klukka;
import vinnsla.Leikur;

import java.util.HashMap;

public class GoldController {

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

    Klukka klukka = new Klukka(60);
    Leikur leikur;

    private static final int EASY_TIMER = 60;
    private static final int MEDIUM_TIMER = 45;
    private static final int HARD_TIMER = 30;

    private static MenuController menuController = new MenuController();

    boolean Igangi = false;


    // Variable to store the direction
    private int stefna;

    public static GoldController getInstance() {
        if (instance == null) {
            instance = new GoldController();
        }
        return instance;
    }

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

    private void stefna(GoldController sc, Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    // Update the direction using the variable
                    sc.setStefna(map.get(event.getCode()).getGradur());
                    afram();
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
            if (stefna == 90) {
                fxGrafari.setY(Y - H);
            } else if (stefna == 180) {
                fxGrafari.setX(X - W);
            } else if (stefna == 270) {
                fxGrafari.setY(Y + H);
            } else if (stefna == 360) {
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
}

