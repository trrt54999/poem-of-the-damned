package com.midnightdraft.poemofthedamned.infrastructure.repository;

import com.midnightdraft.poemofthedamned.domain.model.UserAchievement;
import java.util.List;

public interface UserAchievementRepository extends BaseRepository<UserAchievement> {

  List<UserAchievement> findByUserId(Long userId);
}
