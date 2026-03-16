package com.midnightdraft.poemofthedamned.domain.repository;

import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import java.util.Optional;

public interface GameSceneRepository extends BaseRepository<GameScene> {

  Optional<GameScene> findByTitle(String title);
}
