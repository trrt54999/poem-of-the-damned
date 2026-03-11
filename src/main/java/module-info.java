module com.midnightdraft.poemofthedamned {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;
  requires jakarta.persistence;
  requires static lombok;
  requires org.hibernate.orm.core;
  requires jakarta.validation;

  opens com.midnightdraft.poemofthedamned to javafx.fxml;
  exports com.midnightdraft.poemofthedamned;
}