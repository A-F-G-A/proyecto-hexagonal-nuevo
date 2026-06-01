package com.jcaa.usersmanagement.domain.valueobject;

import com.jcaa.usersmanagement.domain.exception.InvalidProductException;
import java.math.BigDecimal;
import java.util.Objects;

public record ProductPrice(BigDecimal value) {

  public ProductPrice {
    Objects.requireNonNull(value, "ProductPrice cannot be null");
    validatePositive(value);
  }

  private static void validatePositive(final BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw InvalidProductException.becausePriceIsInvalid();
    }
  }

  @Override
  public String toString() {
    return value.toString();
  }
}
