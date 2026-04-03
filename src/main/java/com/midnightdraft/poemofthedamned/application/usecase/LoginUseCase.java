package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.exception.InvalidCredentialsException;
import com.midnightdraft.poemofthedamned.domain.model.User;
import com.midnightdraft.poemofthedamned.domain.repository.UserRepository;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;

public class LoginUseCase {

  private final UserRepository userRepository;

  public LoginUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User execute(String email, String password) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new InvalidCredentialsException("User not found!"));

    if (!BCrypt.checkpw(password, user.getPasswordHash())) {
      throw new InvalidCredentialsException("User not found!");
    }

    return user;
  }
}
