package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetProductByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetProductByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetProductByIdQuery;
import com.jcaa.usersmanagement.application.service.mapper.ProductApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ProductNotFoundException;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import com.jcaa.usersmanagement.domain.valueobject.ProductId;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

@Log
@RequiredArgsConstructor
public final class GetProductByIdService implements GetProductByIdUseCase {

  private final GetProductByIdPort getProductByIdPort;
  private final Validator validator;

  @Override
  public ProductModel execute(final GetProductByIdQuery query) {
    validateQuery(query);

    final ProductId productId = ProductApplicationMapper.fromGetProductByIdQueryToProductId(query);
    return getProductByIdPort
        .getById(productId)
        .orElseThrow(() -> ProductNotFoundException.becauseIdWasNotFound(productId.value()));
  }

  private void validateQuery(final GetProductByIdQuery query) {
    final Set<ConstraintViolation<GetProductByIdQuery>> violations = validator.validate(query);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
