package com.midnightdraft.poemofthedamned.infrastructure.provider;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioBgm;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioSfx;
import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.Backgrounds;
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
        case GAME_MAIN_MENU -> "/css/game_main_menu.css";
        case GAME_SCENE -> "/css/game_scene.css";
      };

      case Fxml fxml -> switch (fxml){
        case GAME_MAIN_MENU -> "/fxml/game_main_menu.fxml";
        case GAME_SCENE -> "/fxml/game_scene.fxml";
      };

      case AudioBgm audioBgm -> switch (audioBgm){
        case OST -> "/assets/audio/bgm/ost.mp3";
        case RAINDROP_AND_PUDDLES -> "/assets/audio/bgm/raindrop_and_puddles.mp3";
      };

      case AudioSfx audioSfx -> switch (audioSfx){
        case HOVER -> "/assets/audio/sfx/hover.wav";
        case SELECT -> "/assets/audio/sfx/select.wav";
      };

      case GameCharacters characters -> switch (characters){
        case HARUKA_LAUGH -> "/assets/characters/haruka/haruka_laugh.png";
        case HARUKA_DIRTY -> "/assets/characters/haruka/haruka_dirty.png";
        case AYA_HAPPY -> "/assets/characters/aya/aya_happy.png";
        case AYA_NORMAL -> "/assets/characters/aya/aya_normal.png";
        case MIO_CAT_SMILE -> "/assets/characters/mio/mio_cat_smile.png";
        case MIO_NORMAL -> "/assets/characters/mio/mio_normal.png";
      };

      case Fonts fonts -> switch (fonts) {
        case RIFFIC_FREE_BOLD -> "/assets/fonts/riffic_free_bold.ttf";
        case ALLER_BOLD -> "/assets/fonts/aller_bold.ttf";
        case ALLER_REGULAR -> "/assets/fonts/aller_regular.ttf";
      };

      case Backgrounds backgrounds -> switch (backgrounds){
        case STREET_DAY -> "/assets/backgrounds/street/street_day.jpg";
        case CLASS_DAY -> "/assets/backgrounds/class/class_day.jpg";
        case ROOFTOP_DAY -> "/assets/backgrounds/rooftop/rooftop_day.jpg";
      };

      case Ui ui -> switch (ui){
        case CIRCLES -> "/assets/ui/circles.png";
        case LOGO -> "/assets/ui/logo.jpg";
        case GITHUB_LOGO -> "/assets/ui/github_logo.png";
        case DIALOGUE_RECTANGLE -> "/assets/ui/dialogue_rectangle.png";
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
