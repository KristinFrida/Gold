package hi.verkefni.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Random;

public class Grafari extends Rectangle {
    private static final Random random = new Random();
    @FXML
    private int OFFSET;

    public Grafari() {
        super(100, 50); // Breidd og hæð eftir þörfum
        setStyle("-fx-fill: blue;"); // Stílar fyrir gerð
    }

    public void afram() {
        Leikbord p = (Leikbord) this.getParent();
        setX((int)(getX() + p.getWidth() + (int) Math.cos(Math.toRadians(getRotate())) * OFFSET) % (int)p.getWidth());
        setY((int)(getY() + p.getHeight() - (int) Math.sin(Math.toRadians(getRotate())) * OFFSET) % (int)p.getHeight());
    }



    /**
     * Athugar hvort Grafari etur (skarast við) gull.
     *
     * @param g gull
     * @return satt ef grafari étur gull annars false
     */
    public boolean erEtur(Gull g) {
        return getBoundsInParent().intersects(g.getBoundsInParent());
    }
}
