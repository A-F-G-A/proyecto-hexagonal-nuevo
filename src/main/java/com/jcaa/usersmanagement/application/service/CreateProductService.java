package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateProductUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveProductPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateProductCommand;
import com.jcaa.usersmanagement.application.service.mapper.ProductApplicationMapper;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.Set;
import jakarta.validation.ConstraintViolation;

@Log
@RequiredArgsConstructor
public final class CreateProductService implements CreateProductUseCase {

  private final SaveProductPort saveProductPort;
  private final Validator validator;

  @Override
  public ProductModel execute(final CreateProductCommand command) {
    validateCommand(command);

    final ProductModel productToSave = ProductApplicationMapper.fromCreateCommandToModel(command);
    return saveProductPort.save(productToSave);
  }

  private void validateCommand(final CreateProductCommand command) {
    final Set<ConstraintViolation<CreateProductCommand>> violations = validator.validate(command);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
