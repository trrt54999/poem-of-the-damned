package com.midnightdraft.poemofthedamned.infrastructure.util;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

@UtilityClass
public class HibernateSessionFactory {

  @Getter
  private final SessionFactory sessionFactory = buildSessionFactory();

  public SessionFactory buildSessionFactory(){
    try {
      return new Configuration().configure().buildSessionFactory();
    } catch (Throwable e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public void shutdown() {
    if (sessionFactory != null) {
      sessionFactory.close();
    }
  }
}
