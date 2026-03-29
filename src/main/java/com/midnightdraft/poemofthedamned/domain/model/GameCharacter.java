package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;
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
public class GameCharacter extends BaseEntity {

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "character_translations", joinColumns = @JoinColumn(name = "character_id"))
  @MapKeyColumn(name = "locale")
  @Column(name = "translated_name", nullable = false)
  private Map<String, String> translationNames = new HashMap<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "description_translations", joinColumns = @JoinColumn(name = "character_id"))
  @MapKeyColumn(name = "locale")
  @Column(name = "translated_description", columnDefinition = "TEXT", nullable = false)
  private Map<String, String> translations = new HashMap<>();

  @NotBlank(message = "Character name cannot be empty!")
  @Size(min = 2, max = 15, message = "Name must be between 2 and 15 characters!")
  @Column(name = "name", unique = true, nullable = false)
  private String name;

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;
}
