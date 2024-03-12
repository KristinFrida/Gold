package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioMenuItem;

public class MenuController {
    private static GoldController goldController = new GoldController();

    public static GoldController getGoldController() {
        if (goldController == null) {
            goldController = new GoldController();
        }
        return goldController;
    }

    public void onErfidleikastig(ActionEvent event) {
        RadioMenuItem selectedMenuItem = (RadioMenuItem) event.getSource();
        int difficultyLevel = Integer.parseInt(selectedMenuItem.getId());

        goldController.setDifficultyLevel(difficultyLevel);
    }


    public void setController(GoldController goldController) {
        this.goldController = goldController;
    }

    public void onNyrLeikur(ActionEvent actionEvent){
        goldController.hefjaLeik();
        goldController.raesaKlukku();
    }

    public void onHaettaLeik(ActionEvent actionEvent) {
        goldController.onHaetta();
    }

    public void onUmForritid(ActionEvent actionEvent) {
        showAboutDialog();
    }
    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Um Forritið");
        alert.setHeaderText(null);
        alert.setContentText("Forritið er samansett af grafara sem á að grafa eftir gulli, fyrir neðan leikborðið " +
                "birtast stigin og hve mikill tími er eftir. Einnig getur þú stilt erfiðleikastigið hér að ofan og þá breytist " +
                "hversu mörg gull eru í boði en einnig hversu mikill tími þú færð");
        alert.getDialogPane().setPrefSize(400, 150);
        alert.showAndWait();
    }



}
