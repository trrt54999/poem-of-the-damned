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
      return Optional.of("auth.error.username_empty");
    }
    if (username.length() > 6) {
      return Optional.of("auth.error.username_length");
    }
    return Optional.empty();
  }

  public Optional<String> validateEmail(String email) {
    if (email.isBlank()) {
      return Optional.of("auth.error.email_empty");
    }
    if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches()) {
      return Optional.of("auth.error.email_valid");
    }
    return Optional.empty();
  }

  public Optional<String> validatePassword(String password) {
    if (password.isBlank()) {
      return Optional.of("auth.error.password_empty");
    }
    if (password.length() < 6 || password.length() > 30) {
      return Optional.of("auth.error.password_length");
    }
    if (!VALID_PASSWORD_ENGLISH_ONLY.matcher(password).matches()) {
      return Optional.of("auth.error.password_english");
    }
    if (!VALID_PASSWORD_LOWERCASE.matcher(password).matches()) {
      return Optional.of("auth.error.password_lowercase");
    }
    if (!VALID_PASSWORD_UPPERCASE.matcher(password).matches()) {
      return Optional.of("auth.error.password_uppercase");
    }
    if (!VALID_PASSWORD_DIGIT.matcher(password).matches()) {
      return Optional.of("auth.error.password_digit");
    }
    if (!VALID_PASSWORD_SPECIAL.matcher(password).matches()) {
      return Optional.of("auth.error.password_special");
    }
    return Optional.empty();
  }

  public Optional<String> validateConfirmPassword(String password, String confirm) {
    if (confirm.isBlank()) {
      return Optional.of("auth.error.confirm_password_empty");
    }
    if (!password.equals(confirm)) {
      return Optional.of("auth.error.confirm_password_match");
    }
    return Optional.empty();
  }
}
