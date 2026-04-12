package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.App;
import com.midnightdraft.poemofthedamned.application.dto.UserAuthDTO;
import com.midnightdraft.poemofthedamned.application.usecase.ChangeLanguageUseCase;
import com.midnightdraft.poemofthedamned.application.usecase.ChangeResolutionUseCase;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.model.GameLanguage;
import com.midnightdraft.poemofthedamned.domain.model.ScreenResolution;
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
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenuController {

  @FXML
  private StackPane rootPane;
  @FXML
  private StackPane mainMenuEnter;
  @FXML
  private BorderPane mainMenu;
  @FXML
  private BorderPane modalWindow;
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
  private Button modalTopCloseButton;
  @FXML
  private Button prevResolutionButton;
  @FXML
  private Button nextResolutionButton;
  @FXML
  private Button prevLanguageButton;
  @FXML
  private Button nextLanguageButton;
  @FXML
  private Button applyButton;
  @FXML
  private VBox loadingBox;
  @FXML
  private Label usernameLabel;
  @FXML
  private Label loggingInLabel;
  @FXML
  private Label timeLabel;
  @FXML
  private Label currentResolutionLabel;
  @FXML
  private Label modalTopLabel;
  @FXML
  private Label currentLanguageLabel;
  @FXML
  private Label gameOptionsLabel;
  @FXML
  private Label musicVolumeLabel;
  @FXML
  private Label soundVolumeLabel;
  @FXML
  private Label displayLabel;
  @FXML
  private Label windowedLabel;
  @FXML
  private Label fullscreenLabel;
  @FXML
  private Label languageLabel;
  @FXML
  private Slider musicVolumeSlider;
  @FXML
  private Slider soundVolumeSlider;
  @FXML
  private ToggleButton resolutionToggle;
  @FXML
  private HBox mainMenuFooter;
  @FXML
  private Pane topStripe;
  @FXML
  private Region backgroundEclipse;
  @FXML
  private ResourceBundle resources;

  private final ChangeResolutionUseCase changeResolutionUseCase = new ChangeResolutionUseCase();
  private final ChangeLanguageUseCase changeLanguageUseCase = new ChangeLanguageUseCase();
  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  private Timeline clockTimeLine;
  private int currentResolutionIndex = 0;
  private int currentLanguageIndex = 0;
  private int appliedResolutionIndex = 0;
  private int appliedLanguageIndex = 0;

  @FXML
  public void initialize() {
    loadResources();
    setupIcons();
    setupUsernameLabel();
    setupKeyHandlers();
    setupScaling();
    setupModalWindow();
    setupResolutionCarousel();
    setupLanguageCarousel();
    topStripe.prefHeightProperty().bind(rootPane.heightProperty().multiply(0.005));

    rootPane.sceneProperty().addListener((_, _, newScene) -> {
      if (newScene == null) {
        return;
      }

      clockTimeLine = BindLocalTime.setupCurrentTime(timeLabel, Locale.of(App.currentLang));
      rootPane.requestFocus();

      if (newScene.getWindow() instanceof Stage stage) {
        setupResolutionToggle(stage);
        setupApplyButton(stage);
      } else {
        newScene.windowProperty().addListener((_, _, window) -> {
          if (!(window instanceof Stage stage)) {
            return;
          }
          setupResolutionToggle(stage);
          setupApplyButton(stage);
        });
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
  private void onSettingsButtonPressed() {
    currentResolutionIndex = appliedResolutionIndex;
    currentLanguageIndex = appliedLanguageIndex;
    updateResolutionLabel();
    updateLanguageLabel();

    settingsButton.getStyleClass().add("main-menu-btn-active");
    setButtonIcon(settingsButton, Ui.SETTINGS_ICON_WHITE);
    openModalWindow();
  }

  @FXML
  private void onEclipsePressed() {
    closeModalWindow();
  }

  @FXML
  private void onQuitButtonPressed() {
    System.exit(0);
    // logging slf4j
  }

  @FXML
  private void onSettingsCloseButtonClicked() {
    closeModalWindow();
  }


  private void setupApplyButton(Stage stage) {
    applyButton.setOnAction(_ -> {
      if (currentResolutionIndex != appliedResolutionIndex && !stage.isFullScreen()) {
        changeResolutionUseCase.execute(ScreenResolution.values()[currentResolutionIndex], stage);
        appliedResolutionIndex = currentResolutionIndex;
      }
      if (currentLanguageIndex != appliedLanguageIndex) {
        changeLanguageUseCase.execute(GameLanguage.values()[currentLanguageIndex].getCode());
        refreshFontStylesheet();
        refreshLabels();
        appliedLanguageIndex = currentLanguageIndex;
      }
    });
  }

  private void refreshFontStylesheet() {
    Scene scene = rootPane.getScene();
    scene.getStylesheets().removeIf(s -> s.contains("font_en") || s.contains("font_uk"));

    switch (App.currentLang) {
      case "uk" ->
          scene.getStylesheets().add(resourceProvider.getUrl(Css.FONT_UK).toExternalForm());
      default -> scene.getStylesheets().add(resourceProvider.getUrl(Css.FONT_EN).toExternalForm());
    }
  }

  private void refreshLabels() {
    ResourceBundle bundle = ResourceBundle.getBundle("localization/ui", Locale.of(App.currentLang));
    resources = bundle;

    enterLoginButton.setText(bundle.getString("main_menu.login"));
    loggingInLabel.setText(bundle.getString("main_menu.login.in"));
    picturesButton.setText(bundle.getString("main_menu.pictures.button"));
    musicButton.setText(bundle.getString("main_menu.music.button"));
    settingsButton.setText(bundle.getString("main_menu.settings.button"));
    quitButton.setText(bundle.getString("main_menu.quit.button"));
    modalTopLabel.setText(bundle.getString("main_menu.settings.top.label"));
    applyButton.setText(bundle.getString("main_menu.settings.apply.button"));
    gameOptionsLabel.setText(bundle.getString("main_menu.settings.game_options.label"));
    musicVolumeLabel.setText(bundle.getString("main_menu.settings.music_volume.label"));
    soundVolumeLabel.setText(bundle.getString("main_menu.settings.sound_volume.label"));
    displayLabel.setText(bundle.getString("main_menu.settings.display.label"));
    windowedLabel.setText(bundle.getString("main_menu.settings.toggle.window.label"));
    fullscreenLabel.setText(bundle.getString("main_menu.settings.toggle.fullscreen.label"));
    languageLabel.setText(bundle.getString("main_menu.settings.language.label"));

    if (clockTimeLine != null) {
      clockTimeLine.stop();
    }
    clockTimeLine = BindLocalTime.setupCurrentTime(timeLabel, Locale.of(App.currentLang));

    setupUsernameLabel();
  }

  private void updateResolutionLabel() {
    currentResolutionLabel.setText(ScreenResolution.values()[currentResolutionIndex].toString());
  }

  private void updateLanguageLabel() {
    currentLanguageLabel.setText(GameLanguage.values()[currentLanguageIndex].toString());
  }

  private void setupResolutionCarousel() {
    updateResolutionLabel();

    nextResolutionButton.setOnMouseClicked(_ -> {
      currentResolutionIndex = (currentResolutionIndex + 1) % ScreenResolution.values().length;
      updateResolutionLabel();
    });

    prevResolutionButton.setOnMouseClicked(_ -> {
      currentResolutionIndex--;
      if (currentResolutionIndex < 0) {
        currentResolutionIndex = ScreenResolution.values().length - 1;
      }
      updateResolutionLabel();
    });
  }

  private void setupLanguageCarousel() {
    updateLanguageLabel();

    nextLanguageButton.setOnMouseClicked(_ -> {
      currentLanguageIndex = (currentLanguageIndex + 1) % GameLanguage.values().length;
      updateLanguageLabel();
    });

    prevLanguageButton.setOnMouseClicked(_ -> {
      currentLanguageIndex--;
      if (currentLanguageIndex < 0) {
        currentLanguageIndex = GameLanguage.values().length - 1;
      }
      updateLanguageLabel();
    });
  }

  private void setupResolutionToggle(Stage stage) {
    resolutionToggle.setSelected(stage.isFullScreen());
    updateResolutionCarouselState(stage.isFullScreen());

    resolutionToggle.selectedProperty().addListener((_, _, isFullScreen) -> {
      stage.setFullScreenExitHint("");
      stage.setFullScreen(isFullScreen);
      updateResolutionCarouselState(isFullScreen);
    });
  }

  private void updateResolutionCarouselState(boolean isFullScreen) {
    prevResolutionButton.setDisable(isFullScreen);
    nextResolutionButton.setDisable(isFullScreen);
    currentResolutionLabel.setOpacity(isFullScreen ? 0.4 : 1.0);
  }

  private void setupModalWindow() {
    modalWindow.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.6));
    modalWindow.maxWidthProperty().bind(rootPane.widthProperty().multiply(0.6));
    modalWindow.prefHeightProperty()
        .bind(rootPane.heightProperty().subtract(mainMenuFooter.heightProperty()));
    modalWindow.maxHeightProperty()
        .bind(rootPane.heightProperty().subtract(mainMenuFooter.heightProperty()));
  }

  private void openModalWindow() {
    backgroundEclipse.setVisible(true);
    modalWindow.setScaleX(0.92);
    modalWindow.setScaleY(0.92);
    modalWindow.setOpacity(0.0);
    modalWindow.setVisible(true);
    modalWindow.setManaged(true);

    ScaleTransition scaleIn = new ScaleTransition(Duration.millis(150), modalWindow);
    scaleIn.setToX(1.0);
    scaleIn.setToY(1.0);

    FadeTransition fadeIn = new FadeTransition(Duration.millis(150), modalWindow);
    fadeIn.setToValue(1.0);

    new ParallelTransition(scaleIn, fadeIn).play();
  }

  private void closeModalWindow() {
    backgroundEclipse.setVisible(false);
    ScaleTransition scaleOut = new ScaleTransition(Duration.millis(120), modalWindow);
    scaleOut.setToX(0.92);
    scaleOut.setToY(0.92);

    FadeTransition fadeOut = new FadeTransition(Duration.millis(120), modalWindow);
    fadeOut.setToValue(0.0);

    ParallelTransition close = new ParallelTransition(scaleOut, fadeOut);
    close.setOnFinished(_ -> {

      modalWindow.setVisible(false);
      modalWindow.setManaged(false);
      modalWindow.setScaleX(1.0);
      modalWindow.setScaleY(1.0);
      modalWindow.setOpacity(1.0);

      settingsButton.getStyleClass().remove("main-menu-btn-active");
      setButtonIcon(settingsButton, Ui.SETTINGS_ICON);
    });
    close.play();
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

    setSmallButtonIcon(modalTopCloseButton, Ui.MODAL_DAGGER_ICON);

    setupCarouselButton(prevResolutionButton, 270);
    setupGraphicImageHover(prevResolutionButton, Ui.ROULETTE_BUTTON_ICON,
        Ui.ROULETTE_BUTTON_HOVER_ICON);

    setupCarouselButton(nextResolutionButton, 90);
    setupGraphicImageHover(nextResolutionButton, Ui.ROULETTE_BUTTON_ICON,
        Ui.ROULETTE_BUTTON_HOVER_ICON);

    setupCarouselButton(prevLanguageButton, 270);
    setupGraphicImageHover(prevLanguageButton, Ui.ROULETTE_BUTTON_ICON,
        Ui.ROULETTE_BUTTON_HOVER_ICON);

    setupCarouselButton(nextLanguageButton, 90);
    setupGraphicImageHover(nextLanguageButton, Ui.ROULETTE_BUTTON_ICON,
        Ui.ROULETTE_BUTTON_HOVER_ICON);
  }

  private void setupCarouselButton(Button button, double rotation) {
    setTinyButtonIcon(button, Ui.ROULETTE_BUTTON_ICON);
    button.getGraphic().setRotate(rotation);
    setupGraphicImageHover(button, Ui.ROULETTE_BUTTON_ICON, Ui.ROULETTE_BUTTON_HOVER_ICON);
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

  private void setSmallButtonIcon(Button button, Ui iconResource) {
    ImageView icon = new ImageView(
        new Image(resourceProvider.getUrl(iconResource).toExternalForm()));

    icon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.033));
    icon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.033));

    button.setGraphic(icon);
  }

  private void setTinyButtonIcon(Button button, Ui iconResource) {
    ImageView icon = new ImageView(
        new Image(resourceProvider.getUrl(iconResource).toExternalForm()));

    icon.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.0167));
    icon.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.0167));

    button.setGraphic(icon);
  }

  private void setupButtonHover(Button button, Ui defaultIcon, Ui hoverIcon) {
    button.hoverProperty().addListener((_, _, hovered) -> {
      boolean isActive = button.getStyleClass().contains("main-menu-btn-active");
      if (isActive) {
        return;
      }

      setButtonIcon(button, hovered ? hoverIcon : defaultIcon);

      if (!(button.getGraphic() instanceof ImageView icon)) {
        return;
      }

      TranslateTransition nudgeIcon = new TranslateTransition(Duration.millis(120), icon);
      nudgeIcon.setInterpolator(Interpolator.EASE_OUT);
      nudgeIcon.setToX(hovered ? 4.0 : 0.0);
      nudgeIcon.play();

      Node buttonText = button.lookup(".text");
      if (buttonText == null) {
        return;
      }

      TranslateTransition nudgeText = new TranslateTransition(Duration.millis(120), buttonText);
      nudgeText.setInterpolator(Interpolator.EASE_OUT);
      nudgeText.setToX(hovered ? 4.0 : 0.0);
      nudgeText.play();
    });
  }

  private void setupGraphicImageHover(Button button, Ui defaultIcon, Ui hoverIcon) {
    Image defaultImg = new Image(resourceProvider.getUrl(defaultIcon).toExternalForm());
    Image hoverImg = new Image(resourceProvider.getUrl(hoverIcon).toExternalForm());

    button.hoverProperty().addListener((_, _, hovered) -> {
      if (button.getGraphic() instanceof ImageView imageView) {
        imageView.setImage(hovered ? hoverImg : defaultImg);
      }
    });
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
