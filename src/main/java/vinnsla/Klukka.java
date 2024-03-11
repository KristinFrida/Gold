package vinnsla;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

public class Klukka {
    private SimpleIntegerProperty timi;

    // Smiður fyrir Klukka klasann
    public Klukka(int byrjaTima) {
        this.timi = new SimpleIntegerProperty(byrjaTima);
    }

    // Aðferð sem telur tímann niður
    public void tic() {
        if (timi.get() > 0) {
            timi.set(timi.get() - 1);
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
