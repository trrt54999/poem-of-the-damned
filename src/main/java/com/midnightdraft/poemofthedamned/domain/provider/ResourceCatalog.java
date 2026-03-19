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

  }

  enum Backgrounds implements ResourceKey{
    CLASS_DAY,
    ROOFTOP_DAY
  }

  enum GameCharacters implements ResourceKey{
    HARUKA_LAUGH,
    AYA_HAPPY,
    MIO_CAT_SMILE
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
