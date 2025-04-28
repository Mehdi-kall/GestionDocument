package edu.gestiondocuments.tests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A simple JavaFX application that doesn't use FXML.
 * This can be used to test if JavaFX is working correctly.
 */
public class SimpleJavaFXApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Create a simple UI
            Label label = new Label("Hello, JavaFX!");
            Button button = new Button("Click Me");
            button.setOnAction(e -> label.setText("Button clicked!"));
            
            VBox root = new VBox(10);
            root.getChildren().addAll(label, button);
            
            // Create the scene
            Scene scene = new Scene(root, 300, 200);
            
            // Configure the stage
            primaryStage.setTitle("Simple JavaFX App");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error in JavaFX application: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
