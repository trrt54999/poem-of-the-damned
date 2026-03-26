package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.GameScene;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.GameSceneRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class GameSceneRepositoryImpl extends BaseRepositoryImpl<GameScene> implements GameSceneRepository {

  public GameSceneRepositoryImpl(){
    super(GameScene.class);
  }

  @Override
  public Optional<GameScene> findById(Long id) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery(
              "SELECT s FROM GameScene s LEFT JOIN FETCH s.nextScene WHERE s.id = :id",
              GameScene.class)
          .setParameter("id", id)
          .uniqueResultOptional();
    } catch (Exception e){
      log.error("Failed to fetch GameScene by id: {}", id, e);
      throw new EntityFetchException(GameScene.class.getSimpleName(), e);
    }
  }

  @Override
  public Optional<GameScene> findByTitle(String title) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery("FROM GameScene WHERE title = :title", GameScene.class)
          .setParameter("title", title)
          .uniqueResultOptional();
    } catch (Exception e){
      log.error("Failed to fetch GameScene by title: {}", title, e);
      throw new EntityFetchException(GameScene.class.getSimpleName(), e);
    }
  }
}
