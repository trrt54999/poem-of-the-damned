package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.dto.ClientSettingsDTO;
import com.midnightdraft.poemofthedamned.domain.model.ClientSettings;
import com.midnightdraft.poemofthedamned.domain.repository.ClientSettingsRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadClientSettingsUseCase {

  private final ClientSettingsRepository clientSettingsRepository;

  public LoadClientSettingsUseCase(ClientSettingsRepository clientSettingsRepository) {
    this.clientSettingsRepository = clientSettingsRepository;
  }

  public ClientSettingsDTO execute(Long userId) {
    log.debug("Fetching client settings for user");
    Optional<ClientSettings> clientSettings = clientSettingsRepository.findByUserId(userId);
    return clientSettings.map(settings -> {
      log.debug("Settings found. Loading configured data.");
      return new ClientSettingsDTO(settings.getScreenResolution(), settings.getGameLanguage(),
          settings.getMusicVolume(), settings.getSoundVolume(), settings.getIsFullScreen());
    }).orElseGet(() -> {
      log.info("No custom settings found for user. Falling back to default settings.");
      return ClientSettingsDTO.defaults();
    });
  }
}
