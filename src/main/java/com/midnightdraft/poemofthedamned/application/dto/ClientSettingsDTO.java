package com.midnightdraft.poemofthedamned.application.dto;

import com.midnightdraft.poemofthedamned.domain.model.GameLanguage;
import com.midnightdraft.poemofthedamned.domain.model.ScreenResolution;

public record ClientSettingsDTO(ScreenResolution screenResolution, GameLanguage gameLanguage,
                                Float musicVolume, Float soundVolume, Boolean isFullScreen) {

  public static ClientSettingsDTO defaults() {
    return new ClientSettingsDTO(ScreenResolution.HD, GameLanguage.EN, 0.5f, 0.5f, false);
  }
}
