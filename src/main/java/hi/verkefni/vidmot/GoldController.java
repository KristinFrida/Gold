package hi.verkefni.vidmot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import vinnsla.Leikur;

import java.util.HashMap;



public class GoldController {
    // viðmótshlutir
    @FXML
    private ListView<Integer> fxStigin; //stigataflan
    @FXML
    private Leikbord leikbord;    // leiksvæðið

    private Leikur leikur;  // vinnslan

    private Timeline t;
    public static final int INTERVAL = 100;
    private static final int UPP = 90;
    private static final int NIDUR = 270;
    private static final int VINSTRI = 180;
    private static final int HAEGRI = 360;

    private static final HashMap<KeyCode, Integer> map = new HashMap<KeyCode, Integer>();
    private static GoldController instance;

    private MenuController menuStyringController;

    private GoldController() {
        // Private constructor to prevent instantiation
    }

    public static GoldController getInstance() {
        if (instance == null) {
            instance = new GoldController();
        }
        return instance;
    }

    public void setMenuStyringController(MenuController menuStyringController) {
        this.menuStyringController = menuStyringController;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        // Implement logic to store the selected difficulty level
        // For example, you can store it in a variable or pass it to another method.
        // Here, let's assume you have a method setDifficultyLevel in your GoldController
        // that accepts the selected difficulty level as a parameter.
        System.out.println("Stilla erfiðleikastigið: " + difficultyLevel);
        // menuStyringController.setDifficultyLevel(difficultyLevel);
    }
    /**
     * Tengir örvatakka við fall sem á að keyra í controller
     **/
    public void orvatakkar() {
        map.put(KeyCode.UP, UPP);   // setjum upp beina aðganginn frá örvatökkunum og í hornið
        map.put(KeyCode.DOWN, NIDUR);
        map.put(KeyCode.RIGHT, HAEGRI);
        map.put(KeyCode.LEFT, VINSTRI);

    }
    public Leikur getLeikur() {
        return leikur;
    }
    /**
     * Setur upp Animation fyrir leikinn og setur upp leikjalykkjuna
     */
    public void hefjaLeik() {
        KeyFrame k = new KeyFrame(Duration.millis(INTERVAL),
                e -> {
                    leikbord.borda();
                    leikbord.afram();
                });
        t = new Timeline(k);
        t.setCycleCount(Timeline.INDEFINITE);   // leikurinn leikur endalaust
        leikbord.nyrLeikur(3);
        t.play();
    }

}
