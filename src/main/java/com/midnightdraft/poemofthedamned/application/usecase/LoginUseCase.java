package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.exception.InvalidCredentialsException;
import com.midnightdraft.poemofthedamned.domain.model.User;
import com.midnightdraft.poemofthedamned.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

@Slf4j
public class LoginUseCase {

  private final UserRepository userRepository;

  public LoginUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User execute(String email, String password) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> {
      log.warn("Failed login attempt");
      return new InvalidCredentialsException("User not found!");
    });

    if (!BCrypt.checkpw(password, user.getPasswordHash())) {
      log.warn("Failed login attempt");
      throw new InvalidCredentialsException("User not found!");
    }

    log.info("User successfully logged in");
    return user;
  }
}
