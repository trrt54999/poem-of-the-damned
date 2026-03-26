package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.SaveSlot;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.SaveSlotRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class SaveSlotRepositoryImpl extends BaseRepositoryImpl<SaveSlot> implements
    SaveSlotRepository {

  public SaveSlotRepositoryImpl(){
    super(SaveSlot.class);
  }

  @Override
  public List<SaveSlot> findByUserId(Long userId) {
    try(Session session = HibernateSessionFactory.getSessionFactory().openSession()){
      return session.createQuery("FROM SaveSlot WHERE user.id = :userId", SaveSlot.class)
          .setParameter("userId", userId)
          .getResultList();
    } catch (Exception e){
      log.error("Failed to fetch SaveSlots by userId: {}", userId, e);
      throw new EntityFetchException(SaveSlot.class.getSimpleName(), e);
    }
  }
}
