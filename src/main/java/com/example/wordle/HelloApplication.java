package com.example.wordle;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;




public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        HelloController controller = loader.getController();
        Scene scene = new Scene(root);
        /*
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode());
            }
        });

         */
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        /*
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        rootStage.setScene(new Scene(root));
        rootStage.setResizable(false);
        rootStage.show();

         */
    }

    public static void main(String[] args) {
        launch();
    }


}