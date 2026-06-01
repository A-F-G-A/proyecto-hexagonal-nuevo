package com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity;

import java.math.BigDecimal;

public record ProductEntity(
    String id,
    String name,
    String description,
    BigDecimal price,
    String createdAt,
    String updatedAt) {}
