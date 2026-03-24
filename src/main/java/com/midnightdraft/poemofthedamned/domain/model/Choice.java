package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "choices")
@NoArgsConstructor
@AllArgsConstructor
public class Choice extends BaseEntity {

  @NotBlank(message = "Сhoice text cannot be empty!")
  @Column(name = "choice_text", nullable = false)
  private String choiceText;

  @Min(value = 1, message = "Order index must be between 1!")
  @Column(name = "order_index", nullable = false)
  private Integer orderIndex;

  @Column(name = "required_flag_value")
  private String requiredFlagValue;

  @OneToMany(mappedBy = "choice", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ChoiceEffect> choiceEffects = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "required_flag_id")
  private Flag requiredFlag;

  @NotNull(message = "Game scene cannot be null!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "game_scene_id", nullable = false)
  private GameScene gameScene;

  @NotNull(message = "Next game scene cannot be null!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "next_game_scene_id")
  private GameScene nextGameScene;
}
