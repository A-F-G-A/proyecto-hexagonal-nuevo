package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.ProductModel;

import java.util.List;

public interface GetAllProductsUseCase {
  List<ProductModel> execute();
}
