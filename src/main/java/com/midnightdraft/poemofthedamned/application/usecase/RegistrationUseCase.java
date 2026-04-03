package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.exception.UserAlreadyExistsException;
import com.midnightdraft.poemofthedamned.domain.model.User;
import com.midnightdraft.poemofthedamned.domain.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;

public class RegistrationUseCase {

  private final UserRepository userRepository;

  public RegistrationUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User execute(String username, String email, String password) {
    if (userRepository.existsByEmail(email)) {
      throw new UserAlreadyExistsException("User already exists!");
    }

    return userRepository.save(
        new User(username, email, BCrypt.hashpw(password, BCrypt.gensalt())));
  }
}
