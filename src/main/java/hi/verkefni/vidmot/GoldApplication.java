package hi.verkefni.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GoldApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GoldApplication.class.getResource("gold-rush-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        GoldController sc = fxmlLoader.getController();
        sc.getOrvatakkar(sc, scene);
        stage.setTitle("GOLD RUSH");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}

