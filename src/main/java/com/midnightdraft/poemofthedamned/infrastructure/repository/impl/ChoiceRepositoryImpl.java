package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.Choice;
import com.midnightdraft.poemofthedamned.infrastructure.repository.ChoiceRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import org.hibernate.Session;

public class ChoiceRepositoryImpl extends BaseRepositoryImpl<Choice> implements ChoiceRepository {

  public ChoiceRepositoryImpl(){
    super(Choice.class);
  }

  @Override
  public List<Choice> findBySceneId(Long sceneId){
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM Choice WHERE gameScene.id = :sceneId "
              + "ORDER BY orderIndex ASC", Choice.class)
          .setParameter("sceneId", sceneId)
          .getResultList();
    }
  }
}
