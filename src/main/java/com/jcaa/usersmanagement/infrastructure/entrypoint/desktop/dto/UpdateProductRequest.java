package com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.dto;

import java.math.BigDecimal;

public record UpdateProductRequest(
    String id,
    String name,
    String description,
    BigDecimal price) {}
