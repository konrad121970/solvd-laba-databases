package com.solvd.laba.persistence.stock.impl;

import com.solvd.laba.domain.stock.Stock;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.stock.IStockDAO;
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
    private static final String GET_BY_ID_QUERY = "SELECT s.id AS stock_id, s.name AS stock_name, " +
            "p.id AS product_id, p.product_number AS product_number, p.name AS product_name, p.price AS product_price " +
            "FROM stocks s " +
            "LEFT JOIN stocks_products sp ON s.id = sp.stock_id " +
            "LEFT JOIN products p ON sp.product_id = p.id " +
            "WHERE s.id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM stocks";
    private static final String UPDATE_QUERY = "UPDATE stocks SET name = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM stocks WHERE id = ?";
    private static final String ADD_PRODUCT_TO_STOCK_QUERY = "INSERT INTO stocks_products (stock_id, product_id) VALUES (?, ?)";
    private static final String REMOVE_PRODUCT_FROM_STOCK_QUERY = "DELETE FROM stocks_products WHERE stock_id = ? AND product_id = ?";

    public static List<Stock> mapRow(ResultSet resultSet, List<Stock> stocks) throws SQLException {
        if (stocks == null) {
            stocks = new ArrayList<>();
        }

        Long stockId = resultSet.getLong("stock_id");

        if (stockId != 0) {
            Stock stock = findById(stockId, stocks);

            stock.setId(stockId);
            stock.setName(resultSet.getString("stock_name"));

            stock.setProductList(ProductDAO.mapRow(resultSet, stock.getProductList()));

            stocks.add(stock);
        }

        return stocks;
    }

    private static Stock findById(Long id, List<Stock> stocks) {
        return stocks.stream()
                .filter(stock -> stock.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Stock newStock = new Stock();
                    newStock.setId(id);
                    stocks.add(newStock);
                    return newStock;
                });
    }

    public static List<Stock> mapStock(ResultSet resultSet, List<Stock> stocks) throws SQLException {
        if (stocks == null) {
            stocks = new ArrayList<>();
        }

        Long stockId = resultSet.getLong("stock_id");

        if (stockId != 0) {
            Stock stock = findById(stockId, stocks);

            stock.setId(stockId);
            stock.setName(resultSet.getString("stock_name"));

            stocks.add(stock);
        }

        return stocks;
    }

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
        List<Stock> stocks = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                mapRow(result, stocks);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return stocks.get(0);
    }

    @Override
    public List<Stock> getAll() {
        List<Stock> stocks = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                mapRow(result, stocks);
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
    public void addProductToStock(Long stockId, Long productId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_STOCK_QUERY)) {
            preparedStatement.setLong(1, stockId);
            preparedStatement.setLong(2, productId);

            preparedStatement.executeUpdate();

            LOGGER.info("Product with id {} added to stock with id {}", productId, stockId);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void removeProductFromStock(Long stockId, Long productId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PRODUCT_FROM_STOCK_QUERY)) {
            preparedStatement.setLong(1, stockId);
            preparedStatement.setLong(2, productId);

            preparedStatement.executeUpdate();

            LOGGER.info("Product with id {} removed from stock with id {}", productId, stockId);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
