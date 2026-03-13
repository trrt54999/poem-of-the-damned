package com.midnightdraft.poemofthedamned.infrastructure.impl;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import com.midnightdraft.poemofthedamned.infrastructure.repository.BasicRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

@AllArgsConstructor
public abstract class BasicRepositoryImpl<T extends BaseEntity> implements BasicRepository<T> {

  private final Class<T> entityClass;

  @Override
  public void save(T entity){
    Transaction tx = null;
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      session.persist(entity);
      tx.commit();
    }catch (Exception e) {
      if (tx != null) tx.rollback();
      throw new RuntimeException(entityClass.getSimpleName(), e);
    }
  }

  @Override
  public void update(T entity){
    Transaction tx = null;
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      session.merge(entity);
      tx.commit();
    } catch (Exception e) {
      if (tx != null) tx.rollback();
      throw new RuntimeException(entityClass.getSimpleName(), e);
    }
  }

  @Override
  public void delete(T entity){
    Transaction tx = null;
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      session.remove(session.contains(entity) ? entity : session.merge(entity));
      tx.commit();
    } catch (Exception e) {
      if (tx != null) tx.rollback();
      throw new RuntimeException(entityClass.getSimpleName(), e);
    }
  }

  @Override
  public Optional<T> findById(Long id){
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      T entity = session.find(entityClass, id);
      return Optional.ofNullable(entity);
    }
  }

  @Override
  public List<T> findAll(){
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
          .getResultList();
    }
  }
}
