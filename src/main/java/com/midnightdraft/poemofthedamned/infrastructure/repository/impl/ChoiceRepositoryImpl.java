package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;

public class ChoiceRepositoryImpl extends BaseRepositoryImpl<Choice> implements ChoiceRepository {

  public ChoiceRepositoryImpl(){
    super(Choice.class);
  }

  @Override
  public Optional<Choice> findById(Long id) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery(
              "SELECT DISTINCT c FROM Choice c " +
                  "LEFT JOIN FETCH c.choiceEffects ce " +
                  "LEFT JOIN FETCH ce.flag " +
                  "LEFT JOIN FETCH c.nextGameScene "
                  + "WHERE c.id = :id", Choice.class)
          .setParameter("id", id)
          .uniqueResultOptional();
    } catch (Exception e){
      throw new EntityFetchException(Choice.class.getSimpleName(), e);
    }
  }

  @Override
  public List<Choice> findBySceneId(Long sceneId){
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery(
              "SELECT DISTINCT c FROM Choice c "
                  + "LEFT JOIN FETCH c.choiceEffects "
                  + "LEFT JOIN FETCH c.nextGameScene "
                  + "LEFT JOIN FETCH c.requiredFlag "
                  + "WHERE c.gameScene.id = :sceneId ORDER BY c.orderIndex ASC",
              Choice.class)
          .setParameter("sceneId", sceneId)
          .getResultList();
    } catch (Exception e){
      throw new EntityFetchException(Choice.class.getSimpleName(), e);
    }
  }
}
