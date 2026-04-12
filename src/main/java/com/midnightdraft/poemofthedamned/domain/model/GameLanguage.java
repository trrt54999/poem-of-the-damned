package com.midnightdraft.poemofthedamned.domain.model;

import lombok.Getter;

@Getter
public enum GameLanguage {
  EN("English", "en"),
  UK("Українська", "uk");

  private final String displayName;
  private final String code;

  GameLanguage(String displayName, String code) {
    this.displayName = displayName;
    this.code = code;
  }

  @Override
  public String toString() {
    return displayName;
  }
}
