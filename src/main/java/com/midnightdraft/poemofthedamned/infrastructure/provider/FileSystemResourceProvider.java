package com.midnightdraft.poemofthedamned.infrastructure.provider;

import com.midnightdraft.poemofthedamned.domain.provider.ResourceCatalog.AudioAmbient;
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
import com.midnightdraft.poemofthedamned.infrastructure.exception.ResourceNotFoundException;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileSystemResourceProvider  implements ResourceProvider {

  @Override
  public String getPath(ResourceKey key) {
    return switch (key) {

      case Css css -> switch (css) {
        case GAME_MAIN_MENU -> "/css/game_main_menu.css";
        case GAME_SCENE -> "/css/game_scene.css";
        case AUTH_MENU -> "/css/auth_menu.css";
        case FONT_EN -> "/css/font_en.css";
        case FONT_UK -> "/css/font_uk.css";
      };

      case Fxml fxml -> switch (fxml) {
        case GAME_MAIN_MENU -> "/fxml/game_main_menu.fxml";
        case GAME_SCENE -> "/fxml/game_scene.fxml";
        case AUTH_MENU -> "/fxml/auth_menu.fxml";
      };

      case AudioBgm audioBgm -> switch (audioBgm) {
        case OST -> "/assets/audio/bgm/ost.mp3";
        case RAINDROP_AND_PUDDLES -> "/assets/audio/bgm/raindrop_and_puddles.mp3";
        case SILENCE, FADE_OUT -> null;
      };

      case AudioSfx audioSfx -> switch (audioSfx) {
        case HOVER -> "/assets/audio/sfx/hover.wav";
        case SELECT -> "/assets/audio/sfx/select.wav";
      };

      case AudioAmbient audioAmbient -> switch (audioAmbient) {
        case SCHOOL_BELL -> "/assets/audio/ambient/school_bell.wav";
        case SCHOOL_HALLWAY -> "/assets/audio/ambient/school_hallway.wav";
        case FOOTSTEPS_WOOD -> "/assets/audio/ambient/footsteps_wood.wav";
        case RAIN_WINDOW -> "/assets/audio/ambient/rain_window.wav";
        case SILENCE, FADE_OUT -> null;
      };

      case GameCharacters characters -> switch (characters) {
        case HARUKA_LAUGH -> "/assets/characters/haruka/haruka_laugh.png";
        case HARUKA_DIRTY -> "/assets/characters/haruka/haruka_dirty.png";
        case HARUKA_SMILE -> "/assets/characters/haruka/haruka_smile.png";
        case HARUKA_ANNOYED -> "/assets/characters/haruka/haruka_annoyed.png";
        case AYA_HAPPY -> "/assets/characters/aya/aya_happy.png";
        case AYA_NORMAL -> "/assets/characters/aya/aya_normal.png";
        case AYA_CURIOUS -> "/assets/characters/aya/aya_curious.png";
        case MIO_CAT_SMILE -> "/assets/characters/mio/mio_cat_smile.png";
        case MIO_NORMAL -> "/assets/characters/mio/mio_normal.png";
      };

      case Fonts fonts -> switch (fonts) {
        case RIFFIC_FREE_BOLD -> "/assets/fonts/riffic_free_bold.ttf";
        case ALLER_BOLD -> "/assets/fonts/aller_bold.ttf";
        case ALLER_REGULAR -> "/assets/fonts/aller_regular.ttf";
        case NUNITO_BLACK -> "/assets/fonts/nunito_black.ttf";
        case UBUNTU_BOLD -> "/assets/fonts/ubuntu_bold.ttf";
        case INTER_MEDIUM -> "/assets/fonts/inter_medium.otf";
      };

      case Backgrounds backgrounds -> switch (backgrounds) {
        case CLASS_DAY -> "/assets/backgrounds/class/class_day.jpg";
        case CLASS_EVE -> "/assets/backgrounds/class/class_eve.jpg";
        case ROOFTOP_DAY -> "/assets/backgrounds/rooftop/rooftop_day.jpg";
        case STREET_DAY -> "/assets/backgrounds/street/street_day.jpg";
        case STREET_EVE -> "/assets/backgrounds/street/street_eve.jpg";
        case ROOM_NIGHT -> "/assets/backgrounds/room/room_night.jpg";
      };

      case Ui ui -> switch (ui) {
        case CIRCLES -> "/assets/ui/circles.png";
        case LOGO -> "/assets/ui/logo.jpg";
        case GITHUB_LOGO -> "/assets/ui/github_logo.png";
        case DIALOGUE_RECTANGLE -> "/assets/ui/dialogue_rectangle.png";
        case LOGIN_TAB_ICON -> "/assets/ui/login_tab_icon.png";
        case REGISTRATION_TAB_ICON -> "/assets/ui/registration_tab_icon.png";
        case USERNAME_ICON -> "/assets/ui/username_icon.png";
        case EMAIL_ICON -> "/assets/ui/email_icon.png";
        case PASSWORD_ICON -> "/assets/ui/password_icon.png";
        case CONFIRM_PASSWORD_ICON -> "/assets/ui/confirm_password_icon.png";
        case GOOGLE_ICON -> "/assets/ui/google_icon.png";
      };
    };
  }

  @Override
  public URL getUrl(ResourceKey key) {
    String path = getPath(key);

    URL url = getClass().getResource(path);

    if (url == null) {
      log.error("File not found! Key: [{}], Path: [{}]", key, path);
      throw new ResourceNotFoundException(
          String.format("File not found! Key: [%s], Path: [%s]", key, path)
      );
    }
    return url;
  }
}
