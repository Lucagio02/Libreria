package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.Libreria;

import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        URL fxmlLocation = getClass().getResource("/view/Libreria.fxml");
        Libreria.getInstance().caricaDaFile("libri.json",false);

        if (fxmlLocation == null) {
            System.err.println("❌ ERRORE: Impossibile trovare Libreria.fxml!");
            return;
        }


        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Libreria Personale");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
