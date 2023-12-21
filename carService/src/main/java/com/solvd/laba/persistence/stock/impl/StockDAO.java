package com.solvd.laba.persistence.stock.impl;

import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.stock.IStockDAO;
import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.domain.stock.Stock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO implements IStockDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO stocks (name) VALUES (?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM stocks WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM stocks";
    private static final String UPDATE_QUERY = "UPDATE stocks SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM stocks WHERE id = ?";
    private static final String GET_PRODUCTS_BY_STOCK_ID_QUERY =
            "SELECT p.* FROM products p JOIN stocks_products sp ON p.id = sp.product_id WHERE sp.stock_id = ?";

    @Override
    public void create(Stock stock) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, stock.getName());

            preparedStatement.executeUpdate();

            LOGGER.info("Stock created: {}", stock);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Stock getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        Stock stock = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                stock = mapResultSetToStock(result);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return stock;
    }

    @Override
    public List<Stock> getAll() {
        List<Stock> stocks = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Stock stock = mapResultSetToStock(result);
                stocks.add(stock);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return stocks;
    }

    @Override
    public void update(Stock stock) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, stock.getName());
            preparedStatement.setLong(2, stock.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Stock updated: {}", stock);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Stock deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Product> getProductsByStockId(Long stockId) {
        List<Product> products = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_STOCK_ID_QUERY)) {
            preparedStatement.setLong(1, stockId);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Product product = mapResultSetToProduct(result);
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return products;
    }

    private Stock mapResultSetToStock(ResultSet result) throws SQLException {
        Stock stock = new Stock();
        stock.setId(result.getLong("id"));
        stock.setName(result.getString("name"));

        return stock;
    }

    private Product mapResultSetToProduct(ResultSet result) throws SQLException {
        Product product = new Product();
        product.setId(result.getLong("id"));
        product.setProductNumber(result.getString("product_number"));
        product.setName(result.getString("name"));
        product.setPrice(result.getDouble("price"));

        return product;
    }
}