package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

public final class PersistenceException extends RuntimeException {

  private static final String MESSAGE_SAVE_USER = "Failed to save user with ID: '%s'.";
  private static final String MESSAGE_UPDATE_USER = "Failed to update user with ID: '%s'.";
  private static final String MESSAGE_FIND_USER = "Failed to find user with ID: '%s'.";
  private static final String MESSAGE_EMAIL = "Failed to find user with email: '%s'.";
  private static final String MESSAGE_ALL_USERS = "Failed to retrieve all users.";
  private static final String MESSAGE_DELETE_USER = "Failed to delete user with ID: '%s'.";

  private static final String MESSAGE_SAVE_PRODUCT = "Failed to save product with ID: '%s'.";
  private static final String MESSAGE_UPDATE_PRODUCT = "Failed to update product with ID: '%s'.";
  private static final String MESSAGE_FIND_PRODUCT = "Failed to find product with ID: '%s'.";
  private static final String MESSAGE_ALL_PRODUCTS = "Failed to retrieve all products.";
  private static final String MESSAGE_DELETE_PRODUCT = "Failed to delete product with ID: '%s'.";
  private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

  private PersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE_USER, userId), cause);
  }

  public static PersistenceException becauseUpdateFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE_USER, userId), cause);
  }

  public static PersistenceException becauseFindByIdFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND_USER, userId), cause);
  }

  public static PersistenceException becauseFindByEmailFailed(
      final String email, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_EMAIL, email), cause);
  }

  public static PersistenceException becauseFindAllFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL_USERS, cause);
  }

  public static PersistenceException becauseDeleteFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE_USER, userId), cause);
  }

  public static PersistenceException becauseSaveProductFailed(final String productId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_SAVE_PRODUCT, productId), cause);
  }

  public static PersistenceException becauseUpdateProductFailed(
      final String productId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_UPDATE_PRODUCT, productId), cause);
  }

  public static PersistenceException becauseFindProductByIdFailed(
      final String productId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_FIND_PRODUCT, productId), cause);
  }

  public static PersistenceException becauseFindAllProductsFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_ALL_PRODUCTS, cause);
  }

  public static PersistenceException becauseDeleteProductFailed(
      final String productId, final Throwable cause) {
    return new PersistenceException(String.format(MESSAGE_DELETE_PRODUCT, productId), cause);
  }

  public static PersistenceException becauseConnectionFailed(final Throwable cause) {
    return new PersistenceException(MESSAGE_CONNECTION, cause);
  }
}
