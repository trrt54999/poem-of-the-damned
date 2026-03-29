package com.midnightdraft.poemofthedamned.presentation.controller;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import com.midnightdraft.poemofthedamned.infrastructure.provider.FileSystemResourceProvider;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthMenuController {

  @FXML
  private StackPane rootPane;

  private final ResourceProvider resourceProvider = new FileSystemResourceProvider();

  @FXML
  public void initialize() {
    loadResources();
  }

  private void loadResources() {
    String css = resourceProvider.getUrl(Css.AUTH_MENU).toExternalForm();
    rootPane.getStylesheets().add(css);
  }
}
