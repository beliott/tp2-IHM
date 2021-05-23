package fr.umontpellier.iut.exercice5;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginMain extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        GridPane root = new LoginControl();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FXML Custom Control");
        stage.show();
    }
}
