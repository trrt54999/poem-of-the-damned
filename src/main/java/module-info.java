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

  opens com.midnightdraft.poemofthedamned to javafx.fxml;
  exports com.midnightdraft.poemofthedamned;

  opens com.midnightdraft.poemofthedamned.domain to org.hibernate.orm.core;
  opens com.midnightdraft.poemofthedamned.domain.model to org.hibernate.orm.core, org.hibernate.validator;
}
