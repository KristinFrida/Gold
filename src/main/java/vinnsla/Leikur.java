package vinnsla;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Leikur {
    private IntegerProperty stigin = new SimpleIntegerProperty(0);

    // Bæta við getter fyrir stigin
    public IntegerProperty stiginProperty() {
        return stigin;
    }

    public int getStigin() {
        return stigin.get();
    }

    public void setStigin(int stig) {
        stigin.set(stig);
    }
}