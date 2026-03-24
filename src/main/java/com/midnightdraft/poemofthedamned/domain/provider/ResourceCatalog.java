package com.midnightdraft.poemofthedamned.domain.provider;

public interface ResourceCatalog {
  enum Css implements ConfigKey {
    GAME_MAIN_MENU,
    GAME_SCENE
  }

  enum Fxml implements ConfigKey {
    GAME_MAIN_MENU,
    GAME_SCENE
  }

  enum Fonts implements ConfigKey {
    RIFFIC_FREE_BOLD,
    ALLER_BOLD,
    ALLER_REGULAR
  }

  enum AudioSfx implements AudioKey {
    HOVER,
    SELECT
  }

  enum AudioBgm implements AudioKey {
    OST,
    RAINDROP_AND_PUDDLES,
    SILENCE
  }

  enum Backgrounds implements VisualKey {
    STREET_DAY,
    CLASS_DAY,
    ROOFTOP_DAY
  }

  enum GameCharacters implements VisualKey {
    HARUKA_LAUGH,
    HARUKA_DIRTY,
    AYA_HAPPY,
    AYA_NORMAL,
    MIO_CAT_SMILE,
    MIO_NORMAL
  }

  enum Ui implements VisualKey {
    CIRCLES,
    LOGO,
    GITHUB_LOGO,
    DIALOGUE_RECTANGLE
  }
}
