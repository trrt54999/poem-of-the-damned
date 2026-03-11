package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "game_scenes")
@NoArgsConstructor
@AllArgsConstructor
public class GameScene extends BaseEntity {

  @NotBlank(message = "Title cannot be empty!")
  @Size(min = 2, max = 20, message = "Title size must be between 2 and 20 characters!")
  @Column(name = "title", unique = true, nullable = false)
  private String title;

  @NotBlank(message = "Background path cannot be empty!")
  @Column(name = "background_path", nullable = false)
  private String backgroundPath;

  @Column(name = "soundtrack_path")
  private String soundtrackPath;
}
