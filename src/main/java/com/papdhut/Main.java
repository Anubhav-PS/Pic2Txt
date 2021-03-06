package com.papdhut;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL url = new File("PrimeWin.fxml").toURI().toURL();   // insert the absoulte path of PrimeWin.fxml inside the double quotes 
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Pic2Txt");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
