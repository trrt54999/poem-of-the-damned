package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.Achievement;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.AchievementRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class AchievementRepositoryImpl extends BaseRepositoryImpl<Achievement> implements
    AchievementRepository {

  public AchievementRepositoryImpl() {
    super(Achievement.class);
  }

  @Override
  public Optional<Achievement> findByTitle(String title) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery("FROM Achievement WHERE title = :title", Achievement.class)
          .setParameter("title", title)
          .uniqueResultOptional();
    } catch (Exception e){
      log.error("Failed to fetch Achievement by title: {}", title, e);
      throw new EntityFetchException(Achievement.class.getSimpleName(), e);
    }
  }

  @Override
  public List<Achievement> findByIsHidden(Boolean isHidden) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery("FROM Achievement WHERE isHidden = :isHidden", Achievement.class)
          .setParameter("isHidden", isHidden)
          .getResultList();
    }catch (Exception e){
      log.error("Failed to fetch Achievements by isHidden: {}", isHidden, e);
      throw new EntityFetchException(Achievement.class.getSimpleName(), e);
    }
  }
}
