package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.User;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.UserRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.Optional;
import org.hibernate.Session;

public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

  public UserRepositoryImpl(){
    super(User.class);
  }

  @Override
  public Optional<User> findByName(String username) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM User WHERE username = :username", User.class)
          .setParameter("username", username)
          .uniqueResultOptional();
    } catch (Exception e){
      throw new EntityFetchException(User.class.getSimpleName(), e);
    }
  }

  @Override
  public Optional<User> findByEmail(String email) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM User WHERE email = :email", User.class)
          .setParameter("email", email)
          .uniqueResultOptional();
    } catch (Exception e){
      throw new EntityFetchException(User.class.getSimpleName(), e);
    }
  }

  @Override
  public boolean existsByEmail(String email) {
    return findByEmail(email).isPresent();
  }
}
