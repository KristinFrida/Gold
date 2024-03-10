package hi.verkefni.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.util.Random;

public class Gull extends Rectangle {
    private static final Random random =new Random();
    public Gull(Leikbord b) {
        lesa();
        setX(random.nextInt((int) (b.getWidth() - getWidth())));
        setY(random.nextInt((int)( b.getHeight() - getHeight())));
        b.getChildren().add(this);
    }

    public void lesa() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gull-view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }
}

