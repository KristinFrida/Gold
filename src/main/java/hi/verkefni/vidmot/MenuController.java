package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class MenuController {

    @FXML
    private ToggleGroup Erfidleikastig;

    @FXML
    private void onErfidleikastig() {
        RadioMenuItem selected = (RadioMenuItem) Erfidleikastig.getSelectedToggle();
        int selectedDifficulty = Integer.parseInt(selected.getId());
        System.out.println("Valið erfiðleikastig: " + selectedDifficulty);

        // Call GoldController to pass the selected difficulty level
        GoldController.getInstance().setDifficultyLevel(selectedDifficulty);
    }

    @FXML
    private void onByrjaSelected(ActionEvent event) {
        // Add code to start the game here
        System.out.println("Game started!");
    }

    @FXML
    private void onHaettaSelected(ActionEvent event) {
        // Add code to end the game here
        System.out.println("Game ended!");
        System.exit(0); // You may choose a different way to end the application
    }

}
