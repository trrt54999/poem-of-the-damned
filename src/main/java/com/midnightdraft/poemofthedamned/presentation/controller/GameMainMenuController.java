package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioSfx;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import com.midnightdraft.poemofthedamned.presentation.util.SoundHelper;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameMainMenuController {

  @FXML
  private StackPane rootPane;
  @FXML
  private Pane leftPanel;
  @FXML
  private StackPane logoContainer;
  @FXML
  private Circle logoClip;
  @FXML
  private Circle logoBorder;
  @FXML
  private Pane dividerLine;
  @FXML
  private Canvas backgroundCanvas;
  @FXML
  private ImageView logoImage;
  @FXML
  private ImageView aya;
  @FXML
  private ImageView haruka;
  @FXML
  private ImageView mio;

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  private Image patternImage;
  private AudioClip hoverSound;
  private AudioClip selectSound;

  private static final double SPEED_X = -50.0;
  private static final double SPEED_Y = -30.0;
  private static final double GAP = 40.0;

  @FXML
  public void initialize() {
    loadResources();
    setupCanvasBindings();
    setupLogoBindings();
    setupCharacterBindings();
    setupBackgroundClip();
    startAnimation();
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
    String css = resourceProvider.getUrl(Css.GAME_MAIN_MENU).toExternalForm();
    rootPane.getStylesheets().add(css);

    Font.loadFont(resourceProvider.getUrl(Fonts.RIFFIC_FREE_BOLD).toExternalForm(), 36);
    patternImage = new Image(resourceProvider.getUrl(Ui.CIRCLES).toExternalForm());

    hoverSound = SoundHelper.loadSoundEffect(resourceProvider.getPath(AudioSfx.HOVER), 0.5);
    selectSound = SoundHelper.loadSoundEffect(resourceProvider.getPath(AudioSfx.SELECT), 0.8);
  }

  private void setupCanvasBindings() {
    backgroundCanvas.widthProperty().bind(rootPane.widthProperty());
    backgroundCanvas.heightProperty().bind(rootPane.heightProperty());
  }

  private void setupLogoBindings() {
    logoImage.fitWidthProperty().bind(rootPane.heightProperty().multiply(0.24));

    logoClip.radiusProperty().bind(logoImage.fitWidthProperty().divide(2));
    logoBorder.radiusProperty().bind(logoImage.fitWidthProperty().divide(2));

    logoClip.centerXProperty().bind(logoImage.fitWidthProperty().divide(2));
    logoClip.centerYProperty().bind(logoImage.fitWidthProperty().divide(2));

    logoContainer.translateXProperty().bind(
        leftPanel.widthProperty().subtract(logoImage.fitWidthProperty().divide(2))
    );
  }

  private void setupCharacterBindings() {
    aya.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.8));
    haruka.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.8));
    mio.fitHeightProperty().bind(rootPane.heightProperty().multiply(0.8));

    haruka.translateXProperty().bind(rootPane.heightProperty().multiply(-0.26));
    aya.translateXProperty().bind(rootPane.heightProperty().multiply(-0.60));
  }

  private void setupBackgroundClip() {
    Rectangle clip = new Rectangle();
    clip.xProperty().bind(leftPanel.widthProperty().add(dividerLine.widthProperty()));
    clip.widthProperty().bind(
        rootPane.widthProperty()
            .subtract(leftPanel.widthProperty())
            .subtract(dividerLine.widthProperty())
    );
    clip.heightProperty().bind(rootPane.heightProperty());
    backgroundCanvas.setClip(clip);
  }

  private void startAnimation() {
    if (patternImage == null) return;

    GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();

    AnimationTimer animationTimer = new AnimationTimer() {
      private long lastUpdate = 0;
      private double worldX = 0;
      private double worldY = 0;

      private static final double MAX_ELAPSED_TIME = 0.05;
      private static final double DEFAULT_FRAME_TIME = 0.016;

      @Override
      public void handle(long now) {
        if (lastUpdate == 0) {
          lastUpdate = now;
          return;
        }

        double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
        lastUpdate = now;

        if (elapsedSeconds > MAX_ELAPSED_TIME) {
          elapsedSeconds = DEFAULT_FRAME_TIME;
        }

        worldX += SPEED_X * elapsedSeconds;
        worldY += SPEED_Y * elapsedSeconds;

        double imgW = patternImage.getWidth();
        double imgH = patternImage.getHeight();
        double stepX = imgW + GAP;
        double stepY = imgH + GAP;

        gc.clearRect(0, 0, backgroundCanvas.getWidth(), backgroundCanvas.getHeight());

        long startRow = (long) Math.floor(-worldY / stepY) - 2;
        long startCol = (long) Math.floor(-worldX / stepX) - 3;

        int rowCount = (int) (backgroundCanvas.getHeight() / stepY) + 5;
        int colCount = (int) (backgroundCanvas.getWidth() / stepX) + 6;

        for (int i = 0; i < rowCount; i++) {
          long currentRow = startRow + i;
          double drawY = worldY + (currentRow * stepY);
          double staggerX = ((currentRow & 1) == 0) ? 0 : stepX / 2.0;

          for (int j = 0; j < colCount; j++) {
            long currentCol = startCol + j;
            double drawX = worldX + (currentCol * stepX) + staggerX;
            gc.drawImage(patternImage, drawX, drawY, imgW, imgH);
          }
        }
      }
    };
    animationTimer.start();
  }
}