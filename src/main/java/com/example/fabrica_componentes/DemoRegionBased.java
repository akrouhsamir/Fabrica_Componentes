package com.example.fabrica_componentes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;

import java.util.function.Consumer;


/**
 * User: hansolo
 * Date: 01.02.21
 * Time: 08:18
 */
public class DemoRegionBased extends Application {
    private RegionControl redButton;
    private RegionControl yellowButton;
    private RegionControl greenButton;
    private HBox          buttonBox;


    @Override public void init() {
        redButton    = new RegionControl(RegionControl.Type.CLOSE);
        yellowButton = new RegionControl(RegionControl.Type.MINIMIZE);
        greenButton  = new RegionControl(RegionControl.Type.ZOOM);
        buttonBox    = new HBox(8, redButton, yellowButton, greenButton);

        registerListeners();
    }

    private void registerListeners() {
        redButton.setOnMousePressed((Consumer<MouseEvent>) e -> System.out.println("Close pressed"));
        redButton.setOnMouseReleased((Consumer<MouseEvent>) e -> System.out.println("Close released"));

        yellowButton.setOnMousePressed((Consumer<MouseEvent>) e -> System.out.println("Minimized pressed"));
        yellowButton.setOnMouseReleased((Consumer<MouseEvent>) e -> System.out.println("Minimized released"));

        greenButton.setOnMousePressed((Consumer<MouseEvent>) e -> {
            System.out.println("Zoom pressed");
            greenButton.setState(!greenButton.getState());
        });
        greenButton.setOnMouseReleased((Consumer<MouseEvent>) e -> System.out.println("Zoom released"));

        buttonBox.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            redButton.setHovered(true);
            yellowButton.setHovered(true);
            greenButton.setHovered(true);
        });
        buttonBox.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            redButton.setHovered(false);
            yellowButton.setHovered(false);
            greenButton.setHovered(false);
        });
    }

    @Override public void start(final Stage stage) {
        StackPane pane = new StackPane(buttonBox);
        pane.setPadding(new Insets(8));

        Scene scene = new Scene(pane);

        stage.setTitle("Region based Control");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}