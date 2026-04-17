package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.dto.ClientSettingsDTO;
import com.midnightdraft.poemofthedamned.domain.repository.ClientSettingsRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaveClientSettingsUseCase {

  private final ClientSettingsRepository clientSettingsRepository;

  public SaveClientSettingsUseCase(ClientSettingsRepository clientSettingsRepository) {
    this.clientSettingsRepository = clientSettingsRepository;
  }

  public void execute(ClientSettingsDTO dto, Long userId) {
    clientSettingsRepository.findByUserId(userId).ifPresentOrElse(settings -> {
      settings.setScreenResolution(dto.screenResolution());
      settings.setGameLanguage(dto.gameLanguage());
      settings.setMusicVolume(dto.musicVolume());
      settings.setSoundVolume(dto.soundVolume());
      settings.setIsFullScreen(dto.isFullScreen());
      clientSettingsRepository.update(settings);

      log.info("Successfully updated client settings for user");
    }, () -> log.warn("Attempted to update settings for non-existent user ID"));
  }
}
