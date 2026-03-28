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
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
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

  @Column(name = "description", columnDefinition = "TEXT")
  private String description;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "achievement_title_translations",
      joinColumns = @JoinColumn(name = "achievement_id"))
  @MapKeyColumn(name = "locale")
  @Column(name = "translated_achievement_title", nullable = false)
  private Map<String, String> translationTitles = new HashMap<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "achievement_description_translations",
      joinColumns = @JoinColumn(name = "achievement_id"))
  @MapKeyColumn(name = "locale")
  @Column(name = "translated_achievement_description", columnDefinition = "TEXT", nullable = false)
  private Map<String, String> translationDescriptions = new HashMap<>();

  @NotBlank(message = "Icon path cannot be empty!")
  @Column(name = "icon_path", nullable = false)
  private String iconPath;

  @NotNull(message = "Is hidden cannot be null!")
  @Column(name = "is_hidden", nullable = false)
  private Boolean isHidden;
}
