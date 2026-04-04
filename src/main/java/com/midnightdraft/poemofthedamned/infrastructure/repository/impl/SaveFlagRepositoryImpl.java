package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.SaveFlag;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.SaveFlagRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class SaveFlagRepositoryImpl extends BaseRepositoryImpl<SaveFlag> implements
    SaveFlagRepository {

  public SaveFlagRepositoryImpl() {
    super(SaveFlag.class);
  }

  @Override
  public List<SaveFlag> findBySaveSlotId(Long saveSlotId) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery("FROM SaveFlag WHERE saveSlot.id = :saveSlotId", SaveFlag.class)
          .setParameter("saveSlotId", saveSlotId)
          .getResultList();
    } catch (Exception e) {
      log.error("Failed to fetch SaveFlags by saveSlotId: {}", saveSlotId, e);
      throw new EntityFetchException(SaveFlag.class.getSimpleName(), e);
    }
  }

  @Override
  public Optional<SaveFlag> findBySaveSlotIdAndFlagId(Long saveSlotId, Long flagId) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery(
              "FROM SaveFlag WHERE saveSlot.id = :saveSlotId AND flag.id = :flagId",
              SaveFlag.class)
          .setParameter("saveSlotId", saveSlotId)
          .setParameter("flagId", flagId)
          .uniqueResultOptional();
    } catch (Exception e) {
      log.error("Failed to fetch SaveFlag by saveSlotId: {} and flagId: {}", saveSlotId, flagId, e);
      throw new EntityFetchException(SaveFlag.class.getSimpleName(), e);
    }
  }
}
