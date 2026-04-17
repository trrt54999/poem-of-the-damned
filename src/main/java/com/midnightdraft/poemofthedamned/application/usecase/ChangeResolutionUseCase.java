package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.model.ScreenResolution;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeResolutionUseCase {

  public void execute(ScreenResolution resolution, Stage stage) {
    if (stage.isFullScreen()) {
      return;
    }

    stage.setWidth(resolution.getWidth());
    stage.setHeight(resolution.getHeight());
    stage.centerOnScreen();
    log.info("Change resolution to: {}", resolution);
  }
}
