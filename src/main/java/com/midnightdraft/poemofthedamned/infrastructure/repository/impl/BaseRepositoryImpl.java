package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityDeleteException;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntitySaveException;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityUpdateException;
import com.midnightdraft.poemofthedamned.infrastructure.repository.BaseRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {

  private final Class<T> entityClass;

  protected BaseRepositoryImpl(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  @Override
  public void save(T entity){
    Transaction tx = null;
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      tx = session.beginTransaction();
      session.persist(entity);
      tx.commit();
    }catch (Exception e) {
      if (tx != null) tx.rollback();
      throw new EntitySaveException(entityClass.getSimpleName(), e);
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
      throw new EntityUpdateException(entityClass.getSimpleName(), e);
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
      throw new EntityDeleteException(entityClass.getSimpleName(), e);
    }
  }

  @Override
  public Optional<T> findById(Long id){
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      T entity = session.find(entityClass, id);
      return Optional.ofNullable(entity);
    } catch (Exception e) {
      throw new EntityFetchException(entityClass.getSimpleName(), e);
    }
  }

  @Override
  public List<T> findAll(){
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
          .getResultList();
    } catch (Exception e){
      throw new EntityFetchException(entityClass.getSimpleName(), e);
    }
  }
}
