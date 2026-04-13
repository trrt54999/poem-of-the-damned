package com.midnightdraft.poemofthedamned.presentation.util;

import static java.util.Arrays.stream;

import com.midnightdraft.poemofthedamned.domain.model.ScreenResolution;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ScreenResolutionFilter {

   public ScreenResolution[] getAvailableResolutions() {
    Rectangle2D bounds = Screen.getPrimary().getBounds();
    int nativeWidth = (int) bounds.getWidth();
    int nativeHeight = (int) bounds.getHeight();

    return stream(ScreenResolution.values())
        .filter(r -> r.getWidth() <= nativeWidth && r.getHeight() <= nativeHeight)
        .toArray(ScreenResolution[]::new);
  }
}
