package hi.verkefni.vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Random;

public class Leikbord extends Pane {
    // Fields
    private static final Random random = new Random();
    private final ObservableList<Grafari> grafariList = FXCollections.observableArrayList();
    private final ObservableList<Gull> gullList = FXCollections.observableArrayList();
    private Grafari grafari;
    private GoldController goldController;

    // Constructor
    public Leikbord() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leikbord-view.fxml"));
        fxmlLoader.setRoot(this);   // rótin á viðmótstrénu sett hér
        fxmlLoader.setController(this); // controllerinn settur hér en ekki í .fxml skránni
        try {
            fxmlLoader.load();          // viðmótstréð lesið inn (þ.e. .fxml skráin)
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    // Public methods
    public Grafari nyrGrafari() {
        if (grafari != null)
            getChildren().remove(grafari);
        grafari = new Grafari();
        getChildren().add(grafari);
        return grafari;
    }

    public void nyrLeikur(int fjoldiMatur) {
        hreinsaBord();
        grafari = nyrGrafari();
    }

    public void afram() {
        grafari.afram();
    }

    public void borda() {
        for (Gull g : gullList) {
            if (grafari.erEtur(g)) {
                goldController.getLeikur().vinningur();
                getChildren().remove(g);
                gullList.remove(g);
                return;
            }
        }
    }

    public void hreinsaBord() {
        hreinsaGrafara();
    }

    // Private methods
    private void hreinsaGrafara() {
        for (Grafari g : grafariList) {
            getChildren().remove(g);
        }
        grafariList.clear();
    }

    // Getter
    public GoldController getController() {
        return goldController;
    }
}
