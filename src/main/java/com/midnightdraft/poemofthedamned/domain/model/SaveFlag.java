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
@Table(name = "save_flags", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"save_slot_id", "flag_id"})
})
@NoArgsConstructor
@AllArgsConstructor
public class SaveFlag extends BaseEntity {

  @NotBlank(message = "Value cannot be empty!")
  @Column(name = "value", nullable = false)
  private String value;

  @NotNull(message = "Save slot cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "save_slot_id", nullable = false)
  private SaveSlot saveSlot;

  @NotNull(message = "Flag cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "flag_id", nullable = false)
  private Flag flag;
}
