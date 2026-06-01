package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ProductModel;

public interface UpdateProductPort {
  ProductModel update(ProductModel product);
}
