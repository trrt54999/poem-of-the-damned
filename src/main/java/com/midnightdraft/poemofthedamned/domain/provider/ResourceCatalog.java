package com.midnightdraft.poemofthedamned.domain.provider;

public interface ResourceCatalog {
  enum Css implements ResourceKey{
    GAME_MAIN_MENU,
    GAME_SCENE
  }

  enum Fxml implements ResourceKey{
    GAME_MAIN_MENU,
    GAME_SCENE
  }

  enum AudioSfx implements ResourceKey{
    HOVER,
    SELECT
  }

  enum AudioBgm implements ResourceKey{
    OST,
    RAINDROP_AND_PUDDLES
  }

  enum Backgrounds implements ResourceKey{
    STREET_DAY,
    CLASS_DAY,
    ROOFTOP_DAY
  }

  enum GameCharacters implements ResourceKey{
    HARUKA_LAUGH,
    HARUKA_DIRTY,
    AYA_HAPPY,
    AYA_NORMAL,
    MIO_CAT_SMILE,
    MIO_NORMAL
  }

  enum Fonts implements ResourceKey{
    RIFFIC_FREE_BOLD,
    ALLER_BOLD,
    ALLER_REGULAR
  }

  enum Ui implements ResourceKey{
    CIRCLES,
    LOGO,
    GITHUB_LOGO,
    DIALOGUE_RECTANGLE
  }
}
