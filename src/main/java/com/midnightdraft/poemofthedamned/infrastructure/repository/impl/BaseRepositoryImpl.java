package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import com.midnightdraft.poemofthedamned.infrastructure.repository.BaseRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

// todo або до винятків норм коменти, або кастомні винятки
@AllArgsConstructor
public abstract class BaseRepositoryImpl<T extends BaseEntity> implements BaseRepository<T> {

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
