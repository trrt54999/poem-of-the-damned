package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.App;
import com.midnightdraft.poemofthedamned.application.dto.UserAuthDTO;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fxml;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import com.midnightdraft.poemofthedamned.presentation.util.BindLocalTime;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MainMenuController {

  @FXML
  private StackPane rootPane;
  @FXML
  private StackPane mainMenuEnter;
  @FXML
  private BorderPane mainMenu;
  @FXML
  private ImageView companyLogo;
  @FXML
  private ImageView loginIcon;
  @FXML
  private ImageView loadingAnimation;
  @FXML
  private ImageView miniLogo;
  @FXML
  private ImageView centerLogo;
  @FXML
  private Button enterLoginButton;
  @FXML
  private Button poemButton;
  @FXML
  private Button picturesButton;
  @FXML
  private Button musicButton;
  @FXML
  private Button settingsButton;
  @FXML
  private Button quitButton;
  @FXML
  private Button profileButton;
  @FXML
  private VBox loadingBox;
  @FXML
  private Label usernameLabel;
  @FXML
  private Label loggingInLabel;
  @FXML
  private Label timeLabel;
  @FXML
  private ResourceBundle resources;

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  private Timeline clockTimeLine;

  @FXML
  public void initialize() {
    loadResources();
    setupIcons();
    setupUsernameLabel();
    setupKeyHandlers();
    setupScaling();

    rootPane.sceneProperty().addListener((_, _, newScene) -> {
      if (newScene != null) {
        clockTimeLine = BindLocalTime.setupCurrentTime(timeLabel, Locale.of(App.currentLang));
      }
    });

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

    PauseTransition delay = new PauseTransition(Duration.seconds(1.0));
    delay.play();
    delay.setOnFinished(_ -> playCrossfadeAnimation());
  }

  @FXML
  private void onLoadGameMenuButtonPressed() {
    FXMLLoader loader = new FXMLLoader(resourceProvider.getUrl(Fxml.GAME_MAIN_MENU));
    loader.setResources(resources);

    try {
      if (clockTimeLine != null) {
        clockTimeLine.stop();
      }

      Parent nextView = loader.load();
      rootPane.getScene().setRoot(nextView);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @FXML
  private void onQuitButtonPressed() {
    System.exit(0);
  }

  private void playCrossfadeAnimation() {
    FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.0), mainMenuEnter);
    fadeOut.setFromValue(1.0);
    fadeOut.setToValue(0.0);

    fadeOut.setOnFinished(_ -> {
      mainMenuEnter.setVisible(false);
      mainMenuEnter.setManaged(false);

      mainMenu.setOpacity(0.0);
      mainMenu.setVisible(true);
      mainMenu.setManaged(true);

      FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.0), mainMenu);
      fadeIn.setFromValue(0.0);
      fadeIn.setToValue(1.0);
      fadeIn.play();
    });
    fadeOut.play();
  }

  private void loadResources() {
    String css = resourceProvider.getUrl(Css.MAIN_MENU).toExternalForm();
    rootPane.getStylesheets().add(css);
    Font.loadFont(resourceProvider.getUrl(Fonts.INTER_MEDIUM).toExternalForm(), 20);
    Font.loadFont(resourceProvider.getUrl(Fonts.INTER_EXTRA_BOLD).toExternalForm(), 20);
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
    centerLogo.setImage(
        new Image(resourceProvider.getUrl(Ui.CENTRAL_MIDNIGHT_LOGO).toExternalForm()));
    miniLogo.setImage(new Image(resourceProvider.getUrl(Ui.PINK_MIDNIGHT_LOGO).toExternalForm()));

    companyLogo.fitWidthProperty().bind(rootPane.widthProperty().multiply(0.35));
    companyLogo.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.55));
    centerLogo.fitWidthProperty().bind(rootPane.widthProperty().multiply(0.5));
    centerLogo.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.8));

    miniLogo.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.0667));
    miniLogo.fitWidthProperty().bind(rootPane.widthProperty().multiply(0.0375));

    setButtonIcon(poemButton, Ui.POEM_ICON);
    setupButtonHover(poemButton, Ui.POEM_ICON, Ui.POEM_ICON_WHITE);

    setButtonIcon(picturesButton, Ui.PICTURES_ICON);
    setupButtonHover(picturesButton, Ui.PICTURES_ICON, Ui.PICTURES_ICON_WHITE);

    setButtonIcon(musicButton, Ui.MUSIC_ICON);
    setupButtonHover(musicButton, Ui.MUSIC_ICON, Ui.MUSIC_ICON_WHITE);

    setButtonIcon(settingsButton, Ui.SETTINGS_ICON);
    setupButtonHover(settingsButton, Ui.SETTINGS_ICON, Ui.SETTINGS_ICON_WHITE);

    setButtonIcon(quitButton, Ui.QUIT_ICON);
    setupButtonHover(quitButton, Ui.QUIT_ICON, Ui.QUIT_ICON_WHITE);

    setButtonIcon(profileButton, Ui.PROFILE_ICON);
    setupButtonHover(profileButton, Ui.PROFILE_ICON, Ui.PROFILE_ICON_WHITE);
  }

  private void setupScaling() {
    rootPane.styleProperty().bind(Bindings.createStringBinding(() -> {
      double calculatedSize = rootPane.getHeight() * 0.016;
      double clampedSize = Math.clamp(calculatedSize, 12, 60);
      return String.format("-fx-font-size: %.1fpx;", clampedSize);
    }, rootPane.heightProperty()));
  }

  private void setButtonIcon(Button button, Ui iconResource) {
    ImageView icon = new ImageView(
        new Image(resourceProvider.getUrl(iconResource).toExternalForm()));

    icon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.066));
    icon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.066));

    button.setGraphic(icon);
  }

  private void setupButtonHover(Button button, Ui defaultIcon, Ui hoverIcon) {
    button.hoverProperty()
        .addListener((_, _, hovered) -> setButtonIcon(button, hovered ? hoverIcon : defaultIcon));
  }

  private void setupUsernameLabel() {
    UserAuthDTO session = GameStateMachine.getInstance().getSessionContext();
    if (session != null && session.username() != null) {
      usernameLabel.setText(
          resources.getString("main_menu.welcome.label") + ", " + session.username() + "!");
    } else {
      usernameLabel.setText(resources.getString("main_menu.default.name"));
    }
  }
}
