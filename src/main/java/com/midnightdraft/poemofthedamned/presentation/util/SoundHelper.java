package com.midnightdraft.poemofthedamned.presentation.util;

import com.midnightdraft.poemofthedamned.App;
import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class SoundHelper {

  public AudioClip loadSoundEffect(String path) {
    URL url = SoundHelper.class.getResource(path);
    if (url == null) {
      log.error("Sound effect not found: {}", path);
      return null;
    }
    AudioClip clip = new AudioClip(url.toExternalForm());
    clip.setVolume(App.currentSoundVolume);
    return clip;
  }

  public MediaPlayer createBackgroundMusic(String fullUrl) {
    try {
      Media media = new Media(fullUrl);
      MediaPlayer player = new MediaPlayer(media);
      player.setVolume(App.currentMusicVolume);
      return player;
    } catch (Exception e) {
      log.error("Failed to load background music: {}", fullUrl, e);
      return null;
    }
  }
}
