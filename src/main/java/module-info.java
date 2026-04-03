module com.midnightdraft.poemofthedamned {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires jakarta.persistence;
  requires static lombok;
  requires org.hibernate.orm.core;
  requires jakarta.validation;
  requires java.naming;
  requires java.sql;
  requires jakarta.el;
  requires javafx.media;
  requires org.slf4j;
  requires org.apache.logging.log4j;
  requires org.apache.logging.log4j.core;
  requires jbcrypt;

  opens com.midnightdraft.poemofthedamned to javafx.fxml;
  exports com.midnightdraft.poemofthedamned;

  opens com.midnightdraft.poemofthedamned.domain to org.hibernate.orm.core;
  opens com.midnightdraft.poemofthedamned.domain.model to org.hibernate.orm.core, org.hibernate.validator;
  opens com.midnightdraft.poemofthedamned.presentation.controller to javafx.fxml;
  opens com.midnightdraft.poemofthedamned.infrastructure.repository.impl to org.hibernate.orm.core;
  opens com.midnightdraft.poemofthedamned.domain.repository to org.hibernate.orm.core;
}
