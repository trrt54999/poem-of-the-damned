package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.GameCharacter;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.GameCharacterRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
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
      log.error("Failed to fetch GameCharacter by name: {}", name, e);
      throw new EntityFetchException(GameCharacter.class.getSimpleName(), e);
    }
  }
}
