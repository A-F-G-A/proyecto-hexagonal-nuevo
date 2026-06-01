package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProductException;
import java.util.Objects;

public record ProductDescription(String value) {

  public ProductDescription {
    final String normalizedValue = Objects.requireNonNull(value, "ProductDescription cannot be null").trim();
    validateNotEmpty(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidProductException.becauseDescriptionIsEmpty();
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
