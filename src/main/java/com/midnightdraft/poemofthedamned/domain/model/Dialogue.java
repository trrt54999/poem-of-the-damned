package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import com.midnightdraft.poemofthedamned.domain.engine.SpritePosition;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dialogues")
@NoArgsConstructor
@AllArgsConstructor
public class Dialogue extends BaseEntity {

  @NotBlank(message = "Text cannot be empty!")
  @Column(name = "text", nullable = false, columnDefinition = "TEXT")
  private String text;

  @Column(name = "music_path")
  private String musicPath;

  @Column(name = "ambient_path")
  private String ambientPath;

  @Min(value = 1, message = "Order index must be between 1!")
  @Column(name = "order_index", nullable = false)
  private Integer orderIndex;

  @Enumerated(EnumType.STRING)
  @Column(name = "sprite_position")
  private SpritePosition spritePosition;

  @NotNull(message = "Game scene cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_scene_id", nullable = false)
  private GameScene gameScene;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "character_id")
  private GameCharacter gameCharacter;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "character_sprite_id")
  private GameCharacterSprite gameCharacterSprite;
}
