package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
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


}
