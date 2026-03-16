package com.midnightdraft.poemofthedamned.domain.repository;

import com.midnightdraft.poemofthedamned.domain.model.SaveSlot;
import java.util.List;

public interface SaveSlotRepository extends BaseRepository<SaveSlot> {

  List<SaveSlot> findByUserId(Long userId);
}
