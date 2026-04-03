package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.domain.model.User;
import com.midnightdraft.poemofthedamned.domain.repository.UserRepository;

public class RegistrationUseCase {

  private final UserRepository userRepository;

  public RegistrationUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // todo implement password hash
  public User execute(String username, String email, String passwordHash) {
    return userRepository.save(new User(username, email, passwordHash));
  }
}
