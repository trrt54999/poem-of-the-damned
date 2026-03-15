package com.midnightdraft.poemofthedamned.presentation.util;

import javafx.scene.media.AudioClip;
import java.net.URL;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SoundManager {

  public AudioClip loadSound(String path, double volume){
    URL url = SoundManager.class.getResource(path);

    if (url == null) {
      System.err.println("Cannot find audio file: " + path);
      return null;
    }

    AudioClip clip = new AudioClip(url.toExternalForm());
    clip.setVolume(volume);
    return clip;
  }
}