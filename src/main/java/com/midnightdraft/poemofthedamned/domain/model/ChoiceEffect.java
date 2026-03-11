package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "choice_effects")
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceEffect extends BaseEntity {

  @NotBlank(message = "New value cannot be empty!")
  @Column(name = "new_value", nullable = false)
  private String newValue;

  @NotNull(message = "Choice cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "choice_id", nullable = false)
  private Choice choice;

  @NotNull(message = "Flag cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flag_id", nullable = false)
  private Flag flag;
}
