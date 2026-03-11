module com.midnightdraft.poemofthedamned {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.graphics;

  opens com.midnightdraft.poemofthedamned to javafx.fxml;
  exports com.midnightdraft.poemofthedamned;
}