package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllProductsUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllProductsPort;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.util.List;

@Log
@RequiredArgsConstructor
public final class GetAllProductsService implements GetAllProductsUseCase {

  private final GetAllProductsPort getAllProductsPort;

  @Override
  public List<ProductModel> execute() {
    return getAllProductsPort.getAll();
  }
}
