package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.application.dto.UserAuthDTO;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
  private ImageView loadingAnimation;
  @FXML
  private Button enterLoginButton;
  @FXML
  private VBox loadingBox;
  @FXML
  private Label usernameLabel;
  @FXML
  private Label loggingInLabel;
  @FXML
  private ResourceBundle resources;

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  @FXML
  public void initialize() {
    loadResources();
    setupIcons();
    setupUsernameLabel();
    setupKeyHandlers();
    rootPane.sceneProperty().addListener((_, _, newScene) -> {
      if (newScene != null) {
        rootPane.requestFocus();
      }
    });
  }

  @FXML
  private void onEnterLoginPressed() {
    enterLoginButton.setVisible(false);
    enterLoginButton.setManaged(false);
    loadingBox.setVisible(true);
  }

  private void loadResources() {
    String css = resourceProvider.getUrl(Css.MAIN_MENU).toExternalForm();
    rootPane.getStylesheets().add(css);
    Font.loadFont(resourceProvider.getUrl(Fonts.INTER_MEDIUM).toExternalForm(), 20);
  }

  private void setupKeyHandlers() {
    rootPane.setOnKeyPressed(event -> {
      switch (event.getCode()) {
        case ENTER, SPACE -> {
          if (enterLoginButton.isVisible()) {
            onEnterLoginPressed();
          }
        }
      }
    });
  }

  private void setupIcons() {
    companyLogo.setImage(new Image(resourceProvider.getUrl(Ui.MIDNIGHT_LOGO).toExternalForm()));
    loginIcon.setImage(
        new Image(resourceProvider.getUrl(Ui.MAIN_MENU_LOGIN_ICON).toExternalForm()));
    loadingAnimation.setImage(
        new Image(resourceProvider.getUrl(Ui.LOADING_CIRCLE_ANIMATION).toExternalForm()));

    companyLogo.fitWidthProperty().bind(rootPane.widthProperty().multiply(0.35));
    companyLogo.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.55));
  }

  private void setupUsernameLabel() {
//     ці 2 строки тестові
//     --------------------------------------------------------------------------------
    UserAuthDTO testUser = new UserAuthDTO("Mike", "asqld@gmail.com");
    GameStateMachine.getInstance().setSessionContext(testUser);
//     --------------------------------------------------------------------------------

    UserAuthDTO session = GameStateMachine.getInstance().getSessionContext();
    if (session != null && session.username() != null) {
      usernameLabel.setText(
          resources.getString("main_menu.welcome.label") + ", " + session.username() + "!");
    } else {
      usernameLabel.setText("Guest");
    }
  }
}
