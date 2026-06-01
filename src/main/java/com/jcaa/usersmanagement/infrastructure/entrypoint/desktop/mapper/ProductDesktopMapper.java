package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProductCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProductCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProductCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetProductByIdQuery;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateProductRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProductResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateProductRequest;

import java.util.List;

public final class ProductDesktopMapper {

  private ProductDesktopMapper() {}

  public static CreateProductCommand toCreateCommand(final CreateProductRequest request) {
    return new CreateProductCommand(
        request.id(), request.name(), request.description(), request.price());
  }

  public static UpdateProductCommand toUpdateCommand(final UpdateProductRequest request) {
    return new UpdateProductCommand(
        request.id(), request.name(), request.description(), request.price());
  }

  public static DeleteProductCommand toDeleteCommand(final String id) {
    return new DeleteProductCommand(id);
  }

  public static GetProductByIdQuery toGetByIdQuery(final String id) {
    return new GetProductByIdQuery(id);
  }

  public static ProductResponse toResponse(final ProductModel product) {
    return new ProductResponse(
        product.getId().value(),
        product.getName().value(),
        product.getDescription().value(),
        product.getPrice().value());
  }

  public static List<ProductResponse> toResponseList(final List<ProductModel> products) {
    return products.stream().map(ProductDesktopMapper::toResponse).toList();
  }
}
