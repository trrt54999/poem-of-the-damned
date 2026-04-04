package com.midnightdraft.poemofthedamned.domain.provider;

public interface ResourceCatalog {
  enum Css implements ConfigKey {
    GAME_MAIN_MENU,
    GAME_SCENE,
    AUTH_MENU,
    FONT_EN,
    FONT_UK
  }

  enum Fxml implements ConfigKey {
    GAME_MAIN_MENU,
    GAME_SCENE,
    AUTH_MENU
  }

  enum Fonts implements ConfigKey {
    RIFFIC_FREE_BOLD,
    ALLER_BOLD,
    ALLER_REGULAR,
    NUNITO_BLACK,
    UBUNTU_BOLD,
    INTER_MEDIUM
  }

  enum AudioSfx implements AudioKey {
    HOVER,
    SELECT
  }

  enum AudioBgm implements AudioKey {
    OST,
    RAINDROP_AND_PUDDLES,
    SILENCE,
    FADE_OUT
  }
  
  enum AudioAmbient implements AudioKey {
    RAIN_WINDOW,
    FOOTSTEPS_WOOD,
    SCHOOL_BELL,
    SCHOOL_HALLWAY,
    SILENCE,
    FADE_OUT
  }

  enum Backgrounds implements VisualKey {
    CLASS_DAY,
    CLASS_EVE,
    ROOFTOP_DAY,
    STREET_DAY,
    STREET_EVE,
    ROOM_NIGHT
  }

  enum GameCharacters implements VisualKey {
    HARUKA_LAUGH,
    HARUKA_DIRTY,
    HARUKA_SMILE,
    HARUKA_ANNOYED,
    AYA_HAPPY,
    AYA_NORMAL,
    AYA_CURIOUS,
    MIO_CAT_SMILE,
    MIO_NORMAL
  }

  enum Ui implements VisualKey {
    CIRCLES,
    LOGO,
    GITHUB_LOGO,
    DIALOGUE_RECTANGLE,
    LOGIN_TAB_ICON,
    REGISTRATION_TAB_ICON,
    USERNAME_ICON,
    EMAIL_ICON,
    PASSWORD_ICON,
    CONFIRM_PASSWORD_ICON,
    GOOGLE_ICON,
    EYE_ON_ICON,
    EYE_OFF_ICON,
    FIELD_REFUSE_ICON,
    FIELD_SUCCESS_ICON
  }
}
