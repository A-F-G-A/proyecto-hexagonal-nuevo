package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.ProductModel;
import com.jcaa.usersmanagement.domain.valueobject.ProductDescription;
import com.jcaa.usersmanagement.domain.valueobject.ProductId;
import com.jcaa.usersmanagement.domain.valueobject.ProductName;
import com.jcaa.usersmanagement.domain.valueobject.ProductPrice;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ProductPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.ProductEntity;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ProductPersistenceMapper {

  public ProductPersistenceDto fromModelToDto(final ProductModel product) {
    return new ProductPersistenceDto(
        product.getId().value(),
        product.getName().value(),
        product.getDescription().value(),
        product.getPrice().value(),
        null,
        null);
  }

  public ProductEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new ProductEntity(
        resultSet.getString("id"),
        resultSet.getString("name"),
        resultSet.getString("description"),
        resultSet.getBigDecimal("price"),
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public ProductModel fromEntityToModel(final ProductEntity entity) {
    return new ProductModel(
        new ProductId(entity.id()),
        new ProductName(entity.name()),
        new ProductDescription(entity.description()),
        new ProductPrice(entity.price()));
  }

  public ProductModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<ProductModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<ProductModel> products = new ArrayList<>();
    while (resultSet.next()) {
      products.add(fromResultSetToModel(resultSet));
    }
    return products;
  }
}
