package com.midnightdraft.poemofthedamned.infrastructure.repository;


import com.midnightdraft.poemofthedamned.domain.model.GameCharacter;
import java.util.Optional;

public interface GameCharacterRepository extends BaseRepository<GameCharacter> {

  Optional<GameCharacter> findByName(String name);
}
