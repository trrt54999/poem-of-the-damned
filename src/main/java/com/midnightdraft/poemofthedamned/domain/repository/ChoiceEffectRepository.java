package com.midnightdraft.poemofthedamned.domain.repository;

import com.midnightdraft.poemofthedamned.domain.model.ChoiceEffect;
import java.util.List;

public interface ChoiceEffectRepository extends BaseRepository<ChoiceEffect>{

  List<ChoiceEffect> findByChoiceId(Long choiceId);
}
