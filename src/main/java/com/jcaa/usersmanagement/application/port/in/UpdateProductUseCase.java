package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateProductCommand;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateProductUseCase {
  ProductModel execute(@NotNull @Valid UpdateProductCommand command);
}
