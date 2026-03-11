package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
    name = "character_sprites",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"character_id", "emotion"})
    }
)
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSprite extends BaseEntity {

  @NotBlank(message = "Emotion cannot be empty!")
  @Column(name = "emotion", nullable = false)
  private String emotion;

  @NotBlank(message = "Sprite path cannot be empty!")
  @Column(name = "sprite_path", nullable = false)
  private String spritePath;

  @NotNull(message = "Character id cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "character_id", nullable = false)
  private Character character;
}
