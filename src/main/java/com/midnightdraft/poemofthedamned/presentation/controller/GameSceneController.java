package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.application.usecase.AdvanceDialogueUseCase;
import com.midnightdraft.poemofthedamned.application.usecase.GetAvailableChoicesUseCase;
import com.midnightdraft.poemofthedamned.application.usecase.StartSceneUseCase;
import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.engine.SpritePosition;
import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioBgm;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioSfx;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Backgrounds;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.GameCharacters;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceKey;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceRepository;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.repository.impl.ChoiceRepositoryImpl;
import com.midnightdraft.poemofthedamned.presentation.util.SoundHelper;
import java.util.List;
import java.util.Optional;
import javafx.animation.Animation.Status;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
  private TextFlow dialogueTextFlow;
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
  private MediaPlayer currentMusic;
  private Transition typewriterTransition;
  private Text typedText = new Text();
  private Text untypedText = new Text();
  private String currentFullText = "";

  private final AdvanceDialogueUseCase advanceDialogueUseCase = new AdvanceDialogueUseCase(
      GameStateMachine.getInstance());
  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  private static final double BASE_WIDTH = 1280.0;
  private static final double BASE_HEIGHT = 720.0;

  // fixme costyl
  private final ChoiceRepository choiceRepository = new ChoiceRepositoryImpl();
  private final GetAvailableChoicesUseCase getAvailableChoicesUseCase = new GetAvailableChoicesUseCase(choiceRepository);

  @FXML
  public void initialize() {
    setupStylesAndFonts();
    setupBackground();
    setupDialoguePanel();
    setupSpritesBindings();
    setupAudio();

    // Тимчасовий метод для відображення хардкоду.
    // Згодом заміню його на renderDialogueStep(DialogueStep step)
   // renderMockState();
    showChoices();
    // renderMockState();
  }

  @FXML
  public void playHoverSound() {
    if (hoverSound != null) hoverSound.play();
  }

  @FXML
  public void playSelectSound() {
    if (selectSound != null) selectSound.play();
  }

  @FXML
  public void handleScreenClick() {
    if (typewriterTransition != null && typewriterTransition.getStatus() == Status.RUNNING) {
      typewriterTransition.stop();
      typedText.setText(currentFullText);
      untypedText.setText("");
      return;
    }
    advanceDialogueUseCase.execute().ifPresentOrElse(
        this::renderDialogueStep,
        this::showChoices
    );
  }

  private void setupStylesAndFonts() {
    String css = resourceProvider.getUrl(Css.GAME_SCENE).toExternalForm();
    rootPane.getStylesheets().add(css);

    Font.loadFont(resourceProvider.getUrl(Fonts.RIFFIC_FREE_BOLD).toExternalForm(), 36);
    Font.loadFont(resourceProvider.getUrl(Fonts.ALLER_BOLD).toExternalForm(), 42);
    Font.loadFont(resourceProvider.getUrl(Fonts.ALLER_REGULAR).toExternalForm(), 24);

    dialoguePanel.styleProperty().bind(
        Bindings.concat("-fx-font-size: ", rootPane.heightProperty().multiply(16.0 / BASE_HEIGHT), "px;")
    );
  }

  private void setupBackground() {
    backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
    backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());
  }

  private void setupDialoguePanel() {
    dialoguePanel.maxWidthProperty().bind(rootPane.widthProperty().multiply(0.80));
    dialoguePanel.maxHeightProperty().bind(rootPane.heightProperty().multiply(0.28));
    dialoguePanel.translateYProperty().bind(
        rootPane.heightProperty().multiply(-40.0 / BASE_HEIGHT)
    );

    dialogueButtons.spacingProperty().bind(
        rootPane.widthProperty().multiply(64.0 / BASE_WIDTH)
    );

    dialogueTextFlow.getChildren().addAll(typedText, untypedText);

    typedText.setFill(Color.WHITE);
    untypedText.setFill(Color.TRANSPARENT);

    dialogueTextFlow.effectProperty().bind(
        Bindings.createObjectBinding(() -> {
          DropShadow shadow = new DropShadow();
          shadow.setColor(Color.web("#473434"));
          shadow.setRadius(rootPane.getHeight() * (2.0 / BASE_HEIGHT));
          shadow.setSpread(1);
          return shadow;
        }, rootPane.heightProperty())
    );

    nextIndicator.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.03));
    nextIndicator.setImage(new Image(resourceProvider.getUrl(Ui.DIALOGUE_RECTANGLE).toExternalForm()));
  }

  private void setupSpritesBindings() {
    spritePosition.translateYProperty().bind(
        rootPane.heightProperty().multiply(12.0 / BASE_HEIGHT)
    );

    leftSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.85));
    rightSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.85));
    centralSpritePosition.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.85));

    leftSpritePosition.translateXProperty().bind(
        rootPane.widthProperty().multiply(0.10)
    );
    rightSpritePosition.translateXProperty().bind(
        rootPane.widthProperty().multiply(-0.14)
    );
  }

  private void setupAudio() {
    hoverSound = SoundHelper.loadSoundEffect(resourceProvider.getPath(AudioSfx.HOVER), 0.5);
    selectSound = SoundHelper.loadSoundEffect(resourceProvider.getPath(AudioSfx.SELECT), 0.8);
  }

  private void setupChoicesUi(){
    choiceContainer.maxWidthProperty().bind(rootPane.widthProperty().multiply(0.5));
    choiceContainer.setMinHeight(VBox.USE_PREF_SIZE);

  }

  private void renderMockState() {
    DialogueStep testStep = new DialogueStep(
        Optional.of("Haruka"),
        Optional.of("HARUKA_LAUGH"),
        Optional.of("RAINDROP_AND_PUDDLES"),
        Optional.of(SpritePosition.LEFT),
        "Lorem ipsum dolor sit amet consectetur adipiscing elit. "
            + "Quisque faucibus ex sapien vitae pellentesque sem placerat. "
            + "In id cursus mi pretium tellus duis convallis. "
            + "Tempus leo eu aenean sed diam urna tempor.",
        "CLASS_DAY"
    );
    renderDialogueStep(testStep);
  }

  private void renderDialogueStep(DialogueStep step){
    if(step == null || step.text().isBlank() || step.backgroundPath().isBlank()) {
      log.warn("Received empty or null DialogueStep");
      return;
    }

    updateBackground(step.backgroundPath());
    updateSprites(step);

    nameplatePanel.setVisible(step.characterName().isPresent());
    step.characterName().ifPresent(characterNameLabel::setText);

    playTypewriter(step.text());
    step.musicPath().ifPresent(this::switchMusic);
  }

  private void showChoices() {
    List<Choice> choices = getAvailableChoicesUseCase.execute();

    choiceContainer.getChildren().clear();

    choiceContainer.spacingProperty().bind(rootPane.heightProperty().multiply(0.05));

    choiceContainer.styleProperty().bind(
        Bindings.concat("-fx-font-size: ", rootPane.heightProperty().multiply(32.0 / BASE_HEIGHT), "px;")
    );

    dialoguePanel.setVisible(false);
    choiceContainer.setVisible(true);

    for (Choice choice : choices) {
      Button btn = new Button(choice.getChoiceText());
      btn.getStyleClass().add("choice-plaque");
      btn.setWrapText(true);
      btn.setAlignment(javafx.geometry.Pos.CENTER);

      btn.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.45));
      btn.prefHeightProperty().bind(rootPane.heightProperty().multiply(0.06));

      btn.setOnMouseEntered(e -> playHoverSound());
      btn.setOnAction(e -> {
        playSelectSound();
        choiceContainer.setVisible(false);
        dialoguePanel.setVisible(true);
        // логіка переходу типу тут буде, гілка 101, 102 і тп це гейм стейт машин.

      });
      choiceContainer.getChildren().add(btn);
    }
  }

  private void switchMusic(String newMusicKey) {
    ResourceKey musicKey = AudioBgm.valueOf(newMusicKey);
    String fullPath = resourceProvider.getUrl(musicKey).toExternalForm();

    if (currentMusic != null && currentMusic.getMedia().getSource().equals(fullPath)) return;
    if (currentMusic != null) currentMusic.stop();

    currentMusic = SoundHelper.createBackgroundMusic(fullPath, 0.5);
    if (currentMusic == null) return;

    currentMusic.setCycleCount(MediaPlayer.INDEFINITE);
    currentMusic.play();
  }

  private void updateBackground(String backgroundName) {
    ResourceKey bgKey = Backgrounds.valueOf(backgroundName);
    String fullPath = resourceProvider.getUrl(bgKey).toExternalForm();

    Image current = backgroundImage.getImage();
    if (current == null || !current.getUrl().equals(fullPath)) {
      backgroundImage.setImage(new Image(fullPath));
    }
  }

  private void updateSprites(DialogueStep step) {
    if (step.characterSpritePath().isEmpty() || step.spritePosition().isEmpty()) return;

    String spriteName = step.characterSpritePath().orElseThrow();
    SpritePosition position = step.spritePosition().orElseThrow();

    ResourceKey spriteKey = GameCharacters.valueOf(spriteName);
    String fullPath = resourceProvider.getUrl(spriteKey).toExternalForm();
    Image newSprite = new Image(fullPath);

    switch (position) {
      case LEFT -> leftSpritePosition.setImage(newSprite);
      case RIGHT -> rightSpritePosition.setImage(newSprite);
      case CENTRAL -> centralSpritePosition.setImage(newSprite);
    }
  }

  private void playTypewriter(String text) {
    if (typewriterTransition != null) {
      typewriterTransition.stop();
    }

    this.currentFullText = text;

    typedText.setText("");
    untypedText.setText(text);

    typewriterTransition = new Transition() {
      {
        setCycleDuration(Duration.millis(text.length() * 30.0));
      }

      @Override
      protected void interpolate(double frac) {
        int length = (int) Math.round(text.length() * frac);

        typedText.setText(text.substring(0, length));
        untypedText.setText(text.substring(length));
      }
    };
    typewriterTransition.play();
  }
}