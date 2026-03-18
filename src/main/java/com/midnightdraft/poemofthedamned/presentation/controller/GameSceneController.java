package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Backgrounds;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.GameCharacters;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameSceneController {

  @FXML
  private StackPane rootPane;
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

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  @FXML
  public void initialize() {
    loadResources();
  }

  private void loadResources() {
    String css = resourceProvider.getUrl(Css.GAME_SCENE).toExternalForm();
    rootPane.getStylesheets().add(css);

    leftSpritePosition.setImage(new Image(resourceProvider.getUrl(GameCharacters.HARUKA_LAUGH).toExternalForm()));
    rightSpritePosition.setImage(new Image(resourceProvider.getUrl(GameCharacters.AYA_HAPPY).toExternalForm()));
    centralSpritePosition.setImage(new Image(resourceProvider.getUrl(GameCharacters.MIO_CAT_SMILE).toExternalForm()));

    backgroundImage.setImage(new Image(resourceProvider.getUrl(Backgrounds.CLASS_DAY).toExternalForm()));
    backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
    backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());

    nextIndicator.setImage(new Image(resourceProvider.getUrl(Ui.DIALOGUE_RECTANGLE).toExternalForm()));

    leftSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.8));
    rightSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.8));
    centralSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.8));

    nextIndicator.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.03));
    // fonts and etc

    centralSpritePosition.translateXProperty().bind(
        rootPane.widthProperty().subtract(centralSpritePosition.fitWidthProperty()).divide(2)
    );
  }
}
