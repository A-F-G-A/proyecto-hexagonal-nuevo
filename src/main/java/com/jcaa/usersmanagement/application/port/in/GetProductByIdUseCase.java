package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetProductByIdQuery;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetProductByIdUseCase {
  ProductModel execute(@NotNull @Valid GetProductByIdQuery query);
}
