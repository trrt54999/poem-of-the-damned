package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.dto.UserAuthDTO;
import com.midnightdraft.poemofthedamned.application.dto.UserRegistrationDTO;
import com.midnightdraft.poemofthedamned.application.exception.UserAlreadyExistsException;
import com.midnightdraft.poemofthedamned.domain.model.User;
import com.midnightdraft.poemofthedamned.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

@Slf4j
public class RegistrationUseCase {

  private final UserRepository userRepository;

  public RegistrationUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserAuthDTO execute(UserRegistrationDTO userRegistrationDTO) {
    if (userRepository.existsByEmail(userRegistrationDTO.email())) {
      log.warn("Registration blocked: email already exists");
      throw new UserAlreadyExistsException("User already exists!");
    }

    log.info("User successfully created");
    User savedUser = userRepository.save(
        new User(userRegistrationDTO.username(), userRegistrationDTO.email(),
            BCrypt.hashpw(userRegistrationDTO.password(), BCrypt.gensalt())));

    return new UserAuthDTO(savedUser.getUsername(), savedUser.getEmail());
  }
}
