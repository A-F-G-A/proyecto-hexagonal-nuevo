package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.ProductDescription;
import com.jcaa.usersmanagement.domain.valueobject.ProductId;
import com.jcaa.usersmanagement.domain.valueobject.ProductName;
import com.jcaa.usersmanagement.domain.valueobject.ProductPrice;
import lombok.Value;

@Value
public class ProductModel {

  ProductId id;
  ProductName name;
  ProductDescription description;
  ProductPrice price;

  public static ProductModel create(
      final ProductId id,
      final ProductName name,
      final ProductDescription description,
      final ProductPrice price) {
    return new ProductModel(id, name, description, price);
  }

  public ProductModel update(
      final ProductName name,
      final ProductDescription description,
      final ProductPrice price) {
    return new ProductModel(this.id, name, description, price);
  }
}
