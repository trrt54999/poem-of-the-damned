package com.midnightdraft.poemofthedamned.infrastructure.repository;


import com.midnightdraft.poemofthedamned.domain.model.Character;
import java.util.Optional;

public interface CharacterRepository extends BaseRepository<Character> {

  Optional<Character> findByName(String name);
}
