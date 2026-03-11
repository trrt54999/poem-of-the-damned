package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "achievements")
@NoArgsConstructor
@AllArgsConstructor
public class Achievement extends BaseEntity {

  @NotBlank(message = "Title cannot be empty!")
  @Column(name = "title", unique = true, nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @NotBlank(message = "Icon path cannot be empty!")
  @Column(name = "icon_path", nullable = false)
  private String iconPath;

  @NotNull(message = "Is hidden cannot be null!")
  @Column(name = "is_hidden", nullable = false)
  private Boolean isHidden;
}
