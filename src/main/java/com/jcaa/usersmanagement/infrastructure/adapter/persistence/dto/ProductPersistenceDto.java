package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

import java.math.BigDecimal;

public record ProductPersistenceDto(
    String id,
    String name,
    String description,
    BigDecimal price,
    String createdAt,
    String updatedAt) {}
