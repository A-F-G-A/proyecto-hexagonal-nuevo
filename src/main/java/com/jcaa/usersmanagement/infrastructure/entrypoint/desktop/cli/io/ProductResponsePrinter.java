package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.cli.io;

import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto.ProductResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class ProductResponsePrinter {

  private static final String SEPARATOR = "-".repeat(52);
  private static final String ROW_FORMAT = "  %-12s : %s%n";

  private final ConsoleIO console;

  public void print(final ProductResponse response) {
    console.println(SEPARATOR);
    console.printf(ROW_FORMAT, "ID",          response.id());
    console.printf(ROW_FORMAT, "Name",        response.name());
    console.printf(ROW_FORMAT, "Description", response.description());
    console.printf(ROW_FORMAT, "Price",       response.price());
    console.println(SEPARATOR);
  }

  public void printList(final List<ProductResponse> products) {
    if (products.isEmpty()) {
      console.println("  No products found.");
      return;
    }
    console.printf("%n  Total: %d product(s)%n", products.size());
    products.forEach(this::print);
  }
}
