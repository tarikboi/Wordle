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

    public static Stage rootStage;
    public static Scene rootScene;
    public static Parent rootParent;

    @Override
    public void start(Stage stage) throws IOException {
        rootStage = stage;
        rootParent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        rootScene = new Scene(rootParent);
        rootStage.setScene(rootScene);
        rootStage.setResizable(false);
        rootStage.show();


    }

    public static void main(String[] args) {
        launch();
    }


}