package com.jcaa.usersmanagement.domain.exception;

public final class ProductNotFoundException extends DomainException {

  private static final String MESSAGE_BY_ID = "The product with id '%s' was not found.";

  private ProductNotFoundException(final String message) {
    super(message);
  }

  public static ProductNotFoundException becauseIdWasNotFound(final String productId) {
    return new ProductNotFoundException(String.format(MESSAGE_BY_ID, productId));
  }
}
