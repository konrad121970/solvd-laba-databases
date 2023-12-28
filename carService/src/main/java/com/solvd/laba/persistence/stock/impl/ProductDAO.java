package com.solvd.laba.persistence.stock.impl;

import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.order.impl.InvoiceDAO;
import com.solvd.laba.persistence.stock.IProductDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO products (product_number, name, price) VALUES (?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT p.id AS product_id, p.product_number, p.name as product_name, p.price as product_price," +
            "s.name as stock_name, i.id as invoice_id, i.date_time as invoice_date_time, i.total_price as invoice_total_price, s.id as stock_id, s.name as stock_name " +
            "FROM products p " +
            "LEFT JOIN stocks_has_products shp ON p.id = shp.products_id " +
            "LEFT JOIN stocks s ON shp.stocks_id = s.id " +
            "LEFT JOIN invoices_has_products ihp ON p.id = ihp.products_id " +
            "LEFT JOIN invoices i ON ihp.invoices_id = i.id " +
            "WHERE p.id = ?";
    private static final String GET_ALL_QUERY = "SELECT p.id AS product_id, p.product_number, p.name, p.price " +
            "FROM products p " +
            "LEFT JOIN stocks_has_products shp ON p.id = shp.products_id " +
            "LEFT JOIN stocks s ON shp.stocks_id = s.id " +
            "LEFT JOIN invoices_has_products ihp ON p.id = ihp.products_id " +
            "LEFT JOIN invoices i ON ihp.invoices_id = i.id";

    private static final String UPDATE_QUERY = "UPDATE products SET product_number = ?, name = ?, price = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM products WHERE id = ?";

    public static List<Product> mapRow(ResultSet resultSet, List<Product> products) throws SQLException {
        if (products == null) {
            products = new ArrayList<>();
        }

        Long productId;
        try {
            productId = resultSet.getLong("product_id");
        } catch (Exception e) {
            return null;
        }


        if (productId != 0) {
            Product product = findById(productId, products);

            product.setId(productId);
            product.setProductNumber(resultSet.getString("product_number"));
            product.setName(resultSet.getString("product_name"));
            product.setPrice(resultSet.getDouble("product_price"));

            product.setInvoices(InvoiceDAO.mapInvoices(resultSet, product.getInvoices()));

            try {
                int stockIdColumnIndex = resultSet.findColumn("stock_id");
                if (stockIdColumnIndex != 0) {
                    Long stockId = resultSet.getLong("stock_id");
                    if (stockId != 0) {
                        product.setStocks(StockDAO.mapStock(resultSet, product.getStocks()));
                    }
                }
            } catch (SQLException e) {
                product.setStocks(null);
            }


            products.add(product);
        }

        return products;
    }

    private static Product findById(Long id, List<Product> products) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Product newProduct = new Product();
                    newProduct.setId(id);
                    products.add(newProduct);
                    return newProduct;
                });
    }


    @Override
    public void create(Product product) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, product.getProductNumber());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());

            preparedStatement.executeUpdate();

            LOGGER.info("Product created: {}", product);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Product getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                mapRow(result, products);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return products.get(0);
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                mapRow(result, products);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return products;
    }

    @Override
    public void update(Product product) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, product.getProductNumber());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setLong(4, product.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Product updated: {}", product);

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

            LOGGER.info("Product deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

}
