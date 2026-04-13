package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.dto.ClientSettingsDTO;
import com.midnightdraft.poemofthedamned.domain.repository.ClientSettingsRepository;

public class SaveClientSettingsUseCase {

  private final ClientSettingsRepository clientSettingsRepository;

  public SaveClientSettingsUseCase(ClientSettingsRepository clientSettingsRepository) {
    this.clientSettingsRepository = clientSettingsRepository;
  }

  public void execute(ClientSettingsDTO dto, Long userId) {
    clientSettingsRepository.findByUserId(userId).ifPresent(settings -> {
      settings.setScreenResolution(dto.screenResolution());
      settings.setGameLanguage(dto.gameLanguage());
      settings.setMusicVolume(dto.musicVolume());
      settings.setSoundVolume(dto.soundVolume());
      settings.setIsFullScreen(dto.isFullScreen());
      clientSettingsRepository.update(settings);
    });
  }
}
