package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.handler;

import com.jcaa.usersmanagement.domain.exception.ProductNotFoundException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ConsoleIO;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io.ProductResponsePrinter;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProductController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProductResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.UpdateProductRequest;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public final class UpdateProductHandler implements OperationHandler {

  private final ProductController productController;
  private final ConsoleIO console;
  private final ProductResponsePrinter printer;

  @Override
  public void handle() {
    final String id          = console.readRequired("ID          : ");
    final String name        = console.readRequired("Name        : ");
    final String description = console.readRequired("Description : ");
    final String priceStr    = console.readRequired("Price       : ");

    try {
      final BigDecimal price = new BigDecimal(priceStr);
      final ProductResponse updated =
          productController.updateProduct(new UpdateProductRequest(id, name, description, price));
      console.println("\n  Product updated successfully.");
      printer.print(updated);
    } catch (final NumberFormatException exception) {
      console.println("  Error: Invalid price format. Please enter a valid number.");
    } catch (final ProductNotFoundException exception) {
      console.println("  Error: " + exception.getMessage());
    }
  }
}
