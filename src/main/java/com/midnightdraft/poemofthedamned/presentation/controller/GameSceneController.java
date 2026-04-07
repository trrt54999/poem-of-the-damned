package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.application.dto.ChoiceDTO;
import com.midnightdraft.poemofthedamned.application.usecase.AdvanceDialogueUseCase;
import com.midnightdraft.poemofthedamned.application.usecase.CompleteSceneTransitionUseCase;
import com.midnightdraft.poemofthedamned.application.usecase.GetAvailableChoicesUseCase;
import com.midnightdraft.poemofthedamned.application.usecase.SelectChoiceUseCase;
import com.midnightdraft.poemofthedamned.application.usecase.StartSceneUseCase;
import com.midnightdraft.poemofthedamned.domain.engine.ChoiceResult;
import com.midnightdraft.poemofthedamned.domain.engine.DialogueResult;
import com.midnightdraft.poemofthedamned.domain.engine.DialogueStep;
import com.midnightdraft.poemofthedamned.domain.engine.EngineResponse;
import com.midnightdraft.poemofthedamned.domain.engine.GameStateMachine;
import com.midnightdraft.poemofthedamned.domain.engine.SpritePosition;
import com.midnightdraft.poemofthedamned.domain.engine.TransitionResult;
import com.midnightdraft.poemofthedamned.domain.engine.TransitionType;
import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.domain.provider.AudioKey;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioAmbient;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioBgm;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioSfx;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Backgrounds;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.GameCharacters;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceRepository;
import com.midnightdraft.poemofthedamned.domain.repository.DialogueRepository;
import com.midnightdraft.poemofthedamned.domain.repository.GameSceneRepository;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.repository.impl.ChoiceRepositoryImpl;
import com.midnightdraft.poemofthedamned.infrastructure.repository.impl.DialogueRepositoryImpl;
import com.midnightdraft.poemofthedamned.infrastructure.repository.impl.GameSceneRepositoryImpl;
import com.midnightdraft.poemofthedamned.presentation.util.SoundHelper;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
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
  private StackPane fadeOverlay;
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
  private MediaPlayer currentAmbient;
  private Transition typewriterTransition;
  private String currentFullText = "";

  // fixme: final impl its costyl, maybe later fix, but its hard to fix..
  private boolean isInputLocked = false;
  private final Text typedText = new Text();
  private final Text untypedText = new Text();
  private final Map<SpritePosition, String> currentSprites = new EnumMap<>(SpritePosition.class);
  private final ChoiceRepository choiceRepository = new ChoiceRepositoryImpl();
  private final GetAvailableChoicesUseCase getAvailableChoicesUseCase =
      new GetAvailableChoicesUseCase(GameStateMachine.getInstance(), choiceRepository);
  private final DialogueRepository dialogueRepository = new DialogueRepositoryImpl();
  private final GameSceneRepository gameSceneRepository = new GameSceneRepositoryImpl();
  private final StartSceneUseCase startSceneUseCase = new StartSceneUseCase(dialogueRepository,
      GameStateMachine.getInstance());
  private final AdvanceDialogueUseCase advanceDialogueUseCase = new AdvanceDialogueUseCase(
      GameStateMachine.getInstance());
  private final CompleteSceneTransitionUseCase completeSceneTransitionUseCase =
      new CompleteSceneTransitionUseCase(GameStateMachine.getInstance(), gameSceneRepository,
          startSceneUseCase, advanceDialogueUseCase);
  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();
  private final SelectChoiceUseCase selectChoiceUseCase = new SelectChoiceUseCase(
      GameStateMachine.getInstance(),
      choiceRepository, advanceDialogueUseCase);

  private static final double BASE_WIDTH = 1280.0;
  private static final double BASE_HEIGHT = 720.0;

  @FXML
  public void initialize() {
    log.info("Initialize game");
    setupStylesAndFonts();
    setupBackground();
    setupDialoguePanel();
    setupSpritesBindings();
    setupChoiceContainerBindings();
    setupAudio();

    // costyl?
    GameScene firstScene = gameSceneRepository.findById(1L).orElseThrow();
    log.info("Starting first scene: '{}'", firstScene.getTitle());
    startSceneUseCase.execute(firstScene);
    handleResponse(advanceDialogueUseCase.execute());
  }

  @FXML
  public void playHoverSound() {
    if (hoverSound != null) {
      hoverSound.play();
    }
  }

  @FXML
  public void playSelectSound() {
    if (selectSound != null) {
      selectSound.play();
    }
  }

  @FXML
  public void handleScreenClick() {
    if (isInputLocked) {
      return;
    }

    if (typewriterTransition != null && typewriterTransition.getStatus() == Status.RUNNING) {
      typewriterTransition.stop();
      typedText.setText(currentFullText);
      untypedText.setText("");
      nextIndicator.setVisible(true);
      return;
    }
    handleResponse(advanceDialogueUseCase.execute());
  }

  private void setupStylesAndFonts() {
    String css = resourceProvider.getUrl(Css.GAME_SCENE).toExternalForm();
    rootPane.getStylesheets().add(css);

    Font.loadFont(resourceProvider.getUrl(Fonts.RIFFIC_FREE_BOLD).toExternalForm(), 36);
    Font.loadFont(resourceProvider.getUrl(Fonts.ALLER_BOLD).toExternalForm(), 42);
    Font.loadFont(resourceProvider.getUrl(Fonts.UBUNTU_BOLD).toExternalForm(), 42);
    Font.loadFont(resourceProvider.getUrl(Fonts.ALLER_REGULAR).toExternalForm(), 24);

    dialoguePanel.styleProperty().bind(
        Bindings.concat("-fx-font-size: ", rootPane.heightProperty().multiply(
            16.0 / BASE_HEIGHT), "px;")
    );
  }

  private void setupBackground() {
    backgroundImage.fitWidthProperty().bind(rootPane.widthProperty());
    backgroundImage.fitHeightProperty().bind(rootPane.heightProperty());

    backgroundImage.setEffect(new GaussianBlur(4.0));
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

    setupNextIndicator();
  }

  private void setupNextIndicator() {
    nextIndicator.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.02));
    nextIndicator.setImage(
        new Image(resourceProvider.getUrl(Ui.DIALOGUE_RECTANGLE).toExternalForm()));

    rootPane.heightProperty().addListener((_, _, newVal) -> {
      double dynamicOffset = newVal.doubleValue() * (7.0 / BASE_HEIGHT);
      AnchorPane.setRightAnchor(nextIndicator, dynamicOffset);
      AnchorPane.setBottomAnchor(nextIndicator, dynamicOffset);
    });

    TranslateTransition translate = new TranslateTransition(Duration.millis(650), nextIndicator);
    translate.setByX(-10.0);

    FadeTransition fade = new FadeTransition(Duration.millis(650), nextIndicator);
    fade.setFromValue(1.0);
    fade.setToValue(0.4);

    ParallelTransition beatingAnimation = new ParallelTransition(translate, fade);
    beatingAnimation.setCycleCount(Animation.INDEFINITE);
    beatingAnimation.setAutoReverse(true);
    beatingAnimation.play();
  }

  private void setupChoiceContainerBindings() {
    choiceContainer.spacingProperty().bind(rootPane.heightProperty().multiply(0.05));
    choiceContainer.styleProperty().bind(
        Bindings.concat("-fx-font-size: ",
            rootPane.heightProperty().multiply(32.0 / BASE_HEIGHT), "px;")
    );
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

  private void playTransition(TransitionType transitionType) {
    switch (transitionType) {
      case TransitionType.FADE_BLACK -> startFadeOut();
      case TransitionType.NONE -> {
        clearSprites();
        handleResponse(completeSceneTransitionUseCase.execute());
      }
    }
  }

  private void handleResponse(EngineResponse response) {
    switch (response) {
      case DialogueResult(DialogueStep step) -> renderDialogueStep(step);
      case TransitionResult(TransitionType transitionType) -> playTransition(transitionType);
      case ChoiceResult _ -> showChoices();
      default -> log.warn("Unhandled EngineResponse type: {}", response.getClass().getSimpleName());
    }
  }

  // погратися з налаштуваннями мілісекунд
  private void startFadeOut() {
    fadeOverlay.setVisible(true);
    isInputLocked = true;
    FadeTransition eclipseBeginningTransition = new FadeTransition(Duration.millis(1000),
        fadeOverlay);
    eclipseBeginningTransition.setFromValue(0.0);
    eclipseBeginningTransition.setToValue(1.0);
    eclipseBeginningTransition.play();

    eclipseBeginningTransition.setOnFinished(_ -> {
      clearSprites();
      handleResponse(completeSceneTransitionUseCase.execute());

      FadeTransition eclipseEndingTransition = new FadeTransition(Duration.millis(1000),
          fadeOverlay);
      eclipseEndingTransition.setFromValue(1.0);
      eclipseEndingTransition.setToValue(0.0);
      eclipseEndingTransition.play();
      eclipseEndingTransition.setOnFinished(_ -> {

        fadeOverlay.setVisible(false);
        isInputLocked = false;
      });
    });
  }

  private void setupAudio() {
    hoverSound = SoundHelper.loadSoundEffect(resourceProvider.getPath(AudioSfx.HOVER), 0.5);
    selectSound = SoundHelper.loadSoundEffect(resourceProvider.getPath(AudioSfx.SELECT), 0.8);
  }

  private void renderDialogueStep(DialogueStep step) {
    if (step == null || step.text().isBlank() || step.backgroundPath().isBlank()) {
      log.warn("Received empty or null DialogueStep");
      return;
    }

    nextIndicator.setVisible(false);

    updateBackground(step.backgroundPath());
    updateSprites(step);

    nameplatePanel.setVisible(step.characterName().isPresent());
    step.characterName().ifPresent(characterNameLabel::setText);

    playTypewriter(step.text());
    typewriterTransition.setOnFinished(_ -> nextIndicator.setVisible(true));
    step.musicPath().ifPresent(this::switchMusic);
    step.ambientPath().ifPresent(this::switchAmbient);
    // highlightSpeaker(step.characterName().orElse(null));
  }

  private void showChoices() {
    List<ChoiceDTO> choices = getAvailableChoicesUseCase.execute();

    choiceContainer.getChildren().clear();

    dialoguePanel.setVisible(false);
    choiceContainer.setVisible(true);

    for (ChoiceDTO choice : choices) {
      Button btn = new Button(choice.choiceText());
      btn.getStyleClass().add("choice-plaque");
      btn.setWrapText(true);
      btn.setAlignment(javafx.geometry.Pos.CENTER);

      btn.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.45));
      btn.prefHeightProperty().bind(rootPane.heightProperty().multiply(0.06));

      btn.setOnMouseEntered(_ -> playHoverSound());
      btn.setOnAction(_ -> {
        playSelectSound();
        choiceContainer.setVisible(false);
        dialoguePanel.setVisible(true);

        handleResponse(selectChoiceUseCase.execute(choice.id()));
      });
      choiceContainer.getChildren().add(btn);
    }
  }

  private MediaPlayer switchTrack(MediaPlayer current, AudioKey key) {
    String fullPath = resourceProvider.getUrl(key).toExternalForm();

    if (current != null && current.getMedia().getSource().equals(fullPath)) {
      return current;
    }

    MediaPlayer player = SoundHelper.createBackgroundMusic(fullPath, 0.5);
    if (player == null) {
      return null;
    }

    player.setCycleCount(MediaPlayer.INDEFINITE);

    if (current != null) {
      Timeline timeline = new Timeline(
          new KeyFrame(Duration.seconds(2.0),
              new KeyValue(current.volumeProperty(), 0.0)));

      timeline.setOnFinished(_ -> {
        current.stop();
        player.play();
      });
      timeline.play();
    } else {
      player.play();
    }

    return player;
  }

  private void switchMusic(String newMusicKey) {
    AudioBgm key = AudioBgm.valueOf(newMusicKey);
    log.debug("Switching music to: {}", key);
    if (key == AudioBgm.SILENCE) {
      if (currentMusic != null) {
        currentMusic.stop();
      }
      currentMusic = null;
      return;
    }
    if (key == AudioBgm.FADE_OUT) {
      fadeOutAndStop(currentMusic);
      currentMusic = null;
      return;
    }
    currentMusic = switchTrack(currentMusic, key);
  }

  private void switchAmbient(String newAmbientKey) {
    AudioAmbient key = AudioAmbient.valueOf(newAmbientKey);
    log.debug("Switching ambient to: {}", key);
    if (key == AudioAmbient.SILENCE) {
      if (currentAmbient != null) {
        currentAmbient.stop();
      }
      currentAmbient = null;
      return;
    }

    if (key == AudioAmbient.FADE_OUT) {
      fadeOutAndStop(currentAmbient);
      currentAmbient = null;
      return;
    }
    currentAmbient = switchTrack(currentAmbient, key);
  }

  private void fadeOutAndStop(MediaPlayer player) {
    if (player == null) {
      return;
    }

    MediaPlayer oldPlayer = player;
    Timeline timeline = new Timeline(
        new KeyFrame(Duration.seconds(2.0),
            new KeyValue(oldPlayer.volumeProperty(), 0.0)));

    timeline.setOnFinished(_ -> oldPlayer.stop());
    timeline.play();
  }

  private void updateBackground(String backgroundName) {
    Backgrounds bgKey = Backgrounds.valueOf(backgroundName);
    String fullPath = resourceProvider.getUrl(bgKey).toExternalForm();

    Image current = backgroundImage.getImage();
    if (current == null || !current.getUrl().equals(fullPath)) {
      backgroundImage.setImage(new Image(fullPath));
    }
  }

  private void updateSprites(DialogueStep step) {
    if (step.characterSpritePath().isEmpty() || step.spritePosition().isEmpty()) {
      return;
    }

    String spriteName = step.characterSpritePath().orElseThrow();
    SpritePosition position = step.spritePosition().orElseThrow();

    if (!spriteName.equals(currentSprites.get(position))) {
      currentSprites.put(position, spriteName);
      GameCharacters spriteKey = GameCharacters.valueOf(spriteName);
      String fullPath = resourceProvider.getUrl(spriteKey).toExternalForm();
      Image newSprite = new Image(fullPath);

      switch (position) {
        case LEFT -> leftSpritePosition.setImage(newSprite);
        case RIGHT -> rightSpritePosition.setImage(newSprite);
        case CENTRAL -> centralSpritePosition.setImage(newSprite);
      }
    }
  }


/*  Може знадобитися як зміна емоції у спрайта
 private void playCharacterJump(ImageView targetSprite) {
    TranslateTransition transition = new TranslateTransition(Duration.millis(150), targetSprite);
    transition.setByY(-15);
    transition.setCycleCount(2);
    transition.setAutoReverse(true);
    transition.play();
  }
 */


  /*  кривий метод пістрибування перса, багато чого не враховує (думки і т.п.), потім допрацювати
  private void applyHighlight(ImageView sprite, String spriteName, String activeCharacter) {
    if (sprite.getImage() == null)
      return;

    boolean isActive = activeCharacter != null && spriteName.toLowerCase().contains(activeCharacter.toLowerCase());

    double targetScale = isActive ? 1.05 : 1.0;

    if (sprite.getScaleX() != targetScale) {
      ScaleTransition scale = new ScaleTransition(Duration.millis(250), sprite);
      scale.setToX(targetScale);
      scale.setToY(targetScale);
      scale.play();
    }
  }
   */

/*  applyHighlight -> мається на увазі обробка кожного перса по позиції
  private void highlightSpeaker(String activeCharacter) {

    applyHighlight(leftSpritePosition, currentSprites.get(SpritePosition.LEFT), activeCharacter);


    applyHighlight(rightSpritePosition, currentSprites.get(SpritePosition.RIGHT), activeCharacter);


    applyHighlight(centralSpritePosition, currentSprites.get(SpritePosition.CENTRAL), activeCharacter);
  }
*/

  private void clearSprites() {
    leftSpritePosition.setImage(null);
    rightSpritePosition.setImage(null);
    centralSpritePosition.setImage(null);
    currentSprites.clear();
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