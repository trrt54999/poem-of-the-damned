package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.presentation.util.SoundManager;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class GameMainMenuController {

  @FXML
  private StackPane rootPane;
  @FXML
  private Pane leftPanel;
  @FXML
  private Pane dividerLine;
  @FXML
  private Canvas backgroundCanvas;

  private Image patternImage;

  private AudioClip hoverSound;
  private AudioClip selectSound;

  private static final double SPEED_X = -50.0;
  private static final double SPEED_Y = -30.0;
  private static final double GAP = 40.0;

  @FXML
  public void initialize() {
    loadResources();

    backgroundCanvas.widthProperty().bind(rootPane.widthProperty());
    backgroundCanvas.heightProperty().bind(rootPane.heightProperty());

    leftPanel.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.25));
    dividerLine.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.03));
    dividerLine.translateXProperty().bind(leftPanel.prefWidthProperty());

    Rectangle clip = new Rectangle();
    clip.xProperty().bind(leftPanel.widthProperty().add(dividerLine.widthProperty()));
    clip.widthProperty().bind(
        rootPane.widthProperty()
            .subtract(leftPanel.widthProperty())
            .subtract(dividerLine.widthProperty())
    );
    clip.heightProperty().bind(rootPane.heightProperty());
    backgroundCanvas.setClip(clip);

    startAnimation();
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

  private void loadResources() {
    String css = this.getClass().getResource("/css/main-menu.css").toExternalForm();
    rootPane.getStylesheets().add(css);

    Font.loadFont(getClass().getResourceAsStream("/assets/fonts/RifficFree-Bold.ttf"), 36);
    patternImage = new Image(getClass().getResourceAsStream("/assets/ui/Circles.png"));

    hoverSound = SoundManager.loadSound("/assets/audio/sfx/hover.wav", 0.5);
    selectSound = SoundManager.loadSound("/assets/audio/sfx/select.wav", 0.8);
  }

  private void startAnimation() {
    if (patternImage == null)
      return;

    GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();

    AnimationTimer animationTimer = new AnimationTimer() {
      private long lastUpdate = 0;
      private double worldX = 0;
      private double worldY = 0;

      @Override
      public void handle(long now) {
        if (lastUpdate == 0) {
          lastUpdate = now;
          return;
        }
        double elapsedSeconds = (now - lastUpdate) / 1_000_000_000.0;
        lastUpdate = now;

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