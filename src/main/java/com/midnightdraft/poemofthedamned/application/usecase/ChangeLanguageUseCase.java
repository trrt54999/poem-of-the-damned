package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.App;

public class ChangeLanguageUseCase {

  public void execute(String language){
    App.currentLang = language;
  }
}
