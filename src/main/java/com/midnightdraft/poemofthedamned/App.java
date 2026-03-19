package com.midnightdraft.poemofthedamned;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fxml;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(String[] args) {
    Application.launch();
  }

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  @Override
  public void start(Stage primaryStage) throws IOException {
    StackPane root = FXMLLoader.load(resourceProvider.getUrl(Fxml.GAME_SCENE));
    Scene scene = new Scene(root, 1280, 720);

    primaryStage.setTitle("Hello!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
