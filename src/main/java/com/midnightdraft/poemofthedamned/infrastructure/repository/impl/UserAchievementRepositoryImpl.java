package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.UserAchievement;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.UserAchievementRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class UserAchievementRepositoryImpl extends BaseRepositoryImpl<UserAchievement> implements
    UserAchievementRepository {

  public UserAchievementRepositoryImpl() {
    super(UserAchievement.class);
  }

  @Override
  public List<UserAchievement> findByUserId(Long userId) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery("FROM UserAchievement WHERE user.id = :userId",
              UserAchievement.class)
          .setParameter("userId", userId)
          .getResultList();
    } catch (Exception e) {
      log.error("Failed to fetch UserAchievements by userId: {}", userId, e);
      throw new EntityFetchException(UserAchievement.class.getSimpleName(), e);
    }
  }
}
