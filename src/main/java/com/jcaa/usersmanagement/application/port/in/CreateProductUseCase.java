package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProductCommand;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateProductUseCase {
  ProductModel execute(@NotNull @Valid CreateProductCommand command);
}
