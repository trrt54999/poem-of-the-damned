package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "client_settings")
@NoArgsConstructor
@AllArgsConstructor
public class ClientSettings extends BaseEntity {

  @NotNull(message = "Screen resolution cannot be null!")
  @Enumerated(EnumType.STRING)
  @Column(name = "screen_resolution", nullable = false)
  private ScreenResolution screenResolution;

  @NotNull(message = "Game language cannot be null!")
  @Enumerated(EnumType.STRING)
  @Column(name = "game_language", nullable = false)
  private GameLanguage gameLanguage;

  @NotNull(message = "Music volume cannot be null!")
  @DecimalMin("0.0")
  @DecimalMax("1.0")
  @Column(name = "music_volume", nullable = false)
  private Float musicVolume;

  @NotNull(message = "Sound volume cannot be null!")
  @DecimalMin("0.0")
  @DecimalMax("1.0")
  @Column(name = "sound_volume", nullable = false)
  private Float soundVolume;

  @NotNull(message = "Fullscreen cannot be null!")
  @Column(name = "is_fullscreen", nullable = false)
  private Boolean isFullScreen;

  @OneToOne(mappedBy = "settings")
  private User user;
}
