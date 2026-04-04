package com.midnightdraft.poemofthedamned.infrastructure.exception;

public class RepositoryException extends RuntimeException {

  public RepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public static class EntitySaveException extends RepositoryException {

    public EntitySaveException(String entityName, Throwable cause) {
      super("Failed to save entity: " + entityName, cause);
    }
  }

  public static class EntityUpdateException extends RepositoryException {

    public EntityUpdateException(String entityName, Throwable cause) {
      super("Failed to update entity: " + entityName, cause);
    }
  }

  public static class EntityDeleteException extends RepositoryException {

    public EntityDeleteException(String entityName, Throwable cause) {
      super("Failed to delete entity: " + entityName, cause);
    }
  }

  public static class EntityFetchException extends RepositoryException {

    public EntityFetchException(String entityName, Throwable cause) {
      super("Failed to fetch entity: " + entityName, cause);
    }
  }
}
