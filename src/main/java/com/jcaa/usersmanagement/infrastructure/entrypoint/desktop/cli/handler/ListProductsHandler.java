package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProductResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProductController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ListProductsHandler implements OperationHandler {

  private final ProductController productController;
  private final ProductResponsePrinter printer;

  @Override
  public void handle() {
    final var products = productController.listAllProducts();
    printer.printList(products);
  }
}
