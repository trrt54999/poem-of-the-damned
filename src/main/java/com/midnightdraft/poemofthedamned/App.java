package com.midnightdraft.poemofthedamned;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fxml;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends Application {

  public static void main(String[] args) {
    Application.launch();
  }

  public static String currentLang = "en";

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  @Override
  public void stop() {
    log.info("Shutdown game app");
    HibernateSessionFactory.shutdown();
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    log.info("Starting game app");
    try {
      FXMLLoader loader = new FXMLLoader(resourceProvider.getUrl(Fxml.AUTH_MENU));
      loader.setResources(ResourceBundle.getBundle("localization/ui", Locale.of(currentLang)));
      StackPane root = loader.load();

      Scene scene = new Scene(root, 1280, 720);

      switch (currentLang) {
        case "uk" ->
            scene.getStylesheets().add(resourceProvider.getUrl(Css.FONT_UK).toExternalForm());
        default ->
            scene.getStylesheets().add(resourceProvider.getUrl(Css.FONT_EN).toExternalForm());
      }

      primaryStage.setTitle("Hello!");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      log.error("Failed to load main scene", e);
      throw e;
    }
  }
}
