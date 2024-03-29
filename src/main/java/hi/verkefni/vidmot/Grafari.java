package hi.verkefni.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;
import java.io.IOException;

public class Grafari extends Rectangle {

    private static final String FXML_SKRA = "grafari-view.fxml";
    private static final int OFFSET = 10;

    /**
     * Lesa inn FXML skránna grafari
     */
    public Grafari() {         // kallar á smið yfirklasans (hér EiturSnakur)
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

    public void afram() {
        Leikbord p = (Leikbord) this.getParent();
        setX((int) (getX() + p.getWidth() + Math.cos(Math.toRadians(getRotate())) * OFFSET) % (int) p.getWidth());
        setY((int) (getY() + p.getHeight() - Math.sin(Math.toRadians(getRotate())) * OFFSET) % (int) p.getHeight());
    }
}
