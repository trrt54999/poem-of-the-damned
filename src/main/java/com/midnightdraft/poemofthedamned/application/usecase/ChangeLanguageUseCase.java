package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.App;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChangeLanguageUseCase {

  public void execute(String language){
    App.currentLang = language;

    log.info("App change language to: {}", language);
  }
}
