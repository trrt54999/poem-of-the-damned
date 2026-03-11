package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "flags")
@NoArgsConstructor
@AllArgsConstructor
public class Flag extends BaseEntity {

  @NotBlank(message = "Flag name cannot be empty!")
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "description")
  private String description;
}
