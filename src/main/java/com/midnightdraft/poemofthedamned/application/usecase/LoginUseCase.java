  package com.midnightdraft.poemofthedamned.application.usecase;

  import com.midnightdraft.poemofthedamned.application.dto.UserAuthDTO;
  import com.midnightdraft.poemofthedamned.application.dto.UserLoginDTO;
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

    public UserAuthDTO execute(UserLoginDTO userLoginDTO) {
      User user = userRepository.findByEmail(userLoginDTO.email()).orElseThrow(() -> {
        log.warn("Failed login attempt");
        return new InvalidCredentialsException("User not found!");
      });

      if (!BCrypt.checkpw(userLoginDTO.password(), user.getPasswordHash())) {
        log.warn("Failed login attempt");
        throw new InvalidCredentialsException("User not found!");
      }

      log.info("User successfully logged in");
      return new UserAuthDTO(user.getId(), user.getUsername(), user.getEmail());
    }
  }
