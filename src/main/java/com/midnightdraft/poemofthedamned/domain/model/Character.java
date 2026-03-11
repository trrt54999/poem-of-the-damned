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
@Table(name = "characters")
@NoArgsConstructor
@AllArgsConstructor
public class Character extends BaseEntity {

  @NotBlank(message = "Character name cannot be empty!")
  @Size(min = 2, max = 15, message = "Name must be between 2 and 15 characters!")
  @Column(name = "name",unique = true, nullable = false)
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;
}
