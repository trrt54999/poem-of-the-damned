package com.midnightdraft.poemofthedamned.infrastructure.repository.impl;

import com.midnightdraft.poemofthedamned.domain.model.ClientSettings;
import com.midnightdraft.poemofthedamned.domain.repository.ClientSettingsRepository;
import com.midnightdraft.poemofthedamned.infrastructure.exception.RepositoryException.EntityFetchException;
import com.midnightdraft.poemofthedamned.infrastructure.util.HibernateSessionFactory;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

@Slf4j
public class ClientSettingsRepositoryImpl extends BaseRepositoryImpl<ClientSettings> implements
    ClientSettingsRepository {

  public ClientSettingsRepositoryImpl() {
    super(ClientSettings.class);
  }

  @Override
  public Optional<ClientSettings> findByUserId(Long userId) {
    try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
      return session.createQuery(
          "SELECT cs FROM ClientSettings cs " + "JOIN cs.user u " + "WHERE u.id = :userId",
          ClientSettings.class).setParameter("userId", userId).uniqueResultOptional();
    } catch (Exception e) {
      log.error("Failed to fetch ClientSettings by userId: {}", userId, e);
      throw new EntityFetchException(ClientSettings.class.getSimpleName(), e);
    }
  }
}