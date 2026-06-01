package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProductException extends DomainException {

  private static final String MESSAGE_INVALID_PRICE = "The product price must be greater than zero.";
  private static final String MESSAGE_INVALID_NAME = "The product name must not be empty.";

  private InvalidProductException(final String message) {
    super(message);
  }

  public static InvalidProductException becausePriceIsInvalid() {
    return new InvalidProductException(MESSAGE_INVALID_PRICE);
  }

  public static InvalidProductException becauseNameIsEmpty() {
    return new InvalidProductException(MESSAGE_INVALID_NAME);
  }
}
