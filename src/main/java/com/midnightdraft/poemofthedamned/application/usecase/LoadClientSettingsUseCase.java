package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.dto.ClientSettingsDTO;
import com.midnightdraft.poemofthedamned.domain.model.ClientSettings;
import com.midnightdraft.poemofthedamned.domain.repository.ClientSettingsRepository;
import java.util.Optional;

public class LoadClientSettingsUseCase {

  private final ClientSettingsRepository clientSettingsRepository;

  public LoadClientSettingsUseCase(ClientSettingsRepository clientSettingsRepository) {
    this.clientSettingsRepository = clientSettingsRepository;
  }

  public ClientSettingsDTO execute(Long userId) {
    Optional<ClientSettings> clientSettings = clientSettingsRepository.findByUserId(userId);

    return clientSettings.map(settings -> new ClientSettingsDTO(settings.getScreenResolution(),
        settings.getGameLanguage(), settings.getMusicVolume(), settings.getSoundVolume(),
        settings.getIsFullScreen())).orElseGet(ClientSettingsDTO::defaults);
  }
}
