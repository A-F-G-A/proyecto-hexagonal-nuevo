package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.ProductModel;

import java.util.List;

public interface GetAllProductsPort {
  List<ProductModel> getAll();
}
