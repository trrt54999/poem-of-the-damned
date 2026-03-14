package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.Dialogue;
import com.midnightdraft.poemofthedamned.infrastructure.repository.DialogueRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

public class DialogueRepositoryImpl extends BaseRepositoryImpl<Dialogue> implements
    DialogueRepository {

  public DialogueRepositoryImpl(){
    super(Dialogue.class);
  }

  @Override
  public Optional<Dialogue> findBySceneId(Long sceneId) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM Dialogue WHERE gameScene.id = :sceneId"
              + " ORDER BY orderIndex ASC", Dialogue.class)
          .setParameter("sceneId", sceneId)
          .setMaxResults(1)
          .uniqueResultOptional();
    }
  }

  @Override
  public List<Dialogue> findBySceneIdOrderByOrderIndex(Long sceneId) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM Dialogue WHERE gameScene.id = :sceneId "
              + "ORDER BY orderIndex ASC", Dialogue.class)
          .setParameter("sceneId", sceneId)
          .getResultList();
    }
  }
}
