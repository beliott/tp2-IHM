package fr.umontpellier.iut.exercice2;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Palette extends Application {

    private Label label;

    private CustomButton vert;
    private CustomButton rouge;
    private CustomButton bleu;

    private CustomButton sourceOfEvent;

    private BorderPane root;
    private Pane panneau;
    private HBox bas;

    private EventHandler<ActionEvent> eventHandler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        label = new Label();
        label.setFont(Font.font("Tahoma",FontWeight.NORMAL, 20));
        BorderPane.setAlignment(label, Pos.CENTER);

        panneau = new Pane();
        panneau.setPrefSize(400,200);

        bas = new HBox(10);
        bas.setAlignment(Pos.CENTER);
        bas.setPadding(new Insets(10,5,10,5));

        vert = new CustomButton("Vert", "#31BCA4");
        rouge = new CustomButton("Rouge", "#F21411");
        bleu = new CustomButton("Bleu", "#3273A4");

        eventHandler = (event) -> {
            sourceOfEvent = (CustomButton) event.getSource();
        };

        vert.setOnAction(eventHandler);
        rouge.setOnAction(eventHandler);
        bleu.setOnAction(eventHandler);

        bas.getChildren().addAll(vert, rouge, bleu);

        root.setCenter(panneau);
        root.setTop(label);
        root.setBottom(bas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

