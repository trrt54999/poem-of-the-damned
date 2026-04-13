package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.App;

public class ChangeVolumeUseCase {

  public void execute(float musicVolume, float soundVolume) {
    App.currentMusicVolume = musicVolume;
    App.currentSoundVolume = soundVolume;
  }
}
