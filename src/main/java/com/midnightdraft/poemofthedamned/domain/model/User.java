package com.midnightdraft.poemofthedamned.domain.model;

import com.midnightdraft.poemofthedamned.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {

  @NotBlank(message = "Name cannot be empty!")
  @Size(min = 1, max = 15, message = "Name must be between 1 and 15 characters!")
  @Column(name = "username", nullable = false)
  private String username;

  @NotBlank(message = "Email cannot be empty!")
  @Email(message = "Incorrect email format!")
  @Column(unique = true, nullable = false)
  private String email;

  @NotBlank(message = "Password cannot be empty!")
  @Column(name = "password_hash", nullable = false)
  private String passwordHash;

  @ManyToMany
  @JoinTable(
      name = "user_unlocked_choices",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "choice_id")
  )
  private Set<Choice> unlockedChoices = new HashSet<>();

  public User(String username, String email, String passwordHash) {
    this.username = username;
    this.email = email;
    this.passwordHash = passwordHash;
  }
}
