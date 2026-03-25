package com.midnightdraft.poemofthedamned.infrastructure.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class HibernateSessionFactory {

  private HibernateSessionFactory(){}

  private static final Logger log = LoggerFactory.getLogger(HibernateSessionFactory.class);
  private static final class SessionFactoryHolder{

    private static final SessionFactory HOLDER_INSTANCE = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
      try {
        return new Configuration().configure().buildSessionFactory();
      } catch (Throwable e) {
        log.error("Failed to initialize session factory: ", e);
        throw new ExceptionInInitializerError(e);
      }
    }
  }

  public static SessionFactory getSessionFactory(){
    return SessionFactoryHolder.HOLDER_INSTANCE;
  }

  public static void shutdown() {
    SessionFactory sessionFactory = SessionFactoryHolder.HOLDER_INSTANCE;
    if (sessionFactory != null && !sessionFactory.isClosed()) {
      sessionFactory.close();
    }
  }
}
