package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProductException;
import java.util.Objects;

public record ProductName(String value) {

  public ProductName {
    final String normalizedValue = Objects.requireNonNull(value, "ProductName cannot be null").trim();
    validateNotEmpty(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidProductException.becauseNameIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
