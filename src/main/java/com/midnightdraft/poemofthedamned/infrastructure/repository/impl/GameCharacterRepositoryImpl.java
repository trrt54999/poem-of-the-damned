package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.GameCharacter;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.infrastructure.repository.GameCharacterRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.Optional;
import org.hibernate.Session;

public class GameCharacterRepositoryImpl extends BaseRepositoryImpl<GameCharacter> implements
    GameCharacterRepository {

  public GameCharacterRepositoryImpl(){
    super(GameCharacter.class);
  }

  @Override
  public Optional<GameCharacter> findByName(String name){
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM GameCharacter WHERE name = :name", GameCharacter.class)
          .setParameter("name", name)
          .uniqueResultOptional();
    } catch (Exception e){
      throw new EntityFetchException(GameCharacter.class.getSimpleName(), e);
    }
  }
}
