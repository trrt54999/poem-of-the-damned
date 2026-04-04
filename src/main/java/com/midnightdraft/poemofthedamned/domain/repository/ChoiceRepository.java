package com.midnightdraft.poemofthedamned.domain.repository;

import com.midnightdraft.poemofthedamned.domain.model.Choice;
import java.util.List;

public interface ChoiceRepository extends BaseRepository<Choice> {

  List<Choice> findBySceneId(Long sceneId);
}
