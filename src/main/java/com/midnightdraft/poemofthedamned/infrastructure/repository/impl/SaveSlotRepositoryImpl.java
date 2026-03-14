package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.SaveSlot;
import com.midnightdraft.poemofthedamned.infrastructure.repository.SaveSlotRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import org.hibernate.Session;

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
    }
  }
}
