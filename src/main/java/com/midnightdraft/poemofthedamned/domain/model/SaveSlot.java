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
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "save_slots", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"slot_number", "user_id"})
})
@NoArgsConstructor
@AllArgsConstructor
public class SaveSlot extends BaseEntity {

  @Min(value = 1, message = "Slot number must be between 1!")
  @Column(name = "slot_number", nullable = false)
  private Integer slotNumber;

  @NotBlank(message = "Screenshot path cannot be empty!")
  @Column(name = "screenshot_path", nullable = false)
  private String screenshotPath = "assets/ui/default_save_placeholder.png"; // todo не забути додати!

  @NotNull(message = "Play time is required")
  @Column(name = "play_time_seconds", nullable = false)
  private Long playTimeSeconds;

  @OneToMany(mappedBy = "saveSlot", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<SaveFlag> saveFlags = new ArrayList<>();

  @NotNull(message = "User id cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @NotNull(message = "Dialogue id cannot be empty!")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "dialogue_id", nullable = false)
  private Dialogue dialogue;
}
