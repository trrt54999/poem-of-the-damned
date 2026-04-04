package com.midnightdraft.poemofthedamned.domain.repository;

import com.midnightdraft.poemofthedamned.domain.model.GameCharacterSprite;
import java.util.List;
import java.util.Optional;

public interface GameCharacterSpriteRepository extends BaseRepository<GameCharacterSprite> {

  Optional<GameCharacterSprite> findByCharacterIdAndEmotion(Long characterId, String emotion);

  List<GameCharacterSprite> findByCharacterId(Long characterId);
}
