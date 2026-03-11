package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_achievements", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "achievement_id"})
})
@NoArgsConstructor
@AllArgsConstructor
public class UserAchievement extends BaseEntity {

  @NotNull(message = "User cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotNull(message = "Achievement cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "achievement_id", nullable = false)
  private Achievement achievement;
}
