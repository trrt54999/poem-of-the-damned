package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.Character;
import com.midnightdraft.poemofthedamned.infrastructure.repository.CharacterRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.Optional;
import org.hibernate.Session;

public class CharacterRepositoryImpl extends BaseRepositoryImpl<Character> implements
    CharacterRepository {

  public CharacterRepositoryImpl(){
    super(Character.class);
  }

  @Override
  public Optional<Character> findByName(String name){
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM Character WHERE name = :name", Character.class)
          .setParameter("name", name)
          .uniqueResultOptional();
    }
  }
}
