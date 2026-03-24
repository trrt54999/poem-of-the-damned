package com.midnightdraft.poemofthedamned.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
