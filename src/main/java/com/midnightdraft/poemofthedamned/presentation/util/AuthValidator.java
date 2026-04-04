package com.midnightdraft.poemofthedamned.presentation.util;

import java.util.Optional;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthValidator {

  private final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
      "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
  private final Pattern VALID_PASSWORD_LOWERCASE = Pattern.compile("^(.*[a-z].*)");
  private final Pattern VALID_PASSWORD_UPPERCASE = Pattern.compile("^(.*[A-Z].*)");
  private final Pattern VALID_PASSWORD_DIGIT = Pattern.compile("^(.*\\d.*)");
  private final Pattern VALID_PASSWORD_SPECIAL = Pattern.compile("^(.*[^a-zA-Z0-9].*)");
  private final Pattern VALID_PASSWORD_ENGLISH_ONLY = Pattern.compile("^[\\x21-\\x7E]+$");

  public Optional<String> validateUsername(String username) {
    if (username.isBlank()) {
      return Optional.of("Username cannot be empty!");
    }
    if (username.length() > 6) {
      return Optional.of("Username must contain up to 6 characters!");
    }
    return Optional.empty();
  }

  public Optional<String> validateEmail(String email) {
    if (email.isBlank()) {
      return Optional.of("Email cannot be empty!");
    }
    if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches()) {
      return Optional.of("Email is not valid!");
    }
    return Optional.empty();
  }

  public Optional<String> validatePassword(String password) {
    if (password.isBlank()) {
      return Optional.of("Password cannot be empty!");
    }
    if (password.length() < 6 || password.length() > 30) {
      return Optional.of("Password length must be 6-30 characters!");
    }
    if (!VALID_PASSWORD_ENGLISH_ONLY.matcher(password).matches()) {
      return Optional.of("Password must contain only english letters!");
    }
    if (!VALID_PASSWORD_LOWERCASE.matcher(password).matches()) {
      return Optional.of("Password must contain at least one lowercase letter!");
    }
    if (!VALID_PASSWORD_UPPERCASE.matcher(password).matches()) {
      return Optional.of("Password must contain at least one uppercase letter!");
    }
    if (!VALID_PASSWORD_DIGIT.matcher(password).matches()) {
      return Optional.of("Password must contain at least one digit!");
    }
    if (!VALID_PASSWORD_SPECIAL.matcher(password).matches()) {
      return Optional.of("Password must contain at least one special symbol!");
    }
    return Optional.empty();
  }

  public Optional<String> validateConfirmPassword(String password, String confirm) {
    if (confirm.isBlank()) {
      return Optional.of("Password confirm cannot be empty!");
    }
    if (!password.equals(confirm)) {
      return Optional.of("Password do not match!");
    }
    return Optional.empty();
  }
}
