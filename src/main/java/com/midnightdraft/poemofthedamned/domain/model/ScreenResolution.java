package com.midnightdraft.poemofthedamned.domain.model;

import lombok.Getter;

@Getter
public enum ScreenResolution {
  HD(1280, 720),
  FULL_HD(1920, 1080),
  Q_HD(2560, 1440),
  U_HD(3840, 2160);

  private final int width;
  private final int height;

  ScreenResolution(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public String toString() {
    return width + "x" + height;
  }
}
