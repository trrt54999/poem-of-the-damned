package com.midnightdraft.poemofthedamned.domain.repository;

import com.midnightdraft.poemofthedamned.domain.model.ClientSettings;
import java.util.Optional;

public interface ClientSettingsRepository extends BaseRepository<ClientSettings> {

  Optional<ClientSettings> findByUserId(Long userId);
}
