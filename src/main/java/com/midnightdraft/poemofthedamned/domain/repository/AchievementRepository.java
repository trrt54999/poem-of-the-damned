package com.midnightdraft.poemofthedamned.domain.repository;

import com.midnightdraft.poemofthedamned.domain.model.Achievement;
import java.util.List;
import java.util.Optional;

public interface AchievementRepository extends BaseRepository<Achievement> {

  Optional<Achievement> findByTitle(String title);

  List<Achievement> findByIsHidden(Boolean isHidden);
}
