package com.midnightdraft.poemofthedamned.infrastructure.repository;

import com.midnightdraft.poemofthedamned.domain.model.SaveFlag;
import java.util.List;
import java.util.Optional;

public interface SaveFlagRepository extends BaseRepository<SaveFlag> {

  List<SaveFlag> findBySaveSlotId(Long saveSlotId);
  Optional<SaveFlag> findBySaveSlotIdAndFlagId(Long saveSlotId, Long flagId);
}
