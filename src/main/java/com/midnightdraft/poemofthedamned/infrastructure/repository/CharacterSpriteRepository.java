package com.midnightdraft.poemofthedamned.infrastructure.repository;

import com.midnightdraft.poemofthedamned.domain.model.CharacterSprite;
import java.util.List;
import java.util.Optional;

public interface CharacterSpriteRepository extends BaseRepository<CharacterSprite> {

  Optional<CharacterSprite> findByCharacterIdAndEmotion(Long characterId, String emotion);
  List<CharacterSprite> findByCharacterId(Long characterId);
}
