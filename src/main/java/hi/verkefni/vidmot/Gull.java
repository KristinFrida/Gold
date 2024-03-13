package hi.verkefni.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.Random;

public class Gull extends Rectangle {
    private static final String FXML_SKRA = "gull-view.fxml";
    private static final int OFFSET = 10; // Set your desired offset value

    public Gull() {         // kallar á smið yfirklasans (hér EiturSnakur)
        lesa(FXML_SKRA);
    }

    protected void lesa(String fxmlSkra) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlSkra));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
