package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioSfx;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Backgrounds;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.GameCharacters;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import com.midnightdraft.poemofthedamned.presentation.util.SoundHelper;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class GameSceneController {

  @FXML
  private StackPane rootPane;
  @FXML
  private StackPane nameplatePanel;
  @FXML
  private ImageView backgroundImage;
  @FXML
  private ImageView leftSpritePosition;
  @FXML
  private ImageView rightSpritePosition;
  @FXML
  private ImageView centralSpritePosition;
  @FXML
  private ImageView nextIndicator;
  @FXML
  private AnchorPane spritePosition;
  @FXML
  private AnchorPane dialoguePanel;
  @FXML
  private Label characterNameLabel;
  @FXML
  private Label dialogueTextLabel;
  @FXML
  private HBox dialogueButtons;
  @FXML
  private Button historyButton;
  @FXML
  private Button skipButton;
  @FXML
  private Button autoButton;
  @FXML
  private Button saveButton;
  @FXML
  private Button loadButton;
  @FXML
  private Button settingsButton;
  @FXML
  private VBox choiceContainer;

  private AudioClip hoverSound;
  private AudioClip selectSound;

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  private static final double BASE_WIDTH = 1280.0;
  private static final double BASE_HEIGHT = 720.0;

  @FXML
  public void initialize() {
    loadResources();
  }

  @FXML
  public void playHoverSound() {
    if (hoverSound != null) hoverSound.play();
  }

  @FXML
  public void playSelectSound() {
    if (selectSound != null) selectSound.play();
  }

  private void loadResources() {
    String css = resourceProvider.getUrl(Css.GAME_SCENE).toExternalForm();
    rootPane.getStylesheets().add(css);

    leftSpritePosition.setImage(new Image(resourceProvider.getUrl(GameCharacters.HARUKA_LAUGH).toExternalForm()));
    rightSpritePosition.setImage(new Image(resourceProvider.getUrl(GameCharacters.MIO_CAT_SMILE).toExternalForm()));
    centralSpritePosition.setImage(new Image(resourceProvider.getUrl(GameCharacters.AYA_HAPPY).toExternalForm()));

    spritePosition.translateYProperty().bind(
        rootPane.heightProperty().multiply(12.0 / BASE_HEIGHT)
    );

    dialogueTextLabel.styleProperty().bind(
        Bindings.concat(
            "-fx-effect: dropshadow(two-pass-box, #473434, ",
            rootPane.heightProperty().multiply(3.0 / BASE_HEIGHT),
            ", 1.0, 0, 0);"
        )
    );

    dialogueTextLabel.setText("Lorem ipsum dolor sit amet consectetur adipiscing elit."
        + " Quisque faucibus ex sapien ewq vitae pellentesque sem placerat."
        + " In id cursus mi pretium tellus duis convallis."
        + " Tempus leo eu aenean sed diam urna tempor. ");

    characterNameLabel.setText("Haruka");

    backgroundImage.setImage(new Image(resourceProvider.getUrl(Backgrounds.CLASS_DAY).toExternalForm()));
    backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
    backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());

    nextIndicator.setImage(new Image(resourceProvider.getUrl(Ui.DIALOGUE_RECTANGLE).toExternalForm()));

    leftSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.85));
    rightSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.85));
    centralSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.85));

    dialoguePanel.maxWidthProperty().bind(rootPane.widthProperty().multiply(0.80));
    dialoguePanel.maxHeightProperty().bind(rootPane.heightProperty().multiply(0.28));

    dialoguePanel.translateYProperty().bind(
        rootPane.heightProperty().multiply(-40.0 / BASE_HEIGHT)
    );

    nextIndicator.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.03));
    // fonts and etc

    leftSpritePosition.translateXProperty().bind(
        rootPane.widthProperty().multiply(0.10)
    );

    rightSpritePosition.translateXProperty().bind(
        rootPane.widthProperty().multiply(-0.14)
    );

    Font.loadFont(resourceProvider.getUrl(Fonts.RIFFIC_FREE_BOLD).toExternalForm(), 36);
    Font.loadFont(resourceProvider.getUrl(Fonts.ALLER_BOLD).toExternalForm(), 42);
    Font.loadFont(resourceProvider.getUrl(Fonts.ALLER_REGULAR).toExternalForm(), 24);

    dialoguePanel.styleProperty().bind(
        Bindings.concat("-fx-font-size: ", rootPane.heightProperty().multiply(16.0 / BASE_HEIGHT), "px;")
    );

    dialogueButtons.spacingProperty().bind(
        rootPane.widthProperty().multiply(64.0 / BASE_WIDTH)
    );

    hoverSound = SoundHelper.loadSoundEffect(resourceProvider.getPath(AudioSfx.HOVER), 0.5);
    selectSound = SoundHelper.loadSoundEffect(resourceProvider.getPath(AudioSfx.SELECT), 0.8);
  }
}
