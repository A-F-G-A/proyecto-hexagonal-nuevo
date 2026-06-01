package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller;

import com.jcaa.usersmanagement.application.port.in.CreateProductUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteProductUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllProductsUseCase;
import com.jcaa.usersmanagement.application.port.in.GetProductByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateProductUseCase;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.CreateProductRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProductResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateProductRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.mapper.ProductDesktopMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public final class ProductController {

  private final CreateProductUseCase createProductUseCase;
  private final UpdateProductUseCase updateProductUseCase;
  private final DeleteProductUseCase deleteProductUseCase;
  private final GetProductByIdUseCase getProductByIdUseCase;
  private final GetAllProductsUseCase getAllProductsUseCase;

  public List<ProductResponse> listAllProducts() {
    final var products = getAllProductsUseCase.execute();
    return ProductDesktopMapper.toResponseList(products);
  }

  public ProductResponse findProductById(final String id) {
    final var query = ProductDesktopMapper.toGetByIdQuery(id);
    final var product = getProductByIdUseCase.execute(query);
    return ProductDesktopMapper.toResponse(product);
  }

  public ProductResponse createProduct(final CreateProductRequest request) {
    final var command = ProductDesktopMapper.toCreateCommand(request);
    final var product = createProductUseCase.execute(command);
    return ProductDesktopMapper.toResponse(product);
  }

  public ProductResponse updateProduct(final UpdateProductRequest request) {
    final var command = ProductDesktopMapper.toUpdateCommand(request);
    final var product = updateProductUseCase.execute(command);
    return ProductDesktopMapper.toResponse(product);
  }

  public void deleteProduct(final String id) {
    final var command = ProductDesktopMapper.toDeleteCommand(id);
    deleteProductUseCase.execute(command);
  }
}
