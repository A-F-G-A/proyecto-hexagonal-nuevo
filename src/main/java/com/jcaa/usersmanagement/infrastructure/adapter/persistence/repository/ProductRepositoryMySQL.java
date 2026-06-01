package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteProductPort;
import com.jcaa.usersmanagement.application.port.out.GetAllProductsPort;
import com.jcaa.usersmanagement.application.port.out.GetProductByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveProductPort;
import com.jcaa.usersmanagement.application.port.out.UpdateProductPort;
import com.jcaa.usersmanagement.domain.exception.ProductNotFoundException;
import com.jcaa.usersmanagement.domain.model.ProductModel;
import com.jcaa.usersmanagement.domain.valueobject.ProductId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ProductPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ProductPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class ProductRepositoryMySQL
    implements SaveProductPort,
        UpdateProductPort,
        GetProductByIdPort,
        GetAllProductsPort,
        DeleteProductPort {

  private static final String SQL_INSERT =
      "INSERT INTO products "
      + "(id, name, description, price, created_at, updated_at) "
      + "VALUES (?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE products SET name = ?, description = ?, price = ?, updated_at = NOW() "
      + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, name, description, price, created_at, updated_at "
      + "FROM products "
      + "WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, name, description, price, created_at, updated_at "
      + "FROM products "
      + "ORDER BY name ASC";

  private static final String SQL_DELETE =
      "DELETE FROM products "
      + "WHERE id = ?";

  private final Connection connection;

  @Override
  public ProductModel save(final ProductModel product) {
    final ProductPersistenceDto dto = ProductPersistenceMapper.fromModelToDto(product);
    executeSave(dto);
    return findByIdOrFail(product.getId());
  }

  @Override
  public ProductModel update(final ProductModel product) {
    final ProductPersistenceDto dto = ProductPersistenceMapper.fromModelToDto(product);
    executeUpdate(dto);
    return findByIdOrFail(product.getId());
  }

  @Override
  public Optional<ProductModel> getById(final ProductId productId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setString(1, productId.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(ProductPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByIdFailed(productId.value(), exception);
    }
  }

  @Override
  public List<ProductModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return ProductPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final ProductId productId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, productId.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeleteFailed(productId.value(), exception);
    }
  }

  private void executeSave(final ProductPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.id());
      statement.setString(2, dto.name());
      statement.setString(3, dto.description());
      statement.setBigDecimal(4, dto.price());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseSaveFailed(dto.id(), exception);
    }
  }

  private void executeUpdate(final ProductPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.name());
      statement.setString(2, dto.description());
      statement.setBigDecimal(3, dto.price());
      statement.setString(4, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdateFailed(dto.id(), exception);
    }
  }

  private ProductModel findByIdOrFail(final ProductId productId) {
    return getById(productId)
        .orElseThrow(() -> ProductNotFoundException.becauseIdWasNotFound(productId.value()));
  }
}
