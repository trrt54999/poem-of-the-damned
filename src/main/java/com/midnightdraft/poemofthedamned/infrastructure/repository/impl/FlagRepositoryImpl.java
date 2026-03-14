package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.Flag;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.infrastructure.repository.FlagRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.Optional;
import org.hibernate.Session;

public class FlagRepositoryImpl extends BaseRepositoryImpl<Flag> implements FlagRepository {

  public FlagRepositoryImpl(){
    super(Flag.class);
  }

  @Override
  public Optional<Flag> findByName(String name) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM Flag WHERE name = :name", Flag.class)
          .setParameter("name", name)
          .uniqueResultOptional();
    } catch (Exception e){
      throw new EntityFetchException(Flag.class.getSimpleName(), e);
    }
  }
}
