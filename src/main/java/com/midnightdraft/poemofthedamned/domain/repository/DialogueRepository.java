package com.midnightdraft.poemofthedamned.domain.repository;

import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import java.util.Optional;
import java.util.List;

public interface DialogueRepository extends BaseRepository<Dialogue> {

  // find only first replica from scene
  Optional<Dialogue> findBySceneId(Long sceneId);

  // find the entire scenario of a specific location in the correct order
  List<Dialogue> findBySceneIdOrderByOrderIndex(Long sceneId);
}
