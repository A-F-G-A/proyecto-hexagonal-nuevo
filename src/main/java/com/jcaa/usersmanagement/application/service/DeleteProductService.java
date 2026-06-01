package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteProductUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteProductPort;
import com.jcaa.usersmanagement.application.port.out.GetProductByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProductCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProductApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.ProductNotFoundException;
import com.jcaa.usersmanagement.domain.valueobject.ProductId;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

@Log
@RequiredArgsConstructor
public final class DeleteProductService implements DeleteProductUseCase {

  private final DeleteProductPort deleteProductPort;
  private final GetProductByIdPort getProductByIdPort;
  private final Validator validator;

  @Override
  public void execute(final DeleteProductCommand command) {
    validateCommand(command);

    final ProductId productId = ProductApplicationMapper.fromDeleteCommandToProductId(command);
    ensureProductExists(productId);

    deleteProductPort.delete(productId);
  }

  private void validateCommand(final DeleteProductCommand command) {
    final Set<ConstraintViolation<DeleteProductCommand>> violations = validator.validate(command);
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
