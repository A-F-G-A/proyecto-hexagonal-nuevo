package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProductException;
import java.util.Objects;

public record ProductId(String value) {

  public ProductId {
    final String normalizedValue = Objects.requireNonNull(value, "ProductId cannot be null").trim();
    validateNotEmpty(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidProductException.becauseIdIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
