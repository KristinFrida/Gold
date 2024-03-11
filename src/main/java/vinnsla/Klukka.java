package vinnsla;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

public class Klukka {
    private SimpleIntegerProperty timi;
    private Timeline timeline;

    // Smiður fyrir Klukka klasann
    public Klukka(int byrjaTima) {
        this.timi = new SimpleIntegerProperty(byrjaTima);
    }

    // Aðferð sem telur tímann niður
    public void tic() {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (timi.get() > 0) {
                timi.set(timi.get() - 1);
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // Aðferð sem stöðvar klukkuna
    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    // Aðferð sem skilar tímanum
    public int getTimi() {
        return timi.get();
    }

    // Aðferð sem skilar SimpleIntegerProperty tilviksbreytunni
    public SimpleIntegerProperty timiProperty() {
        return timi;
    }

    // Aðferð sem stillir tímann á ákveðinn gildi
    public void setTimi(int nyrTimi) {
        timi.set(nyrTimi);
    }
}
