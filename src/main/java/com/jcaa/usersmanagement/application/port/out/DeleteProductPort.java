package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.valueobject.ProductId;

public interface DeleteProductPort {
  void delete(ProductId productId);
}
