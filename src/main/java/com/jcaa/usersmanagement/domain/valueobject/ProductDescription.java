package com.jcaa.usersmanagement.domain.valueobject;

import java.util.Objects;

public record ProductDescription(String value) {

  public ProductDescription {
    final String normalizedValue = Objects.requireNonNull(value, "ProductDescription cannot be null").trim();
    value = normalizedValue;
  }

  @Override
  public String toString() {
    return value;
  }
}
