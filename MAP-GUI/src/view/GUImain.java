package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GUImain extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //System.out.println(getClass().getResource("C:\\Users\\zsoka\\IdeaProjects\\GUI-ToyLanguage\\src\\view\\View.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
        primaryStage.setTitle("Toy Language");
        primaryStage.setScene(new Scene(root,845,550));
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
