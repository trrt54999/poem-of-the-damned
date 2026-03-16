package com.midnightdraft.poemofthedamned.domain.provider;

public interface ResourceCatalog {
  enum Css implements ResourceKey{
    GAME_MAIN_MENU
  }

  enum Fxml implements ResourceKey{
    GAME_MAIN_MENU
  }

  enum AudioSfx implements ResourceKey{
    HOVER,
    SELECT
  }

  enum AudioBgm implements ResourceKey{

  }

  enum Backgrounds implements ResourceKey{

  }

  enum GameCharacters implements ResourceKey{
    HARUKA_LAUGH,
    AYA_HAPPY,
    MIO_CAT_SMILE
  }

  enum Fonts implements ResourceKey{
    RIFFIC_FREE_BOLD
  }

  enum Ui implements ResourceKey{
    CIRCLES,
    LOGO
  }

}
