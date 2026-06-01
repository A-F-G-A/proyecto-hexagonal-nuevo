package com.jcaa.usersmanagement.application.service.dto.command;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductCommand(
    @NotBlank(message = "id must not be blank") String id,
    @NotBlank(message = "name must not be blank")
        @Size(min = 3, message = "name must have at least 3 characters")
        String name,
    @NotBlank(message = "description must not be blank") String description,
    @NotNull(message = "price must not be null")
        @DecimalMin(value = "0.01", message = "price must be greater than zero")
        BigDecimal price) {
}
