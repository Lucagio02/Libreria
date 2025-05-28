package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        Button button = new Button("Ciao JavaFX!");
        Scene scene = new Scene(new StackPane(button), 300, 200);
        stage.setScene(scene);
        stage.setTitle("Test JavaFX");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
