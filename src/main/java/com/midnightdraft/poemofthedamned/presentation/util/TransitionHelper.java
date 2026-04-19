package com.midnightdraft.poemofthedamned.presentation.util;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransitionHelper {

  public FadeTransition fade(Node node, double from, double to, double millis) {
    FadeTransition ft = new FadeTransition(Duration.millis(millis), node);
    ft.setFromValue(from);
    ft.setToValue(to);
    return ft;
  }

  public FadeTransition fadeIn(Node node, double millis) {
    return fade(node, 0.0, 1.0, millis);
  }

  public FadeTransition fadeOut(Node node, double millis) {
    return fade(node, 1.0, 0.0, millis);
  }

  public ParallelTransition pop(Node node, double fromScale, double toScale,
      double fromOpacity, double toOpacity, double millis) {
    ScaleTransition scale = new ScaleTransition(Duration.millis(millis), node);
    scale.setFromX(fromScale);
    scale.setFromY(fromScale);
    scale.setToX(toScale);
    scale.setToY(toScale);

    FadeTransition fade = fade(node, fromOpacity, toOpacity, millis);
    return new ParallelTransition(scale, fade);
  }

  public ParallelTransition popIn(Node node, double millis) {
    return pop(node, 0.92, 1.0, 0.0, 1.0, millis);
  }

  public ParallelTransition popOut(Node node, double millis) {
    return pop(node, 1.0, 0.92, 1.0, 0.0, millis);
  }
}
