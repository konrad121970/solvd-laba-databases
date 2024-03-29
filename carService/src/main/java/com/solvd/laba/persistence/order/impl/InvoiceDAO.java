package com.solvd.laba.persistence.order.impl;

import com.solvd.laba.domain.order.Invoice;
import com.solvd.laba.domain.stock.Product;
import com.solvd.laba.persistence.ConnectionPool;
import com.solvd.laba.persistence.order.IInvoiceDAO;
import com.solvd.laba.persistence.stock.impl.ProductDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO implements IInvoiceDAO {
    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String CREATE_QUERY = "INSERT INTO invoices (date_time, total_price) VALUES (?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT i.id AS invoice_id, i.date_time AS invoice_date_time, i.total_price AS invoice_total_price," +
            "                           p.id AS product_id, p.product_number AS product_number, p.name AS product_name, p.price AS product_price" +
            "                           FROM invoices i " +
            "                           LEFT JOIN invoices_has_products ihp ON i.id = ihp.invoices_id " +
            "                           LEFT JOIN products p ON ihp.products_id = p.id " +
            "                           WHERE i.id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM invoices";
    private static final String ADD_PRODUCT_TO_INVOICE_QUERY = "INSERT INTO invoices_has_products (invoices_id, products_id) VALUES (?, ?)";
    private static final String GET_PRODUCTS_BY_INVOICE_QUERY = "SELECT p.* FROM products p JOIN invoices_has_products pi ON p.id = pi.product_id WHERE pi.invoice_id = ?";
    private static final String DELETE_PRODUCT_FROM_INVOICE_QUERY = "DELETE FROM invoices_has_products WHERE invoice_id = ? AND product_id = ?";
    private static final String DELETE_INVOICE_QUERY = "DELETE FROM invoices WHERE id = ?";
    private static final String UPDATE_INVOICE_QUERY = "UPDATE invoices SET date_time = ?, total_price = ? WHERE id = ?";

    public static List<Invoice> mapRow(ResultSet resultSet, List<Invoice> invoices) throws SQLException {
        if (invoices == null) {
            invoices = new ArrayList<>();
        }

        Long invoiceId;
        try {
            invoiceId = resultSet.getLong("invoice_id");
        } catch (SQLException e) {
            invoiceId = null;
        }

        if (invoiceId != null) {
            Invoice invoice = findById(invoiceId, invoices);

            invoice.setId(invoiceId);
            invoice.setDateTime(resultSet.getTimestamp("invoice_date_time"));
            invoice.setTotalPrice(resultSet.getDouble("invoice_total_price"));

            invoice.setProducts(ProductDAO.mapRow(resultSet, invoice.getProducts()));

            invoices.add(invoice);
        }

        return invoices;
    }

    private static Invoice findById(Long id, List<Invoice> invoices) {
        return invoices.stream()
                .filter(invoice -> invoice.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    Invoice newInvoice = new Invoice();
                    newInvoice.setId(id);
                    invoices.add(newInvoice);
                    return newInvoice;
                });
    }

    public static List<Invoice> mapInvoices(ResultSet resultSet, List<Invoice> invoices) throws SQLException {
        if (invoices == null) {
            invoices = new ArrayList<>();
        }

        Long invoiceId = resultSet.getLong("invoice_id");

        if (invoiceId != 0) {
            Invoice invoice = findById(invoiceId, invoices);

            invoice.setId(invoiceId);
            invoice.setDateTime(resultSet.getTimestamp("invoice_date_time"));
            invoice.setTotalPrice(resultSet.getDouble("invoice_total_price"));

            invoices.add(invoice);
        }

        return invoices;
    }


    @Override
    public void create(Invoice invoice, Long serviceOrderId) {
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setTimestamp(1, invoice.getDateTime());
            preparedStatement.setDouble(2, invoice.getTotalPrice());

            preparedStatement.executeUpdate();

            LOGGER.info("Invoice created: {}", invoice);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Invoice getById(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();
        List<Invoice> invoices = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            preparedStatement.setLong(1, id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                mapRow(result, invoices);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return invoices.get(0);
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(result.getLong("id"));
                invoice.setDateTime(result.getTimestamp("date_time"));
                invoice.setTotalPrice(result.getDouble("total_price"));
                invoice.setProducts(getProductsByInvoice(invoice.getId()));

                invoices.add(invoice);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return invoices;
    }

    @Override
    public void update(Invoice invoice) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INVOICE_QUERY)) {
            preparedStatement.setTimestamp(1, invoice.getDateTime());
            preparedStatement.setDouble(2, invoice.getTotalPrice());
            preparedStatement.setLong(3, invoice.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Invoice updated: {}", invoice);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVOICE_QUERY)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            LOGGER.info("Invoice deleted with id: {}", id);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void addProductToInvoice(Invoice invoice, Product product) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_TO_INVOICE_QUERY)) {
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setLong(2, invoice.getId());

            preparedStatement.executeUpdate();

            LOGGER.info("Product {} added to Invoice {}", product.getName(), invoice.getId());
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteProductFromInvoice(Invoice invoice, Product product) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_FROM_INVOICE_QUERY)) {
            preparedStatement.setLong(1, invoice.getId());
            preparedStatement.setLong(2, product.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                LOGGER.info("Product {} deleted from Invoice {}", product.getName(), invoice.getId());
            } else {
                LOGGER.warn("Product {} not found in Invoice {}", product.getName(), invoice.getId());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Product> getProductsByInvoice(Long invoiceId) {
        List<Product> products = new ArrayList<>();
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_INVOICE_QUERY)) {
            preparedStatement.setLong(1, invoiceId);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Product product = new Product();
                product.setId(result.getLong("id"));
                product.setName(result.getString("name"));
                product.setProductNumber(result.getString("product_number"));
                product.setPrice(result.getDouble("price"));

                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return products;
    }

}
