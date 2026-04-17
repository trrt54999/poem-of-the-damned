package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.App;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeVolumeUseCase {

  public void execute(float musicVolume, float soundVolume) {
    if(musicVolume == App.currentMusicVolume && soundVolume == App.currentSoundVolume) {
      return;
    }

    App.currentMusicVolume = musicVolume;
    App.currentSoundVolume = soundVolume;

    log.info("Change music to: {}, change sound to: {}", musicVolume, soundVolume);
  }
}
