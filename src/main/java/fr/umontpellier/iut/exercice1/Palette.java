package fr.umontpellier.iut.exercice1;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.text.NumberFormat;

@SuppressWarnings("Duplicates")
public class Palette extends Application {

    private int nbVert = 0;
    private int nbRouge = 0;
    private int nbBleu = 0;

    private IntegerProperty nbfois = new SimpleIntegerProperty(0);
    private StringProperty message;

    private Label texteDuHaut;

    private Button vert;
    private Button rouge;
    private Button bleu;

    private BorderPane root;
    private Pane panneau;
    private HBox boutons;

    private Label texteDuBas;

    private StringProperty couleurPanneau;

    public void createBindings(){
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty();
        pasEncoreDeClic.bind(Bindings.when(Bindings.equal(nbfois, 0)).then(true).otherwise(false));
        if (pasEncoreDeClic.getValue()){
            texteDuHaut.textProperty().bind(Bindings.concat(message, " choisi ", nbfois, " fois"));
            panneau.styleProperty().bind(Bindings.concat("-fx-background-color: ", couleurPanneau));
            texteDuBas.textProperty().bind(Bindings.concat("Le ", message, " est une jolie couleur !"));
            texteDuBas.styleProperty().bind(Bindings.concat("-fx-text-fill: ", couleurPanneau));
        }
    }


    @Override
    public void start(Stage primaryStage) {
        couleurPanneau = new SimpleStringProperty("#000000");
        root = new BorderPane();

        texteDuHaut = new Label();
        texteDuHaut.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        BorderPane.setAlignment(texteDuHaut, Pos.CENTER);
        texteDuHaut.setText("Cliquez sur un bouton");

        panneau = new Pane();
        panneau.setPrefSize(400, 200);

        VBox bas = new VBox();
        boutons = new HBox(10);
        boutons.setAlignment(Pos.CENTER);
        boutons.setPadding(new Insets(10, 5, 10, 5));
        texteDuBas = new Label();
        bas.setAlignment(Pos.CENTER_RIGHT);
        bas.getChildren().addAll(boutons, texteDuBas);
        message = new SimpleStringProperty();

        vert = new Button("Vert");
        vert.setOnAction(actionEvent -> {
            createBindings();
            message.setValue(vert.getText());
            //panneau.setStyle("-fx-background-color: green");
            couleurPanneau.setValue("green");
            nbVert++;
            nbfois.setValue(nbVert);

        });

        rouge = new Button("Rouge");
        rouge.setOnAction(actionEvent -> {
            createBindings();
            message.setValue(rouge.getText());
            //panneau.setStyle("-fx-background-color: red");
            couleurPanneau.setValue("red");
            nbRouge++;
            nbfois.setValue(nbRouge);
        });

        bleu = new Button("Bleu");
        bleu.setOnAction( actionEvent -> {
            createBindings();
            message.setValue(bleu.getText());
            //panneau.setStyle("-fx-background-color: blue");
            couleurPanneau.setValue("blue");
            nbBleu++;
            nbfois.setValue(nbBleu);
        });


        /* VOTRE CODE ICI */


        boutons.getChildren().addAll(vert, rouge, bleu);

        root.setCenter(panneau);
        root.setTop(texteDuHaut);
        root.setBottom(bas);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

