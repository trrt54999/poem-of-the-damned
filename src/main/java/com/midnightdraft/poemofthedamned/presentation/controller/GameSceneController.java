package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    String css = resourceProvider.getUrl(Css.GAME_MAIN_MENU).toExternalForm();
    rootPane.getStylesheets().add(css);

    // fonts and etc
  }
}
