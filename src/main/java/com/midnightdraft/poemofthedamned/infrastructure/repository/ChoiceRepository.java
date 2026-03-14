package com.midnightdraft.poemofthedamned.infrastructure.repository;

import com.midnightdraft.poemofthedamned.domain.model.Choice;
import java.util.List;

public interface ChoiceRepository extends BaseRepository<Choice>{

  List<Choice> findBySceneId(Long sceneId);
}
