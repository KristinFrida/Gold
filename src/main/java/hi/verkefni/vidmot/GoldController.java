package hi.verkefni.vidmot;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;

public class GoldController {

    private static GoldController instance;

    @FXML
    private Pane fxLeikbord;

    private HashMap<KeyCode, Stefna> map = new HashMap<KeyCode, Stefna>();

    @FXML
    private Rectangle fxGrafari;

    // Variable to store the direction
    private int stefna;

    public static GoldController getInstance() {
        if (instance == null) {
            instance = new GoldController();
        }
        return instance;
    }

    public void setStefna(int upp) {
        // Assign the direction to the variable
        this.stefna = upp;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        System.out.println("Stilla erfiðleikastigið: " + difficultyLevel);
    }

    private void orvatakkar(GoldController sc, Scene scene) {
        map.put(KeyCode.UP, Stefna.UPP);
        map.put(KeyCode.DOWN, Stefna.NIDUR);
        map.put(KeyCode.RIGHT, Stefna.HAEGRI);
        map.put(KeyCode.LEFT, Stefna.VINSTRI);

        stefna(sc, scene);
    }

    private void stefna(GoldController sc, Scene scene){
        scene.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {
                    // Update the direction using the variable
                    sc.setStefna(map.get(event.getCode()).getGradur());
                    afram();
                });
    }

    public void getOrvatakkar(GoldController sc, Scene scene){
        orvatakkar(sc, scene);
    }

    private void afram(){
        double X = fxGrafari.getX();
        double Y = fxGrafari.getY();
        double W = fxGrafari.getWidth();
        double H = fxGrafari.getHeight();

        // Use the stored direction from the variable
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
