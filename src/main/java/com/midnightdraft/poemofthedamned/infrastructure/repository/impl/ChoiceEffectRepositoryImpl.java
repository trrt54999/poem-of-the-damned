package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.ChoiceEffect;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.domain.repository.ChoiceEffectRepository;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class ChoiceEffectRepositoryImpl extends BaseRepositoryImpl<ChoiceEffect> implements
    ChoiceEffectRepository {

  public ChoiceEffectRepositoryImpl() {
    super(ChoiceEffect.class);
  }

  @Override
  public List<ChoiceEffect> findByChoiceId(Long choiceId) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery("FROM ChoiceEffect WHERE choice.id = :choiceId",
              ChoiceEffect.class)
          .setParameter("choiceId", choiceId)
          .getResultList();
    } catch (Exception e) {
      log.error("Failed to fetch ChoiceEffects by choiceId: {}", choiceId, e);
      throw new EntityFetchException(ChoiceEffect.class.getSimpleName(), e);
    }
  }
}
