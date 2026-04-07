package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class MainMenuController {

  @FXML
  private StackPane rootPane;
  @FXML
  private StackPane mainMenuEnter;
  @FXML
  private ImageView companyLogo;
  @FXML
  private ImageView loginIcon;
  @FXML
  private Button loginButton;

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  @FXML
  public void initialize() {
    loadResources();
    setupIcons();
  }

  private void loadResources() {
    String css = resourceProvider.getUrl(Css.MAIN_MENU).toExternalForm();
    rootPane.getStylesheets().add(css);
    Font.loadFont(resourceProvider.getUrl(Fonts.INTER_MEDIUM).toExternalForm(), 20);
  }

  private void setupIcons() {
    companyLogo.setImage(new Image(resourceProvider.getUrl(Ui.MIDNIGHT_LOGO).toExternalForm()));
    loginIcon.setImage(
        new Image(resourceProvider.getUrl(Ui.MAIN_MENU_LOGIN_ICON).toExternalForm()));
  }
}
