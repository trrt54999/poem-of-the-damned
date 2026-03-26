package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.DialogueRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class DialogueRepositoryImpl extends BaseRepositoryImpl<Dialogue> implements
    DialogueRepository {

  public DialogueRepositoryImpl(){
    super(Dialogue.class);
  }

  @Override
  public Optional<Dialogue> findBySceneId(Long sceneId) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery(
              "FROM Dialogue d " +
                  "LEFT JOIN FETCH d.gameCharacter " +
                  "LEFT JOIN FETCH d.gameCharacterSprite " +
                  "WHERE d.gameScene.id = :sceneId " +
                  "ORDER BY d.orderIndex ASC", Dialogue.class)
          .setParameter("sceneId", sceneId)
          .setMaxResults(1)
          .uniqueResultOptional();
    } catch (Exception e) {
      log.error("Failed to fetch Dialogue by sceneId: {}", sceneId, e);
      throw new EntityFetchException(Dialogue.class.getSimpleName(), e);
    }
  }

  @Override
  public List<Dialogue> findBySceneIdOrderByOrderIndex(Long sceneId) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery(
              "SELECT d FROM Dialogue d "
                  + "LEFT JOIN FETCH d.gameCharacter "
                  + "LEFT JOIN FETCH d.gameCharacterSprite "
                  + "WHERE d.gameScene.id = :sceneId "
                  + "ORDER BY d.orderIndex ASC",
              Dialogue.class)
          .setParameter("sceneId", sceneId)
          .getResultList();
    } catch (Exception e){
      log.error("Failed to fetch Dialogues by sceneId: {}", sceneId, e);
      throw new EntityFetchException(Dialogue.class.getSimpleName(), e);
    }
  }
}
