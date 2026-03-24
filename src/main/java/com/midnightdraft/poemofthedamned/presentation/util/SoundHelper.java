package com.midnightdraft.poemofthedamned.presentation.util;

import javafx.scene.media.AudioClip;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class SoundHelper {

  public AudioClip loadSoundEffect(String path, double volume) {
    URL url = SoundHelper.class.getResource(path);
    if (url == null) {
      log.warn("Sound effect not found: {}", path);
      return null;
    }
    AudioClip clip = new AudioClip(url.toExternalForm());
    clip.setVolume(volume);
    return clip;
  }

  public MediaPlayer createBackgroundMusic(String fullUrl, double volume) {
    try {
      Media media = new Media(fullUrl);
      MediaPlayer player = new MediaPlayer(media);
      player.setVolume(volume);
      return player;
    } catch (Exception e) {
      log.warn("Background music not found: {}", fullUrl);
      return null;
    }
  }
}
