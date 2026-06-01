package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateProductCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteProductCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateProductCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetProductByIdQuery;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import com.jcaa.usersmanagement.domain.valueobject.ProductDescription;
import com.jcaa.usersmanagement.domain.valueobject.ProductId;
import com.jcaa.usersmanagement.domain.valueobject.ProductName;
import com.jcaa.usersmanagement.domain.valueobject.ProductPrice;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductApplicationMapper {

  public ProductModel fromCreateCommandToModel(final CreateProductCommand command) {
    return ProductModel.create(
        new ProductId(command.id()),
        new ProductName(command.name()),
        new ProductDescription(command.description()),
        new ProductPrice(command.price()));
  }

  public ProductModel fromUpdateCommandToModel(final UpdateProductCommand command) {
    return new ProductModel(
        new ProductId(command.id()),
        new ProductName(command.name()),
        new ProductDescription(command.description()),
        new ProductPrice(command.price()));
  }

  public ProductId fromGetProductByIdQueryToProductId(final GetProductByIdQuery query) {
    return new ProductId(query.id());
  }

  public ProductId fromDeleteCommandToProductId(final DeleteProductCommand command) {
    return new ProductId(command.id());
  }
}
