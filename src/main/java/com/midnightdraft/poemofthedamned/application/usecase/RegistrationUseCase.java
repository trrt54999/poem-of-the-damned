package com.midnightdraft.poemofthedamned.application.usecase;

import com.midnightdraft.poemofthedamned.application.dto.ClientSettingsDTO;
import com.midnightdraft.poemofthedamned.application.dto.UserAuthDTO;
import com.midnightdraft.poemofthedamned.application.dto.UserRegistrationDTO;
import com.midnightdraft.poemofthedamned.application.exception.UserAlreadyExistsException;
import com.midnightdraft.poemofthedamned.domain.model.ClientSettings;
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
    User newUser = new User(userRegistrationDTO.username(), userRegistrationDTO.email(),
        BCrypt.hashpw(userRegistrationDTO.password(), BCrypt.gensalt()));

    ClientSettingsDTO def = ClientSettingsDTO.defaults();

    ClientSettings defaultSettings = new ClientSettings(def.screenResolution(), def.gameLanguage(),
        def.musicVolume(), def.soundVolume(), def.isFullScreen());
    newUser.setSettings(defaultSettings);

    User savedUser = userRepository.save(newUser);

    return new UserAuthDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
  }
}
