package com.midnightdraft.poemofthedamned;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(String[] args) {
    Application.launch();
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    StackPane root = new StackPane();
    Scene scene = new Scene(root, 800, 600);

    primaryStage.setTitle("Hello!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
