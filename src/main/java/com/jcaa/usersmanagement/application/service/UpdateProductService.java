package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateProductUseCase;
import com.jcaa.usersmanagement.application.port.out.GetProductByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateProductPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProductCommand;
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
public final class UpdateProductService implements UpdateProductUseCase {

  private final UpdateProductPort updateProductPort;
  private final GetProductByIdPort getProductByIdPort;
  private final Validator validator;

  @Override
  public ProductModel execute(final UpdateProductCommand command) {
    validateCommand(command);

    final ProductId productId = new ProductId(command.id());
    ensureProductExists(productId);

    final ProductModel productToUpdate = ProductApplicationMapper.fromUpdateCommandToModel(command);
    return updateProductPort.update(productToUpdate);
  }

  private void validateCommand(final UpdateProductCommand command) {
    final Set<ConstraintViolation<UpdateProductCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }

  private void ensureProductExists(final ProductId productId) {
    getProductByIdPort
        .getById(productId)
        .orElseThrow(() -> ProductNotFoundException.becauseIdWasNotFound(productId.value()));
  }
}
