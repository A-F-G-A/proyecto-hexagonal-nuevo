package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ProductNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProductController;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class DeleteProductHandler implements OperationHandler {

  private final ProductController productController;
  private final ConsoleIO console;

  @Override
  public void handle() {
    final String id = console.readRequired("ID: ");

    try {
      productController.deleteProduct(id);
      console.println("\n  Product deleted successfully.");
    } catch (final ProductNotFoundException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
