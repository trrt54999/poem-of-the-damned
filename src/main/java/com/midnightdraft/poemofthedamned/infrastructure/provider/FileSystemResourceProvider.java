package com.midnightdraft.poemofthedamned.infrastructure.provider;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioSfx;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Css;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fonts;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Fxml;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.GameCharacters;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Ui;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceKey;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceProvider;
import java.net.URL;

public class FileSystemResourceProvider  implements ResourceProvider {

  @Override
  public String getPath(ResourceKey key) {
    return switch (key){

      case Css css -> switch (css){
        case GAME_MAIN_MENU -> "/css/game-main-menu.css";
      };

      case Fxml fxml -> switch (fxml){
        case GAME_MAIN_MENU -> "/fxml/GameMainMenu.fxml";
      };

      case AudioSfx audioSfx -> switch (audioSfx){
        case HOVER -> "/assets/audio/sfx/hover.wav";
        case SELECT -> "/assets/audio/sfx/select.wav";
      };

      case GameCharacters characters -> switch (characters){
        case HARUKA_LAUGH -> "/assets/characters/haruka/haruka_laugh.png";
        case AYA_HAPPY -> "/assets/characters/aya/aya_happy.png";
        case MIO_CAT_SMILE -> "/assets/characters/mio/mio_catsmile.png";
      };

      case Fonts fonts -> switch (fonts) {
        case RIFFIC_FREE_BOLD -> "/assets/fonts/RifficFree-Bold.ttf";
      };

      case Ui ui -> switch (ui){
        case CIRCLES -> "/assets/ui/Circles.png";
        case LOGO -> "/assets/ui/logo.jpg";
      };

      default -> throw new IllegalArgumentException("Unknown asset group: " + key.getClass());
    };
  }

  @Override
  public URL getUrl(ResourceKey key) {
    String path = getPath(key);

    URL url = getClass().getResource(path);

    if (url == null) {
      throw new RuntimeException(
          String.format("File not found! Key: [%s], Path: [%s]", key, path)
      );
    }
    return url;
  }
}
